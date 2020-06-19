package com.sts.demodemo.entity;

import java.io.Serializable;
import java.util.Objects;

public class StartEndPlace implements Cloneable, Serializable {

    private int p_id;
    private String p_start;
    private String p_end;

    public StartEndPlace(){
    }


    public StartEndPlace(int p_id, String p_start, String p_end) {
        this.p_id = p_id;
        this.p_start = p_start;
        this.p_end = p_end;
    }

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public String getP_start() {
        return p_start;
    }

    public void setP_start(String p_start) {
        this.p_start = p_start;
    }

    public String getP_end() {
        return p_end;
    }

    public void setP_end(String p_end) {
        this.p_end = p_end;
    }

    @Override
    public String toString() {
        return p_id+" "+p_start+" "+p_end;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        StartEndPlace startEndPlace = null;
        startEndPlace = (StartEndPlace) super.clone();
        return startEndPlace;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StartEndPlace that = (StartEndPlace) o;
        return p_id == that.p_id &&
                Objects.equals(p_start, that.p_start) &&
                Objects.equals(p_end, that.p_end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(p_id, p_start, p_end);
    }
}
