package com.sts.demodemo.service.impl;

import com.sts.demodemo.dao.StartEndPlaceMapper;
import com.sts.demodemo.entity.StartEndPlace;
import com.sts.demodemo.service.StartEndPlaceService;
import com.sts.demodemo.util.Graph;
import com.sts.demodemo.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("startEndPlaceService")
public class StartEndPlaceServiceImpl implements StartEndPlaceService {

    @Autowired
    private StartEndPlaceMapper startEndPlaceMapper;

    @Autowired
    private RedisUtil redisUtil;


    @Override
    public List<List<StartEndPlace>> findAllStartEndPlaceByP_startOrByP_end(String p_start,String p_end) {
        List<StartEndPlace> startEndPlaces;
        String key = "startEndPlaces_"+p_start+"_"+p_end;
        boolean hasKey = redisUtil.hasKey(key);
        if (hasKey){
            startEndPlaces = (List<StartEndPlace>) redisUtil.get(key);
        }else {
            StartEndPlace startEndPlace = new StartEndPlace();
            startEndPlace.setP_start(p_start);
            startEndPlace.setP_end(p_end);
            startEndPlaces = startEndPlaceMapper.findAllStartEndPlacesByP_startOrP_end(startEndPlace);
            redisUtil.set(key, startEndPlaces);
        }

        //将所有城市节点组成一个集合
        List<String> stringList = new ArrayList<>();
        stringList.add(p_start);
        stringList.add(p_end);
        for (StartEndPlace startEndPlace : startEndPlaces) {
            if (!stringList.contains(startEndPlace.getP_start())) {
                stringList.add(startEndPlace.getP_start());
            }else if (!stringList.contains(startEndPlace.getP_end())) {
                stringList.add(startEndPlace.getP_end());
            }
        }
        String[] vertex = new String[stringList.size()];
        vertex[0] = p_start;
        vertex[stringList.size() - 1] = p_end;
        for (int i = 1 ; i < stringList.size() - 1; i++) {
            vertex[i] = stringList.get(i + 1);
        }
        //将所有的城市节点组成邻接矩阵
        double[][] matrix = new double[stringList.size()][stringList.size()];
        for (int i = 0 ; i < stringList.size(); i++) {
            for (int j = 0; j < stringList.size(); j++) {
                for (StartEndPlace startEndPlace : startEndPlaces) {
                    if(startEndPlace.getP_start().equals(vertex[i]) && startEndPlace.getP_end().equals(vertex[j])) {
                        matrix[i][j] = 1;
                    }
                }
            }
        }
        Graph graph = new Graph(matrix, vertex);

        List<List<String>> paths  = graph.returnDFS(0,stringList.size() - 1);
        List<List<StartEndPlace>> lists = new ArrayList<>();
        for (List<String> stringList1 : paths) {
            List<StartEndPlace> startEndPlaceList = new ArrayList<>();
            for (int i = 0; i < stringList1.size() - 1; i++) {
                StartEndPlace startEndPlace = new StartEndPlace();
                startEndPlace.setP_start(stringList1.get(i));
                startEndPlace.setP_end(stringList1.get(i+1));
                startEndPlaceList.add(startEndPlace);
            }
            lists.add(startEndPlaceList);
        }
        return lists;
    }



}


//    List<StartEndPlace> startEndPlaces;
//    String key = "startEndPlaces";
//    boolean hasKey = redisUtil.hasKey(key);
//        if (hasKey){
//                startEndPlaces = (List<StartEndPlace>) redisUtil.get(key);
//        }else {
//        startEndPlaces = startEndPlaceMapper.findAllStartEndPlace();
//        redisUtil.set(key, startEndPlaces);
//        }
//
//        //将所有城市节点组成一个集合
//        List<String> stringList = new ArrayList<>();
//        stringList.add(p_start);
//        stringList.add(p_end);
//        for (StartEndPlace startEndPlace : startEndPlaces) {
//        if (!stringList.contains(startEndPlace.getP_start())) {
//        stringList.add(startEndPlace.getP_start());
//        }else if (!stringList.contains(startEndPlace.getP_end())) {
//        stringList.add(startEndPlace.getP_end());
//        }
//        }
//        String[] vertex = new String[stringList.size()];
//        vertex[0] = p_start;
//        vertex[stringList.size() - 1] = p_end;
//        for (int i = 1 ; i < stringList.size() - 1; i++) {
//        vertex[i] = stringList.get(i + 1);
//        }
//        //将所有的城市节点组成邻接矩阵
//        double[][] matrix = new double[stringList.size()][stringList.size()];
//        for (int i = 0 ; i < stringList.size(); i++) {
//        for (int j = 0; j < stringList.size(); j++) {
//        for (StartEndPlace startEndPlace : startEndPlaces) {
//        if(startEndPlace.getP_start().equals(vertex[i]) && startEndPlace.getP_end().equals(vertex[j])) {
//        matrix[i][j] = 1;
//        }
//        }
//        }
//        }
//        Graph graph = new Graph(matrix, vertex);
//
//        List<List<String>> paths  = graph.returnDFS(0,stringList.size() - 1);
//        List<List<StartEndPlace>> lists = new ArrayList<>();
//        for (List<String> stringList1 : paths) {
//        List<StartEndPlace> startEndPlaceList = new ArrayList<>();
//        for (int i = 0; i < stringList1.size() - 1; i++) {
//        StartEndPlace startEndPlace = new StartEndPlace();
//        startEndPlace.setP_start(stringList1.get(i));
//        startEndPlace.setP_end(stringList1.get(i+1));
//        startEndPlaceList.add(startEndPlace);
//        }
//        lists.add(startEndPlaceList);
//        }
//        System.out.println(lists);


//    List<StartEndPlace> startEndPlaces1 = startEndPlaceMapper.findAllStartEndPlaceByP_start(p_start);
//    List<StartEndPlace> startEndPlaces2 = startEndPlaceMapper.findAllStartEndPlaceByP_end(p_end);
//    List<List<StartEndPlace>> lists = new ArrayList<>();
//    List<StartEndPlace> list = null;
//    boolean flag = true;
//        for (StartEndPlace startEndPlace1 : startEndPlaces1){
//                for (StartEndPlace startEndPlace2 : startEndPlaces2){
//                if (startEndPlace1.getP_end().equals(startEndPlace2.getP_start())){
//                list = new ArrayList<>();
//        list.add(startEndPlace1);
//        list.add(startEndPlace2);
//        lists.add(list);
//        }else if (startEndPlace1.getP_start().equals(p_start) && startEndPlace1.getP_end().equals(p_end) && flag==true){
//        list = new ArrayList<>();
//        list.add(startEndPlace1);
//        lists.add(list);
//        flag = false;
//        }
//        }
//        }
