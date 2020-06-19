package com.sts.demodemo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Ways implements Cloneable, Serializable {

    private List<StartEndPlace> startEndPlaces;

    private List<Traffic> traffics;

    private List<SubwayInfo> subways;

    private List<ChangeSubway> changeSubways;

    private List<Motorcar> motorcars;

    private List<Plane> planes;

    private List<String> times;

    private List<String> moneys;

    private int totalTime;

    private double totalMoney;

    public Ways() {
    }

    public Ways(List<StartEndPlace> startEndPlaces, List<Traffic> traffics, List<SubwayInfo> subways, List<ChangeSubway> changeSubways, List<Motorcar> motorcars, List<Plane> planes, List<String> times , List<String> moneys, int totalTime, double totalMoney) {
        this.startEndPlaces = startEndPlaces;
        this.traffics = traffics;
        this.subways = subways;
        this.changeSubways = changeSubways;
        this.motorcars = motorcars;
        this.planes = planes;
        this.times = times;
        this.moneys = moneys;
        this.totalTime = totalTime;
        this.totalMoney = totalMoney;
    }

    public List<StartEndPlace> getStartEndPlaces() {
        return startEndPlaces;
    }

    public void setStartEndPlaces(List<StartEndPlace> startEndPlaces) {
        this.startEndPlaces = startEndPlaces;
    }

    public List<Traffic> getTraffics() {
        return traffics;
    }

    public void setTraffics(List<Traffic> traffics) {
        this.traffics = traffics;
    }

    public List<SubwayInfo> getSubways() {
        return subways;
    }

    public void setSubways(List<SubwayInfo> subways) {
        this.subways = subways;
    }

    public List<ChangeSubway> getChangeSubways() {
        return changeSubways;
    }

    public void setChangeSubways(List<ChangeSubway> changeSubways) {
        this.changeSubways = changeSubways;
    }

    public List<Motorcar> getMotorcars() {
        return motorcars;
    }

    public void setMotorcars(List<Motorcar> motorcars) {
        this.motorcars = motorcars;
    }

    public List<Plane> getPlanes() {
        return planes;
    }

    public void setPlanes(List<Plane> planes) {
        this.planes = planes;
    }

    public List<String> getTimes() {
        return times;
    }

    public void setTimes(List<String> times) {
        this.times = times;
    }

    public List<String> getMoneys() {
        return moneys;
    }

    public void setMoneys(List<String> moneys) {
        this.moneys = moneys;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    @Override
    public String toString() {
        return startEndPlaces+"   "+traffics+"   "+subways+"   "+ changeSubways +"    "+motorcars+"   "+planes+"   "+times+"   "+moneys+"   "+totalTime+"   "+totalMoney;
    }

    @Override
    public Ways clone() {
        Ways ways11 = null;
        try {
            ways11 = (Ways) super.clone();

            if (motorcars != null) {
                List<Motorcar> motorcarList = new ArrayList<>();
                for (int i = 0; i < motorcars.size(); i++) {
                    motorcarList.add((Motorcar) motorcars.get(i).clone());
                }
                ways11.setMotorcars(motorcarList);
            }

            if (planes != null) {
                List<Plane> planeList = new ArrayList<>();
                for (int i = 0; i < planes.size(); i++) {
                    planeList.add((Plane) planes.get(i).clone());
                }
                ways11.setPlanes(planeList);
            }


            if (times != null) {
                List<String> timeList = new ArrayList<>();
                for (int i = 0; i < times.size(); i++) {
                    timeList.add(times.get(i));
                }
                ways11.setTimes(timeList);
            }

            if (moneys != null) {
                List<String> moneyList = new ArrayList<>();
                for (int i = 0; i < moneys.size(); i++) {
                    moneyList.add(moneys.get(i));
                }
                ways11.setMoneys(moneyList);
            }

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return ways11;
    }
}
