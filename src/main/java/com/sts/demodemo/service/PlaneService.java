package com.sts.demodemo.service;

import com.sts.demodemo.entity.Plane;
import com.sts.demodemo.entity.Ways;

import java.util.List;

public interface PlaneService {

    List<Ways> findAllNeedPlane(Ways ways, String startTime, int p);
}
