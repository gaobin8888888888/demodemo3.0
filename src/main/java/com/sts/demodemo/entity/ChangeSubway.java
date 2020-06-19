package com.sts.demodemo.entity;

import java.io.Serializable;
import java.util.Objects;

public class ChangeSubway implements Cloneable, Serializable {

    private String c_place;
    private String c_last_sid;
    private String c_now_sid;
    private String c_pass_time;

    public ChangeSubway(){
    }

    public ChangeSubway(String c_place, String c_last_sid, String c_now_sid, String c_pass_time) {
        this.c_place = c_place;
        this.c_last_sid = c_last_sid;
        this.c_now_sid = c_now_sid;
        this.c_pass_time = c_pass_time;
    }

    public String getC_place() {
        return c_place;
    }

    public void setC_place(String c_place) {
        this.c_place = c_place;
    }

    public String getC_last_sid() {
        return c_last_sid;
    }

    public void setC_last_sid(String c_last_sid) {
        this.c_last_sid = c_last_sid;
    }

    public String getC_now_sid() {
        return c_now_sid;
    }

    public void setC_now_sid(String c_now_sid) {
        this.c_now_sid = c_now_sid;
    }

    public String getC_pass_time() {
        return c_pass_time;
    }

    public void setC_pass_time(String c_pass_time) {
        this.c_pass_time = c_pass_time;
    }

    @Override
    public String toString() {
        return c_place+" "+c_last_sid+" "+c_now_sid+" "+c_pass_time;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChangeSubway that = (ChangeSubway) o;
        return Objects.equals(c_place, that.c_place) &&
                Objects.equals(c_last_sid, that.c_last_sid) &&
                Objects.equals(c_now_sid, that.c_now_sid) &&
                Objects.equals(c_pass_time, that.c_pass_time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(c_place, c_last_sid, c_now_sid, c_pass_time);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        ChangeSubway changeSubway = null;
        changeSubway = (ChangeSubway) super.clone();
        return changeSubway;
    }
}
