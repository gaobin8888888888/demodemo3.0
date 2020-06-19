package com.sts.demodemo.service;

import com.sts.demodemo.entity.Subway;
import com.sts.demodemo.entity.SubwayInfo;

import java.util.List;

public interface SubwayService {

    List<SubwayInfo> findNeedSubway(String startPlaceCity, String startPlace , String startPlace1);

    List<Subway> findStartSubwayByDim(String startPlace);

}
