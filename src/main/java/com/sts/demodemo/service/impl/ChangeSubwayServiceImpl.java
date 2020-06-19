package com.sts.demodemo.service.impl;

import com.sts.demodemo.dao.ChangeSubwayMapper;
import com.sts.demodemo.entity.ChangeSubway;
import com.sts.demodemo.entity.Subway;
import com.sts.demodemo.entity.Ways;
import com.sts.demodemo.service.ChangeSubwayService;
import com.sts.demodemo.util.RedisUtil;
import com.sts.demodemo.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service("changeSubwayService")
public class ChangeSubwayServiceImpl implements ChangeSubwayService {

    @Autowired
    private ChangeSubwayMapper changeSubwayMapper;

    @Autowired
    private TimeUtil timeUtil;

    @Autowired
    private RedisUtil redisUtil;

    private Logger log = LoggerFactory.getLogger(ChangeSubwayServiceImpl.class);

    //这里实现得操作有   将地铁按线路分开   并加入换乘时间
    @Override
    public List<Ways> findNeedAllChange(List<Ways> ways, String startTime) {
        List<ChangeSubway> changeSubwayList;
        String key = "changeSubwayList_"+ways.get(0).getStartEndPlaces().get(0).getP_start();
        boolean hasKey = redisUtil.hasKey(key);
        if (hasKey){
            changeSubwayList = (List<ChangeSubway>) redisUtil.get(key);
        }else {
            Set<ChangeSubway> changeSubways = changeSubwayMapper.findAllChangeByStartPlace(ways.get(0).getStartEndPlaces().get(0).getP_start());
            changeSubwayList = new ArrayList<>();
            changeSubwayList.addAll(changeSubways);
            redisUtil.set(key, changeSubwayList);
        }

        //先判断有没有换乘
        int[] a = new int[ways.size()];
        for (int i = 0; i < ways.size(); i++){
            a[i] = 0;
            for (int j = 0; j < ways.get(i).getSubways().size() - 1; j++){
                if (!(ways.get(i).getSubways().get(j).getSubway().getS_sid().equals(ways.get(i).getSubways().get(j + 1).getSubway().getS_sid()))){
                    a[i]++;
                }
            }
        }

        List<ChangeSubway> changeSubways;
        for (int  i = 0; i < ways.size(); i++){
            if(a[i] == 0){
                continue;
            }else {
                changeSubways = new ArrayList<>();
                for (int j = 0; j < ways.get(i).getSubways().size() - 1; j++){
                    if (!ways.get(i).getSubways().get(j).getSubway().getS_sid().equals(ways.get(i).getSubways().get(j + 1).getSubway().getS_sid())){
                        for (int z = 0; z < changeSubwayList.size(); z++){
                            //匹配不上
                            if (changeSubwayList.get(z).getC_place().equals(ways.get(i).getSubways().get(j).getSubway().getS_place()) && changeSubwayList.get(z).getC_last_sid().equals(ways.get(i).getSubways().get(j).getSubway().getS_sid()) && changeSubwayList.get(z).getC_now_sid().equals(ways.get(i).getSubways().get(j + 1).getSubway().getS_sid())){
                                changeSubways.add(changeSubwayList.get(z));
                            }
                        }
                    }
                }
                ways.get(i).setChangeSubways(changeSubways);
            }
        }

        List<String> money;

        //对地铁的money进行处理
        for (int i = 0; i < ways.size(); i++){
            money = new ArrayList<>();
            double m;
            double totalmoney = 0;
            if(ways.get(i).getChangeSubways() == null || ways.get(i).getChangeSubways().size() == 0){
                m = (ways.get(i).getSubways().size() - 1) * 0.5;
                money.add("第1趟地铁共需要消费："+m);
                ways.get(i).setTotalMoney(m);
                ways.get(i).setMoneys(money);
            }else{
                int t = 0;
                for (int j = 1; j <= ways.get(i).getChangeSubways().size() + 1; j++){
                    int num = 1;
                    for (int z = t; z < ways.get(i).getSubways().size() - 1; z++){
                        if (ways.get(i).getSubways().get(z).getSubway().getS_sid().equals(ways.get(i).getSubways().get(z + 1).getSubway().getS_sid())){
                            num++;
                            t++;
                        }else {
                            t++;
                            break;
                        }
                    }
                    m = (num - 1) * 0.5;
                    totalmoney = totalmoney + m;
                    money.add("第"+j+"趟地铁共需要消费："+m);
                }
                ways.get(i).setTotalMoney(totalmoney);
                ways.get(i).setMoneys(money);
            }
        }

        //对地铁的时间进行处理
        List<String> time;
        //t1为坐上站的时间，t2为做的最后一站的时间
        String t1 = new String(),everyt = new String(),t2 = new String();
        int t11 = 0,everyt1 = 0,t22 = 0;
        int totaltime = 0,needtime = 0;
        int starttime1 = timeUtil.timehandleToInt(startTime);
        for (int i = 0; i < ways.size(); i++){
            time = new ArrayList<>();
            time.add("用户出发时间为："+startTime);
            if(ways.get(i).getChangeSubways() == null || ways.get(i).getChangeSubways().size() == 0){
                if(ways.get(i).getSubways().get(0).getSubway().getS_id() < ways.get(i).getSubways().get(ways.get(i).getSubways().size() - 1).getSubway().getS_id()){
                    t1 = ways.get(i).getSubways().get(0).getSubway().getS_seq_first_time();
                    everyt = ways.get(i).getSubways().get(0).getSubway().getS_pass_time();
                    t2 = ways.get(i).getSubways().get(ways.get(i).getSubways().size() - 1).getSubway().getS_seq_first_time();
                }else if(ways.get(i).getSubways().get(0).getSubway().getS_id() > ways.get(i).getSubways().get(ways.get(i).getSubways().size() - 1).getSubway().getS_id()){
                    t1 = ways.get(i).getSubways().get(0).getSubway().getS_back_first_time();
                    everyt = ways.get(i).getSubways().get(0).getSubway().getS_pass_time();
                    t2 = ways.get(i).getSubways().get(ways.get(i).getSubways().size() - 1).getSubway().getS_back_first_time();
                }else {
                    continue;
                }
                t11 = timeUtil.timehandleToInt(t1);
                t22 = timeUtil.timehandleToInt(t2);
                everyt1 = timeUtil.timehandleToInt(everyt);
                for (int n = 0; ; n++){
                    if (starttime1 <= t11){
                        needtime = t11 - starttime1;
                    }else if((t11 + n * everyt1) < starttime1 && (t11 + (n + 1) * everyt1) >= starttime1){
                        needtime = t11 + (n + 1) * everyt1 - starttime1;
                    }
                    time.add("需要等待地铁"+timeUtil.timehandleToString(needtime)+"时间");
                    time.add("坐第1趟地铁需要"+timeUtil.timehandleToString(t22 - t11)+"时间");
                    ways.get(i).setTimes(time);
                    ways.get(i).setTotalTime(needtime + t22 - t11);
                    break;
                }
            }else if (ways.get(i).getChangeSubways().size() >= 1){
                //获取换乘的编号
                int[] changes1 = new int[ways.get(i).getChangeSubways().size() * 2 + 2];
                changes1[0] = 0;
                starttime1 = timeUtil.timehandleToInt(startTime);
                changes1[ways.get(i).getChangeSubways().size() * 2 + 1] = ways.get(i).getSubways().size() - 1;
                int t = 1;
                for (int j = 1; j < ways.get(i).getSubways().size() - 1; j++){
                    if (ways.get(i).getSubways().get(j).getSubway().getS_place().equals(ways.get(i).getSubways().get(j + 1).getSubway().getS_place())){
                        changes1[t] = j;
                        changes1[t + 1] = j + 1;
                        t = t + 2;
                        if (t >= (changes1.length - 1)){
                            break;
                        }
                    }
                }

                //然后先判断车上顺序还是逆序，然后添加时间
                int z = 1;
                for (int j = 0; j < changes1.length - 1; j = j + 2){
                    //1.先判断顺序逆序
                    if(ways.get(i).getSubways().get(changes1[j]).getSubway().getS_id() > ways.get(i).getSubways().get(changes1[j + 1]).getSubway().getS_id()){
                        //逆序
                        t1 = ways.get(i).getSubways().get(changes1[j]).getSubway().getS_back_first_time();
                        t2 = ways.get(i).getSubways().get(changes1[j + 1]).getSubway().getS_back_first_time();
                        everyt = ways.get(i).getSubways().get(changes1[j]).getSubway().getS_pass_time();
                    }else if(ways.get(i).getSubways().get(changes1[j]).getSubway().getS_id() < ways.get(i).getSubways().get(changes1[j + 1]).getSubway().getS_id()){
                        //顺序
                        t1 = ways.get(i).getSubways().get(changes1[j]).getSubway().getS_seq_first_time();
                        t2 = ways.get(i).getSubways().get(changes1[j + 1]).getSubway().getS_seq_first_time();
                        everyt = ways.get(i).getSubways().get(changes1[j]).getSubway().getS_pass_time();
                    }
                    t11 = timeUtil.timehandleToInt(t1);
                    t22 = timeUtil.timehandleToInt(t2);
                    everyt1 = timeUtil.timehandleToInt(everyt);
                    //2.在换乘的地方考虑等待下一个地铁的时间
                    for (int n = 0; ; n++){
                        boolean f = false;
                        if (starttime1 <= t11){
                            f = true;
                        }else if((t11 + n * everyt1) < starttime1 && (t11 + (n + 1) * everyt1) >= starttime1){
                            t11 = t11 + (n + 1) * everyt1;
                            t22 = t22 + (n + 1) * everyt1;
                            f = true;
                        }
                        if (f) {
                            //等待地铁时间
                            needtime = t11 - starttime1;
                            if (needtime < 0){
                                break;
                            }
                            starttime1 = t22;
                            time.add("需要等待地铁"+timeUtil.timehandleToString(needtime)+"时间");
                            time.add("坐第"+z+"趟地铁需要"+timeUtil.timehandleToString(t22 - t11)+"时间");
                            z++;
                            break;
                        }
                    }
                    if ((j + 1) == (changes1.length - 1)){
                        totaltime = t22 - timeUtil.timehandleToInt(startTime);
                        ways.get(i).setTimes(time);
                        ways.get(i).setTotalTime(totaltime);
                    }

                    //需要考虑换乘需要的时间
                    for (int x = 0; x < ways.get(i).getChangeSubways().size(); x++){
                        if (ways.get(i).getChangeSubways().get(x).getC_place().equals(ways.get(i).getSubways().get(changes1[j + 1]).getSubway().getS_place()) && ways.get(i).getChangeSubways().get(x).getC_last_sid().equals(ways.get(i).getSubways().get(changes1[j + 1]).getSubway().getS_sid()) && ways.get(i).getChangeSubways().get(x).getC_now_sid().equals(ways.get(i).getSubways().get(changes1[j + 2]).getSubway().getS_sid())){
                            time.add("换乘地铁需要"+ways.get(i).getChangeSubways().get(x).getC_pass_time()+"时间");
                            starttime1 = starttime1 + timeUtil.timehandleToInt(ways.get(i).getChangeSubways().get(x).getC_pass_time());
                        }
                    }
                }
            }
        }
        log.info("对地铁换乘以后的路线返回");
        for (int i = 0; i < ways.size(); i++){
            for (int j = 0; j < ways.get(i).getSubways().size(); j++){
                ways.get(i).getSubways().get(j).setPrev(null);
                ways.get(i).getSubways().get(j).setNext(null);
                ways.get(i).getSubways().get(j).setOrderSetMap(null);
            }
        }

        return ways;
    }


}
