package com.sts.demodemo.service.impl;

import com.sts.demodemo.controller.WayController;
import com.sts.demodemo.dao.SubwayMapper;
import com.sts.demodemo.entity.Subway;
import com.sts.demodemo.entity.SubwayInfo;
import com.sts.demodemo.service.SubwayService;
import com.sts.demodemo.util.RedisUtil;
import com.sts.demodemo.util.SubwayGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SubwayServiceImpl implements SubwayService {

    @Autowired
    private SubwayMapper subwayMapper;


    @Autowired
    private RedisUtil redisUtil;

    private Logger log = LoggerFactory.getLogger(SubwayServiceImpl.class);

    @Override
    public List<SubwayInfo> findNeedSubway(String startPlaceCity, String startPlace, String endPlace) {

        Set<List<SubwayInfo>> lineSet = new HashSet<>();//所有线集合

        int totalStaion = 0;//总的站点数量

        //存放所有地铁
        List<Subway> subways;
        String key = "subways_"+startPlaceCity;
        boolean fl = redisUtil.hasKey(key);
        if (fl){
            subways = (List<Subway>) redisUtil.get(key);
        }else{
            //查找所在城市的地铁信息
            subways = subwayMapper.findAllSubwaysByS_belong_place(startPlaceCity);
            redisUtil.set(key, subways);
        }


        //存放该城市所有的地铁
        List<SubwayInfo> subwayInfos = new ArrayList<>();
        SubwayInfo info;

        //将Subway值赋给SubwayInfo
        for (int i = 0; i < subways.size(); i++){
            info = new SubwayInfo();
            info.setSubway(subways.get(i));
            subwayInfos.add(info);
        }

        //计算所在城市地铁线路的数量
        int m = 1;
        for (int i = 0; i < subwayInfos.size() - 1; i++){
            if (subwayInfos.get(i).getSubway().getS_sid().equals(subwayInfos.get(i + 1).getSubway().getS_sid())){
                continue;
            }else {
                m++;
            }
        }

        //按地铁线路号存放地铁信息
        List<SubwayInfo> infos;
        int t = 0;
        for (int i = 0; i < m; i++){
            infos = new ArrayList<>();
            boolean flag = true;
            for (int j = t; j < subwayInfos.size() - 1; j++){
                if (!subwayInfos.get(j).getSubway().getS_sid().equals(subwayInfos.get(j + 1).getSubway().getS_sid())){
                    flag = false;
                }
                infos.add(subwayInfos.get(j));
                t++;
                if (t == subwayInfos.size() - 1){
                    infos.add(subwayInfos.get(t));
                }
                if (flag == false){
                    break;
                }
            }
            for (int z = 0; z < infos.size(); z++){
                if (z < infos.size() - 1){
                    infos.get(z).setNext(infos.get(z + 1));
                    infos.get(z + 1).setPrev(infos.get(z));
                }
            }
            lineSet.add(infos);
        }

        totalStaion = totalStaion + subwayInfos.size();

        //存放初始站点和末站点
        SubwayInfo startPlaceSubway = new SubwayInfo();
        SubwayInfo endPlaceSubway = new SubwayInfo();

        //找到地铁初始点和末地址得具体站点信息
        for (int i = 0; i < subwayInfos.size(); i++) {
            if (subwayInfos.get(i).getSubway().getS_place().equals(startPlace)) {
                startPlaceSubway = subwayInfos.get(i);
            }
            if (subwayInfos.get(i).getSubway().getS_place().equals(endPlace)) {
                endPlaceSubway = subwayInfos.get(i);
            }
        }



        SubwayGraph sw = new SubwayGraph();
        List<SubwayInfo> needWubways = sw.returnSubways(startPlaceSubway, endPlaceSubway, totalStaion, lineSet);
        for (int i = 0; i < needWubways.size() - 1; i++){
            if (!needWubways.get(i).getSubway().getS_sid().equals(needWubways.get(i + 1).getSubway().getS_sid())){
                for (int j = 0; j < subwayInfos.size(); j++){
                    if (subwayInfos.get(j).getSubway().getS_sid().equals(needWubways.get(i + 1).getSubway().getS_sid()) && subwayInfos.get(j).getSubway().getS_place().equals(needWubways.get(i).getSubway().getS_place())){
                        needWubways.add(i + 1, subwayInfos.get(j));
                    }
                }
            }
        }

        if (needWubways.size() >= 2){
            if (needWubways.get(0).getSubway().getS_place().equals(needWubways.get(1).getSubway().getS_place()) && !(needWubways.get(0).getSubway().getS_sid().equals(needWubways.get(1).getSubway().getS_sid()))){
                needWubways.remove(0);
            }
        }
        log.info("对处理好的地铁路线进行返回");
        return needWubways;
    }

    //根据起始站点进行模糊查询
    @Override
    public List<Subway> findStartSubwayByDim(String startPlace) {
        long s = System.currentTimeMillis();
        //先查一样的
        Set<Subway> subwaySet1 = new HashSet<>();
        List<Subway> list = new ArrayList<>();
        subwaySet1.addAll(subwayMapper.findSubwayByS_place(startPlace));
        list.addAll(subwaySet1);

        //在查开头一样的
        Set<Subway> subwaySet2 = new HashSet<>();
        startPlace = startPlace+"%";
        subwaySet2.addAll(subwayMapper.findStartSubwayByDim(startPlace));
        subwaySet2.removeAll(subwaySet1);
        list.addAll(subwaySet2);

        //最后模糊查询
        Set<Subway> subwaySet3 = new HashSet<>();
        startPlace = "%"+startPlace;
        subwaySet3.addAll(subwayMapper.findStartSubwayByDim(startPlace));
        subwaySet3.removeAll(subwaySet1);
        subwaySet3.removeAll(subwaySet2);
        list.addAll(subwaySet3);
        long e = System.currentTimeMillis();
        log.info("起始地点模糊查询花费的时间为"+(e - s));
        return list;
    }

}
