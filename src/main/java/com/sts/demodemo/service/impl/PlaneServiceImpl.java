package com.sts.demodemo.service.impl;

import com.sts.demodemo.dao.PlaneMapper;
import com.sts.demodemo.entity.Plane;
import com.sts.demodemo.entity.Ways;
import com.sts.demodemo.service.PlaneService;
import com.sts.demodemo.util.DeepClone;
import com.sts.demodemo.util.RedisUtil;
import com.sts.demodemo.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class PlaneServiceImpl implements PlaneService {

    @Autowired
    private PlaneMapper planeMapper;

    @Autowired
    private TimeUtil timeUtil;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private DeepClone deepClone;

    @Override
    public List<Ways> findAllNeedPlane(Ways ways, String startTime, int p) {
        List<Plane> planes;
        String key = "planes_"+ways.getTraffics().get(p).getT_id();
        boolean hasKey = redisUtil.hasKey(key);
        if (hasKey){
            planes = (List<Plane>) redisUtil.get(key);
        }else {
            planes = planeMapper.findAllPlaneByL_place_id(ways.getTraffics().get(p).getT_id());
            redisUtil.set(key, planes);
        }


        List<Plane> useplane = new ArrayList<>();

        int s = timeUtil.timehandleToInt(startTime);

        int starttime = s + ways.getTotalTime();


        //先找到对自己有用的数据
        for (int i = 0; i < planes.size(); i++){
            if ((timeUtil.timehandleToInt(planes.get(i).getL_start_time()) - 3600) < starttime){
                planes.remove(i);
                i--;
            }
        }

        //按到达目的地时间排序
        Collections.sort(planes, new Comparator<Plane>() {
            @Override
            public int compare(Plane o1, Plane o2) {
                int num = 1;
                if (timeUtil.timehandleToInt(o1.getL_end_time()) < timeUtil.timehandleToInt(o2.getL_end_time())){
                    num = -1;
                }else if (timeUtil.timehandleToInt(o1.getL_end_time()) > timeUtil.timehandleToInt(o2.getL_end_time())){
                    num = 1;
                }else {
                    if (timeUtil.timehandleToInt(o1.getL_pass_time()) < timeUtil.timehandleToInt(o2.getL_pass_time())){
                        num = -1;
                    }else if (timeUtil.timehandleToInt(o1.getL_pass_time()) > timeUtil.timehandleToInt(o2.getL_pass_time())){
                        num = 1;
                    }
                }
                return num;
            }
        });



        List<String> times;

        List<String> moneys;
        double totalmoney;

        List<Ways> waysList = new ArrayList<>();

        int planestart;
        int planeend;
        int planepass;
        int waittime;
        for (int i = 0; i < planes.size(); i++){
            Ways ways1 = null;
            ways1 = (Ways) deepClone.deepClone(ways);

            times = ways1.getTimes();
            moneys = ways1.getMoneys();
            useplane = ways1.getPlanes();
            if (useplane == null){
                useplane = new ArrayList<>();
            }
            totalmoney = ways1.getTotalMoney();
            if (ways1.getTraffics().get(p).getT_method1().equals(planes.get(i).getL_start_place()) && ways1.getTraffics().get(p).getT_method2().equals(planes.get(i).getL_end_place())){
                planestart = timeUtil.timehandleToInt(planes.get(i).getL_start_time());
                planeend = timeUtil.timehandleToInt(planes.get(i).getL_end_time());
                planepass = timeUtil.timehandleToInt(planes.get(i).getL_pass_time());
                waittime = planestart - starttime;
                times.add("需要等待飞机"+timeUtil.timehandleToString(waittime)+"时间");
                times.add("飞机需要坐"+timeUtil.timehandleToString(planepass)+"时间");
                moneys.add("坐飞机需要花费"+(Double.parseDouble(new DecimalFormat("0.00").format(planes.get(i).getL_price() * planes.get(i).getL_sale()))));
                ways1.setTimes(times);
                ways1.setMoneys(moneys);
                ways1.setTotalMoney(Double.parseDouble(new DecimalFormat("0.00").format(totalmoney + planes.get(i).getL_price() * planes.get(i).getL_sale())));
                if ((planeend - s) < 0){
                    continue;
                }
                ways1.setTotalTime(planeend - s);
                useplane.add(planes.get(i));
                ways1.setPlanes(useplane);
                waysList.add(ways1);

            }
        }

        return waysList;
    }
}
