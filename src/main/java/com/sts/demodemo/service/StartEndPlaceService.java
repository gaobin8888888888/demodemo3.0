package com.sts.demodemo.service;

import com.sts.demodemo.entity.StartEndPlace;

import java.util.List;
import java.util.Set;

public interface StartEndPlaceService {

    //根据起始城市和末城市实现城市之间的搭配
    List<List<StartEndPlace>> findAllStartEndPlaceByP_startOrByP_end(String p_start,String p_end);

}
