package com.sts.demodemo.entity;

import java.io.Serializable;

public class Motorcar implements Cloneable, Serializable {

    private Integer m_id;
    private Integer m_place_id;
    private String m_start_place;
    private String m_start_time;
    private String m_end_place;
    private String m_end_time;
    private String m_pass_time;
    private double m_first_rate_price;
    private double m_second_rate_price;
    private double m_business_rate_price;

    public Motorcar(){
    }

    public Motorcar(Integer m_id, Integer m_place_id, String m_start_place, String m_start_time, String m_end_place, String m_end_time, String m_pass_time, double m_first_rate_price, double m_second_rate_price, double m_business_rate_price) {
        this.m_id = m_id;
        this.m_place_id = m_place_id;
        this.m_start_place = m_start_place;
        this.m_start_time = m_start_time;
        this.m_end_place = m_end_place;
        this.m_end_time = m_end_time;
        this.m_pass_time = m_pass_time;
        this.m_first_rate_price = m_first_rate_price;
        this.m_second_rate_price = m_second_rate_price;
        this.m_business_rate_price = m_business_rate_price;
    }

    public Integer getM_id() {
        return m_id;
    }

    public void setM_id(Integer m_id) {
        this.m_id = m_id;
    }

    public Integer getM_place_id() {
        return m_place_id;
    }

    public void setM_place_id(Integer m_place_id) {
        this.m_place_id = m_place_id;
    }

    public String getM_start_place() {
        return m_start_place;
    }

    public void setM_start_place(String m_start_place) {
        this.m_start_place = m_start_place;
    }

    public String getM_start_time() {
        return m_start_time;
    }

    public void setM_start_time(String m_start_time) {
        this.m_start_time = m_start_time;
    }

    public String getM_end_place() {
        return m_end_place;
    }

    public void setM_end_place(String m_end_place) {
        this.m_end_place = m_end_place;
    }

    public String getM_end_time() {
        return m_end_time;
    }

    public void setM_end_time(String m_end_time) {
        this.m_end_time = m_end_time;
    }

    public String getM_pass_time() {
        return m_pass_time;
    }

    public void setM_pass_time(String m_pass_time) {
        this.m_pass_time = m_pass_time;
    }

    public double getM_first_rate_price() {
        return m_first_rate_price;
    }

    public void setM_first_rate_price(double m_first_rate_price) {
        this.m_first_rate_price = m_first_rate_price;
    }

    public double getM_second_rate_price() {
        return m_second_rate_price;
    }

    public void setM_second_rate_price(double m_second_rate_price) {
        this.m_second_rate_price = m_second_rate_price;
    }

    public double getM_business_rate_price() {
        return m_business_rate_price;
    }

    public void setM_business_rate_price(double m_business_rate_price) {
        this.m_business_rate_price = m_business_rate_price;
    }

    @Override
    public String toString() {
        return m_id+" "+m_place_id+" "+m_start_place+" "+m_start_time+" "+m_end_place+" "+m_end_time+" "+m_pass_time+" "+m_first_rate_price+" "+m_second_rate_price+" "+m_business_rate_price;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Motorcar motorcar = null;
        motorcar = (Motorcar) super.clone();
        return motorcar;
    }
}
