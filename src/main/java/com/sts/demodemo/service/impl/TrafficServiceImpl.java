package com.sts.demodemo.service.impl;

import com.sts.demodemo.dao.TrafficMapper;
import com.sts.demodemo.entity.Traffic;
import com.sts.demodemo.service.TrafficService;
import com.sts.demodemo.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("trafficService")
public class TrafficServiceImpl implements TrafficService {

    @Autowired
    private TrafficMapper trafficMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public List<Traffic> findAllNeedTraffic(String t_start_place, String t_end_place) {
        List<Traffic> trafficList;
        String key = "trafficList_"+t_start_place+"_"+t_end_place;
        boolean hasKey = redisUtil.hasKey(key);
        if (hasKey){
            trafficList = (List<Traffic>) redisUtil.get(key);
        }else {
            Traffic traffic = new Traffic();
            traffic.setT_start_place(t_start_place);
            traffic.setT_end_place(t_end_place);
            trafficList = trafficMapper.findAllTrafficByStartAndEnd(traffic);
            redisUtil.set(key, trafficList);
        }

        return trafficList;
    }
}
