package com.sts.demodemo.util;

import com.sts.demodemo.entity.Subway;
import com.sts.demodemo.entity.SubwayInfo;
import com.sts.demodemo.service.impl.SubwayServiceImpl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class SubwayGraph {

    private List<SubwayInfo> outList = new ArrayList<>();//记录已经分析过的站点

    private List<SubwayInfo> needWubways = new ArrayList<>();

    //计算从s1站到s2站的最短经过路径
    public void calculate(SubwayInfo s1, SubwayInfo s2, int totalStaion, Set<List<SubwayInfo>> lineSet){
        if(outList.size() == totalStaion){
            for(SubwayInfo subway : s1.getAllPassedStations(s2)){
                needWubways.add(subway);
            }
            return;
        }
        if(!outList.contains(s1)){
            outList.add(s1);
        }

        //如果起点站的OrderSetMap为空，则第一次用起点站的前后站点初始化之
        if(s1.getOrderSetMap().isEmpty()){
            List<SubwayInfo> Linkedstations = getAllLinkedStations(s1,lineSet);
            for(SubwayInfo s : Linkedstations){
                s1.getAllPassedStations(s).add(s);
            }
        }

        SubwayInfo parent = getShortestPath(s1);//获取距离起点站s1最近的一个站（有多个的话，随意取一个）
        if(parent == s2){
            for(SubwayInfo subway : s1.getAllPassedStations(s2)){
                needWubways.add(subway);
            }
            return;
        }
        for(SubwayInfo child : getAllLinkedStations(parent,lineSet)){
            if(outList.contains(child)){
                continue;
            }

            int shortestPath = (s1.getAllPassedStations(parent).size()-1) + 1;//前面这个1表示计算路径需要去除自身站点，后面这个1表示增加了1站距离
            if(s1.getAllPassedStations(child).contains(child)){

                //如果s1已经计算过到此child的经过距离，那么比较出最小的距离
                if((s1.getAllPassedStations(child).size()-1) > shortestPath){

                    //重置S1到周围各站的最小路径
                    s1.getAllPassedStations(child).clear();
                    s1.getAllPassedStations(child).addAll(s1.getAllPassedStations(parent));
                    s1.getAllPassedStations(child).add(child);
                }
            } else {
                //如果s1还没有计算过到此child的经过距离
                s1.getAllPassedStations(child).addAll(s1.getAllPassedStations(parent));

                s1.getAllPassedStations(child).add(child);
            }
        }
        outList.add(parent);
        calculate(s1,s2,totalStaion,lineSet);//重复计算，往外面站点扩展
    }



    //取参数station到各个站的最短距离，相隔1站，距离为1，依次类推
    private SubwayInfo getShortestPath(SubwayInfo subway){
        int minPatn = Integer.MAX_VALUE;
        SubwayInfo rets = null;
        for(SubwayInfo s :subway.getOrderSetMap().keySet()){
            if(outList.contains(s)){
                continue;
            }
            LinkedHashSet<SubwayInfo> set  = subway.getAllPassedStations(s);//参数station到s所经过的所有站点的集合
            if(set.size() < minPatn){
                minPatn = set.size();
                rets = s;
            }
        }
        return rets;
    }

    //获取参数station直接相连的所有站，包括交叉线上面的站
    private List<SubwayInfo> getAllLinkedStations(SubwayInfo subway,Set<List<SubwayInfo>> lineSet){
        List<SubwayInfo> linkedStaions = new ArrayList<>();
        for(List<SubwayInfo> line : lineSet){
            if(line.contains(subway)){//如果某一条线包含了此站，注意由于重写了hashcode方法，只有name相同，即认为是同一个对象
                SubwayInfo s = line.get(line.indexOf(subway));
                if(s.getPrev() != null){
                    linkedStaions.add(s.getPrev());
                }
                if(s.getNext() != null){
                    linkedStaions.add(s.getNext());
                }
            }
        }
        return linkedStaions;
    }


    public List<SubwayInfo> returnSubways(SubwayInfo s1, SubwayInfo s2, int totalStaion, Set<List<SubwayInfo>> lineSet) {
        calculate(s1, s2, totalStaion, lineSet);
        return needWubways;
    }
}
