package com.sts.demodemo.service;

import com.sts.demodemo.entity.Traffic;

import java.util.List;

public interface TrafficService {

    List<Traffic> findAllNeedTraffic(String t_start_place, String t_end_place);
}
