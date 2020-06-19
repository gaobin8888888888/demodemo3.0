package com.sts.demodemo.dao;

import com.sts.demodemo.entity.ChangeSubway;

import java.util.List;
import java.util.Set;

public interface ChangeSubwayMapper {

    //查询所有地铁换乘
    List<ChangeSubway> findAllChange();

    //根据换乘地点查找地铁换乘
    List<ChangeSubway> findAllChangeByPlace(ChangeSubway changeSubway);

    //根据换乘地点、换乘站点和要换乘的站点查找地铁换乘
    ChangeSubway findAllChangeByLastAndNowAndPlace(ChangeSubway changeSubway);

    Set<ChangeSubway> findAllChangeByStartPlace(String str);

}
