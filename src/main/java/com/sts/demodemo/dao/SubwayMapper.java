package com.sts.demodemo.dao;

import com.sts.demodemo.entity.Subway;

import java.util.List;
import java.util.Set;

public interface SubwayMapper {

    //查找所有地铁信息
    List<Subway> findAllSubway();

    //根据地铁线路查找地铁信息
    List<Subway> findAllSubwayByS_sid();

    //根据起始站点查找该站点所在的城市
    Set<Subway> findSubwayByS_place(String startPlace);

    //根据起始站点模糊查找
    Set<Subway> findStartSubwayByDim(String startPlace);

    //根据城市找到该城市的所有站点
    List<Subway> findAllSubwaysByS_belong_place(String s_belong_place);

}
