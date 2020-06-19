package com.sts.demodemo.entity;

import java.io.Serializable;

public class Plane implements Cloneable, Serializable {

    private Integer l_id;
    private Integer l_place_id;
    private String l_company;
    private String l_model;
    private String l_start_place;
    private String l_start_time;
    private String l_end_place;
    private String l_end_time;
    private String l_pass_time;
    private double l_ontime_rate;
    private double l_price;
    private double l_sale;

    public Plane(){
    }

    public Plane(Integer l_id, Integer l_place_id, String l_company, String l_model, String l_start_place, String l_start_time, String l_end_place, String l_end_time, String l_pass_time, double l_ontime_rate, double l_price, double l_sale) {
        this.l_id = l_id;
        this.l_place_id = l_place_id;
        this.l_company = l_company;
        this.l_model = l_model;
        this.l_start_place = l_start_place;
        this.l_start_time = l_start_time;
        this.l_end_place = l_end_place;
        this.l_end_time = l_end_time;
        this.l_pass_time = l_pass_time;
        this.l_ontime_rate = l_ontime_rate;
        this.l_price = l_price;
        this.l_sale = l_sale;
    }

    public Integer getL_id() {
        return l_id;
    }

    public void setL_id(Integer l_id) {
        this.l_id = l_id;
    }

    public Integer getL_place_id() {
        return l_place_id;
    }

    public void setL_place_id(Integer l_place_id) {
        this.l_place_id = l_place_id;
    }

    public String getL_company() {
        return l_company;
    }

    public void setL_company(String l_company) {
        this.l_company = l_company;
    }

    public String getL_model() {
        return l_model;
    }

    public void setL_model(String l_model) {
        this.l_model = l_model;
    }

    public String getL_start_place() {
        return l_start_place;
    }

    public void setL_start_place(String l_start_place) {
        this.l_start_place = l_start_place;
    }

    public String getL_start_time() {
        return l_start_time;
    }

    public void setL_start_time(String l_start_time) {
        this.l_start_time = l_start_time;
    }

    public String getL_end_place() {
        return l_end_place;
    }

    public void setL_end_place(String l_end_place) {
        this.l_end_place = l_end_place;
    }

    public String getL_end_time() {
        return l_end_time;
    }

    public void setL_end_time(String l_end_time) {
        this.l_end_time = l_end_time;
    }

    public String getL_pass_time() {
        return l_pass_time;
    }

    public void setL_pass_time(String l_pass_time) {
        this.l_pass_time = l_pass_time;
    }

    public double getL_ontime_rate() {
        return l_ontime_rate;
    }

    public void setL_ontime_rate(double l_ontime_rate) {
        this.l_ontime_rate = l_ontime_rate;
    }

    public double getL_price() {
        return l_price;
    }

    public void setL_price(double l_price) {
        this.l_price = l_price;
    }

    public double getL_sale() {
        return l_sale;
    }

    public void setL_sale(double l_sale) {
        this.l_sale = l_sale;
    }

    @Override
    public String toString() {
        return l_id+" "+l_place_id+" "+l_company+" "+l_model+" "+l_start_place+" "+l_start_time+" "+l_end_place+" "+l_end_time+" "+l_pass_time+" "+l_ontime_rate+" "+l_price+" "+l_sale;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Plane plane = null;
        plane = (Plane)super.clone();
        return plane;
    }
}
