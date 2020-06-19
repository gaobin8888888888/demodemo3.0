package com.sts.demodemo.dao;

import com.sts.demodemo.entity.Traffic;

import java.util.List;

public interface TrafficMapper {

    //查找所有的路线交通工具搭配
    List<Traffic> findAllTraffic();

    List<Traffic> findAllTrafficByStartAndEnd(Traffic traffic);
}
