package com.sts.demodemo.dao;

import com.sts.demodemo.entity.Motorcar;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface MotorcarMapper {

    //查找所有高铁信息
    List<Motorcar> findAllMotorcar();

    //根据城市路线查找可以乘坐的高铁信息
    List<Motorcar> findAllMotorcarByM_place_id(Integer m_place_id);

}
