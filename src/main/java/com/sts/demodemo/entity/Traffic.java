package com.sts.demodemo.entity;

import java.io.Serializable;

public class Traffic implements Cloneable, Serializable {
    private Integer t_id;
    private Integer t_p_id;
    private String t_method;
    private String t_method1;
    private String t_method2;
    private String t_start_place;
    private String t_end_place;

    public Traffic() {
    }
    public Traffic(Integer t_id, Integer t_p_id, String t_method, String t_method1, String t_method2, String t_start_place, String t_end_place) {
        this.t_id = t_id;
        this.t_p_id = t_p_id;
        this.t_method = t_method;
        this.t_method1 = t_method1;
        this.t_method2 = t_method2;
        this.t_start_place = t_start_place;
        this.t_end_place = t_end_place;
    }

    public Integer getT_id() {
        return t_id;
    }

    public void setT_id(Integer t_id) {
        this.t_id = t_id;
    }

    public Integer getT_p_id() {
        return t_p_id;
    }

    public void setT_p_id(Integer t_p_id) {
        this.t_p_id = t_p_id;
    }

    public String getT_method() {
        return t_method;
    }

    public void setT_method(String t_method) {
        this.t_method = t_method;
    }

    public String getT_start_place() {
        return t_start_place;
    }

    public void setT_start_place(String t_start_place) {
        this.t_start_place = t_start_place;
    }

    public String getT_end_place() {
        return t_end_place;
    }

    public void setT_end_place(String t_end_place) {
        this.t_end_place = t_end_place;
    }

    public String getT_method1() {
        return t_method1;
    }

    public void setT_method1(String t_method1) {
        this.t_method1 = t_method1;
    }

    public String getT_method2() {
        return t_method2;
    }

    public void setT_method2(String t_method2) {
        this.t_method2 = t_method2;
    }

    @Override
    public String toString() {
        return t_id+" "+t_p_id+" "+t_method+" "+t_method1+" "+t_method2+" "+t_start_place+" "+t_end_place;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Traffic traffic = null;
        traffic = (Traffic) super.clone();
        return traffic;
    }
}
