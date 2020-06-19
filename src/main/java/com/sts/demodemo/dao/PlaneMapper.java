package com.sts.demodemo.dao;

import com.sts.demodemo.entity.Plane;

import java.util.List;

public interface PlaneMapper {

    //查找所有飞机信息
    List<Plane> findAllPlane();

    List<Plane> findAllPlaneByL_place_id(Integer l_place_id);

}
