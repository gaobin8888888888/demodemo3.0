package com.sts.demodemo.service;

import com.sts.demodemo.entity.Ways;

import java.util.List;

public interface MotorcarService {

    List<Ways> findAllNeedMotorcar(Ways ways, String startTime, int j);

}
