package com.sts.demodemo.service.impl;

import com.sts.demodemo.dao.MotorcarMapper;
import com.sts.demodemo.entity.Motorcar;
import com.sts.demodemo.entity.Ways;
import com.sts.demodemo.service.MotorcarService;
import com.sts.demodemo.util.DeepClone;
import com.sts.demodemo.util.RedisUtil;
import com.sts.demodemo.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class MotorcarServiceImpl implements MotorcarService {

    @Autowired
    private MotorcarMapper motorcarMapper;

    @Autowired
    private TimeUtil timeUtil;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private DeepClone deepClone;

    @Override
    public List<Ways> findAllNeedMotorcar(Ways ways, String startTime, int p){
        List<Motorcar> motorcars;
        String key = "motorcars_"+ways.getTraffics().get(p).getT_id();
        boolean hasKey = redisUtil.hasKey(key);
        if (hasKey){
            motorcars = (List<Motorcar>) redisUtil.get(key);
        }else {
            motorcars = motorcarMapper.findAllMotorcarByM_place_id(ways.getTraffics().get(p).getT_id());
            redisUtil.set(key, motorcars);
        }

        List<Motorcar> usemotor = new ArrayList<>();

        int starttime = timeUtil.timehandleToInt(startTime);
        int starttime1 = timeUtil.timehandleToInt(startTime) + ways.getTotalTime();

        //先找到对自己有用的数据
        for (int i = 0; i < motorcars.size(); i++){
            if ((timeUtil.timehandleToInt(motorcars.get(i).getM_start_time()) - 1800) < starttime1){
                motorcars.remove(i);
                i--;
            }
        }

        Motorcar motorcar;
        //按到达目的地时间排序
        Collections.sort(motorcars, new Comparator<Motorcar>() {
            @Override
            public int compare(Motorcar o1, Motorcar o2) {
                int num = 1;
                if (timeUtil.timehandleToInt(o1.getM_end_time()) < timeUtil.timehandleToInt(o2.getM_end_time())){
                    num = -1;
                }else if (timeUtil.timehandleToInt(o1.getM_end_time()) > timeUtil.timehandleToInt(o2.getM_end_time())){
                    num = 1;
                }else {
                    if (timeUtil.timehandleToInt(o1.getM_pass_time()) < timeUtil.timehandleToInt(o2.getM_pass_time())){
                        num = -1;
                    }else if (timeUtil.timehandleToInt(o1.getM_pass_time()) > timeUtil.timehandleToInt(o2.getM_pass_time())){
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

        int mstart;
        int mend;
        int mpass;
        int waittime;
        for (int i = 0; i < motorcars.size(); i++){
            Ways ways1 = null;
            ways1 = (Ways) deepClone.deepClone(ways);

            times = ways1.getTimes();
            moneys = ways1.getMoneys();
            totalmoney = ways1.getTotalMoney();
           if (ways1.getTraffics().get(p).getT_method1().equals(motorcars.get(i).getM_start_place()) && ways1.getTraffics().get(p).getT_method2().equals(motorcars.get(i).getM_end_place())){
                mstart = timeUtil.timehandleToInt(motorcars.get(i).getM_start_time());
                mend = timeUtil.timehandleToInt(motorcars.get(i).getM_end_time());
                mpass = timeUtil.timehandleToInt(motorcars.get(i).getM_pass_time());
                waittime = mstart - starttime1;
                if(waittime < 0){
                    continue;
                }
                times.add("需要等待高铁"+timeUtil.timehandleToString(waittime)+"时间");
                times.add("高铁需要坐"+timeUtil.timehandleToString(mpass)+"时间");
                moneys.add("坐高铁需要花费"+(motorcars.get(i).getM_first_rate_price()));
                ways1.setTimes(times);
                ways1.setMoneys(moneys);
                ways1.setTotalMoney(totalmoney + motorcars.get(i).getM_first_rate_price());
                ways1.setTotalTime(mend - starttime);
                usemotor = new ArrayList<>();
                usemotor.add(motorcars.get(i));
                ways1.setMotorcars(usemotor);
                waysList.add(ways1);
            }
        }

        return waysList;
    }




}
