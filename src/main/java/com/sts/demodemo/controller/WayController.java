package com.sts.demodemo.controller;

import com.sts.demodemo.entity.*;
import com.sts.demodemo.service.*;
import com.sts.demodemo.util.SelectPathUtil;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;


@RestController
public class WayController {

    @Autowired
    private StartEndPlaceService startEndPlaceService;

    @Autowired
    private SubwayService subwayService;

    @Autowired
    private TrafficService trafficService;

    @Autowired
    private ChangeSubwayService changeSubwayService;

    @Autowired
    private PlaneService planeService;

    @Autowired
    private MotorcarService motorcarService;

    @Autowired
    private SelectPathUtil selectPathUtil;


    private Logger log = LoggerFactory.getLogger(WayController.class);

    /**
     * @param startPlace
     * @return List<Subway>
     */
    @CrossOrigin
    @RequestMapping(value = "/request")
    public @ResponseBody List<Subway> chooseStart(@RequestParam("startPlace")String startPlace){
        log.info("准备开始起始地点模糊查询");
        return subwayService.findStartSubwayByDim(startPlace);
    }


    /**
     *
     * @param startPlace
     * @param startPlaceCity
     * @param endPlace
     * @param startTime
     * @return List<List<Ways>>
     */
    @CrossOrigin
    @RequestMapping(value = "/recommend")
    public @ResponseBody List<List<Ways>> recommend(@RequestParam("startPlace")String startPlace, @RequestParam("startPlaceCity")String startPlaceCity , @RequestParam("endPlace")String endPlace, @RequestParam("startTime")String startTime){
        long start = System.currentTimeMillis();

        List<List<Ways>>  listList = new ArrayList<>();
        //判断传回的初始地城市是否为空，如果为空直接返回空集合，如果不为空则在进行下一步
        log.info("先判断初始初始是否为空，如果为空直接返回空集合，如果不为空则在进行下一步");
        if(startPlaceCity == null){
            return listList;
        }

        log.info("前端传到后端的值为"+startPlace+" "+startPlaceCity+" "+endPlace+" "+startTime);

        //获取起点城市到目的城市的所有路径中城市周转小于等于三个的路径
        log.info("准备获取起点城市到目的城市的所有路径中城市周转小于等于三个的路径");
        List<List<StartEndPlace>> startEndPlaces = startEndPlaceService.findAllStartEndPlaceByP_startOrByP_end(startPlaceCity,endPlace);

        //每条路线集合
        Ways ways1;

        //所有路线集合
        List<Ways> waysList = new ArrayList<>();

        List<Ways> waysList1 = new ArrayList<>();

        List<Ways> waysList2 = new ArrayList<>();

        //一条条路径理清
        List<Ways> ways = new ArrayList<>();

        List<StartEndPlace> startEndPlacess ;


        List<Traffic> trafficList1;

        //开始遍历，先找出每条路径做高铁或者飞机的起点和末地址
        log.info("开始遍历，先找出每条路径做高铁或者飞机的起点和末地址");
        for (int i = 0; i < startEndPlaces.size(); i++) {
            boolean flag = true;
            ways1 = new Ways();
            startEndPlacess = new ArrayList<>();
            trafficList1 = new ArrayList<>();
            for (int j = 0; j < startEndPlaces.get(i).size(); j++) {
                //根据起始城市与末城市查找可以乘坐的详细路线
                List<Traffic> trafficList = trafficService.findAllNeedTraffic(startEndPlaces.get(i).get(j).getP_start(), startEndPlaces.get(i).get(j).getP_end());
                if (trafficList.size() != 0) {
                    startEndPlacess.add(startEndPlaces.get(i).get(j));
                    trafficList1.addAll(trafficList);
                }else {
                    break;
                }
                if (j == (startEndPlaces.get(i).size() - 1)) {
                    flag = false;
                }

                if (flag == false) {
                    if (startEndPlacess.size() != 0 && trafficList1.size() != 0) {
                        ways1.setStartEndPlaces(startEndPlacess);
                        ways1.setTraffics(trafficList1);
                    }
                    break;
                }
            }
            if(ways1.getStartEndPlaces() != null) {
                waysList.add(ways1);
            }
        }

        for (int i = 0; i < waysList.size(); i++) {
            if (waysList.get(i).getStartEndPlaces().size() == 1) {
                for (int z = 0; z < waysList.get(i).getTraffics().size(); z++) {
                    ways1 = new Ways();
                    ways1.setStartEndPlaces(waysList.get(i).getStartEndPlaces());
                    trafficList1 = new ArrayList<>();
                    trafficList1.add(waysList.get(i).getTraffics().get(z));
                    ways1.setTraffics(trafficList1);
                    ways.add(ways1);
                }
            }else if (waysList.get(i).getStartEndPlaces().size() == 2) {
                if(waysList.get(i).getTraffics().size() == 2) {
                    ways1 = new Ways();
                    ways1.setStartEndPlaces(waysList.get(i).getStartEndPlaces());
                    ways1.setTraffics(waysList.get(i).getTraffics());
                    ways.add(ways1);
                }else {
                    for (int j = 0; j < waysList.get(i).getTraffics().size() - 1; j++) {
                        for (int t = j + 1; t < waysList.get(i).getTraffics().size(); t++) {
                            ways1 = new Ways();
                            ways1.setStartEndPlaces(waysList.get(i).getStartEndPlaces());
                            trafficList1 = new ArrayList<>();
                            if (waysList.get(i).getTraffics().get(j).getT_p_id() == waysList.get(i).getTraffics().get(t).getT_p_id()) {
                                continue;
                            }else {
                                trafficList1.add(waysList.get(i).getTraffics().get(j));
                                trafficList1.add(waysList.get(i).getTraffics().get(t));
                                ways1.setTraffics(trafficList1);
                                ways.add(ways1);
                            }
                        }
                    }
                }
            }
        }

        //从输入的地铁站点到高铁或者飞机站口的路径计算   然后根据之前计算的路径和地铁返回组合的
        //还需要考虑地铁换乘、到飞机场的情况
        List<SubwayInfo> subwayList ;
        for (int i = 0; i < ways.size(); i++){
            //根据传入的起始城市、起始站点和末站点通过深度优先遍历找出地铁路线
            subwayList = subwayService.findNeedSubway(startPlaceCity, startPlace, ways.get(i).getTraffics().get(0).getT_method1());
            ways.get(i).setSubways(subwayList);
        }

        //将不符合条件的路径去掉（地铁路线为空）
        for (int i = 0; i < ways.size(); i++) {
            if(ways.get(i).getSubways() == null) {
                ways.remove(i);
                i--;
            }
        }

        //先计算从开始的地铁站口到高铁站或者飞机场得时间（还要考虑换乘时间）在换乘impl里实现
        ways = changeSubwayService.findNeedAllChange(ways, startTime);
        waysList = new ArrayList<>();

        //接下来选择高铁和飞机
        for(int i = 0; i < ways.size(); i++){
            if(ways.get(i).getTraffics().size() >= 1){
                waysList1 = new ArrayList<>();
                if (ways.get(i).getTraffics().get(0).getT_method().equals("飞机")) {
                    waysList1 = planeService.findAllNeedPlane(ways.get(i), startTime, 0);
                } else if (ways.get(i).getTraffics().get(0).getT_method().equals("高铁")) {
                    waysList1 = motorcarService.findAllNeedMotorcar(ways.get(i), startTime, 0);
                }
                if(waysList1 != null && waysList1.size() > 0 && ways.get(i).getTraffics().size() == 1){
                    waysList.addAll(waysList1);
                }
            }
            if(ways.get(i).getTraffics().size() > 1 && waysList1 != null && waysList1.size() > 0){
                for (int j = 0; j < waysList1.size(); j++){
                    waysList2 = new ArrayList<>();
                    if (ways.get(i).getTraffics().get(1).getT_method().equals("飞机")) {
                        waysList2 = planeService.findAllNeedPlane(waysList1.get(j), startTime, 1);
                    } else if (ways.get(i).getTraffics().get(1).getT_method().equals("高铁")) {
                        waysList2 = motorcarService.findAllNeedMotorcar(waysList1.get(j), startTime, 1);
                    }
                }
                waysList.addAll(waysList2);
            }
        }

        //返回需要的数据（三种方式挑选的）
        List<List<Ways>> needWaysList = selectPathUtil.selectRight(waysList);

        long end = System.currentTimeMillis();
        System.out.println((end - start)+"时间");
        return needWaysList;
    }
}
