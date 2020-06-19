package com.sts.demodemo.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;

public class Subway implements Cloneable, Serializable {

    private Integer s_id;
    private String s_sid;
    private String s_place;
    private String s_seq_first_time;
    private String s_back_first_time;
    private String s_pass_time;
    private String s_belong_place;

    public Subway(){
    }

    public Subway(Integer s_id, String s_sid, String s_place, String s_seq_first_time, String s_back_first_time,String s_pass_time,String s_belong_place) {
        this.s_id = s_id;
        this.s_sid = s_sid;
        this.s_place = s_place;
        this.s_seq_first_time = s_seq_first_time;
        this.s_back_first_time = s_back_first_time;
        this.s_pass_time = s_pass_time;
        this.s_belong_place = s_belong_place;

    }

    public Integer getS_id() {
        return s_id;
    }

    public void setS_id(Integer s_id) {
        this.s_id = s_id;
    }

    public String getS_sid() {
        return s_sid;
    }

    public void setS_sid(String s_sid) {
        this.s_sid = s_sid;
    }

    public String getS_place() {
        return s_place;
    }

    public void setS_place(String s_place) {
        this.s_place = s_place;
    }

    public String getS_seq_first_time() {
        return s_seq_first_time;
    }

    public void setS_seq_first_time(String s_seq_first_time) {
        this.s_seq_first_time = s_seq_first_time;
    }

    public String getS_back_first_time() {
        return s_back_first_time;
    }

    public void setS_back_first_time(String s_back_first_time) {
        this.s_back_first_time = s_back_first_time;
    }

    public String getS_pass_time() {
        return s_pass_time;
    }

    public void setS_pass_time(String s_pass_time) {
        this.s_pass_time = s_pass_time;
    }

    public String getS_belong_place() {
        return s_belong_place;
    }

    public void setS_belong_place(String s_belong_place) {
        this.s_belong_place = s_belong_place;
    }

    @Override
    public String toString() {
        return s_id+" "+s_sid+" "+s_place+" "+s_seq_first_time+" "+s_back_first_time+" "+s_pass_time+" "+s_belong_place;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subway subway = (Subway) o;
        return Objects.equals(s_place, subway.s_place);
    }

    @Override
    public int hashCode() {
        return Objects.hash(s_place);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Subway subway = null;
        subway = (Subway) super.clone();
        return subway;
    }
}
