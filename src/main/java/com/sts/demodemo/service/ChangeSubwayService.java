package com.sts.demodemo.service;

import com.sts.demodemo.entity.ChangeSubway;
import com.sts.demodemo.entity.Ways;

import java.util.List;

public interface ChangeSubwayService {

    List<Ways> findNeedAllChange(List<Ways> ways, String startTime);
}
