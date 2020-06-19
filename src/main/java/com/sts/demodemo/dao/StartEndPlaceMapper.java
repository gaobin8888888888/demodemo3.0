package com.sts.demodemo.dao;

import com.sts.demodemo.entity.StartEndPlace;

import java.util.List;

public interface StartEndPlaceMapper {

    //根据起始点查找所在的城市
    StartEndPlace findOneStartEndPlace(StartEndPlace startEndPlace);

    //查找所有城市路线
    List<StartEndPlace> findAllStartEndPlace();

    //根据起始点查找城市路线
    List<StartEndPlace> findAllStartEndPlaceByP_start(String p_start);

    //根据末地址查找城市路线
    List<StartEndPlace> findAllStartEndPlaceByP_end(String p_end);

    List<StartEndPlace> findAllStartEndPlacesByP_startOrP_end(StartEndPlace startEndPlace);

}
