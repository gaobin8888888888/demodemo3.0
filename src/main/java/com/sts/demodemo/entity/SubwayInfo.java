package com.sts.demodemo.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;

public class SubwayInfo implements Cloneable, Serializable {

    private Subway subway;

    private SubwayInfo prev;

    private SubwayInfo next;

    //本站到某一个目标站(key)所经过的所有站集合(value)，保持前后顺序
    private Map<SubwayInfo, LinkedHashSet<SubwayInfo>> orderSetMap = new HashMap<>();


    public SubwayInfo(){
        orderSetMap = new HashMap<>();
    }

    public Subway getSubway() {
        return subway;
    }

    public void setSubway(Subway subway) {
        this.subway = subway;
    }

    public SubwayInfo getPrev() {
        return prev;
    }

    public void setPrev(SubwayInfo prev) {
        this.prev = prev;
    }

    public SubwayInfo getNext() {
        return next;
    }

    public void setNext(SubwayInfo next) {
        this.next = next;
    }

    public LinkedHashSet<SubwayInfo> getAllPassedStations(SubwayInfo subwayInfo) {
        if(orderSetMap.get(subwayInfo) == null){
            LinkedHashSet<SubwayInfo> set = new LinkedHashSet<>();
            set.add(this);
            orderSetMap.put(subwayInfo, set);
        }
        return orderSetMap.get(subwayInfo);
    }

    public Map<SubwayInfo, LinkedHashSet<SubwayInfo>> getOrderSetMap() {
        return orderSetMap;
    }

    public void setOrderSetMap(Map<SubwayInfo, LinkedHashSet<SubwayInfo>> orderSetMap) {
        this.orderSetMap = orderSetMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubwayInfo that = (SubwayInfo) o;
        return Objects.equals(subway.getS_place(), that.subway.getS_place());
    }

    @Override
    public int hashCode() {
        return Objects.hash(subway.getS_place());
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
