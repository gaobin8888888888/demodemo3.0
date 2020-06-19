package com.sts.demodemo.util;

import com.sts.demodemo.entity.Ways;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SelectPathUtil {

    /**
     *
     * @param waysList
     * @return List<List<Ways>>
     */
    public List<List<Ways>> selectRight(List<Ways> waysList){

        List<List<Ways>> needWaysList = new ArrayList<>();

        //1.时间
        double[][] time = new double[waysList.size()][2];
        //2.花费
        double[][] money = new double[waysList.size()][2];
        //3.综合
        double[][] total = new double[waysList.size()][2];

        for (int i = 0; i < waysList.size(); i++){
            time[i][0] = waysList.get(i).getTotalTime();
            time[i][1] = i;

            money[i][0] = waysList.get(i).getTotalMoney();
            money[i][1] = i;
        }

        needWaysList.add(compare(waysList, time));
        needWaysList.add(compare(waysList,money));


        //3.综合
        for (int i = 0; i < waysList.size(); i++){
            for (int j = 0; j < waysList.size(); j++){
                if(time[i][1] == money[j][1]){
                    total[i][0] = i + j;
                    total[i][1] = time[i][1];
                    break;
                }
            }
        }

        needWaysList.add(compare(waysList, total));
        return needWaysList;
    }


    /**
     *
     * @param waysList
     * @param other
     * @return List<Ways>
     */
    public List<Ways> compare(List<Ways> waysList, double[][] other){
        List<Ways> list = new ArrayList<>();
        for (int i = 1; i < waysList.size(); i++){
            boolean flag = true;
            for(int j = 0; j < waysList.size() - 1; j++){
                if (other[j][0] > other[j + 1][0]){
                    double x,y;
                    x = other[j][0];
                    y = other[j][1];
                    other[j][0] = other[j + 1][0];
                    other[j][1] = other[j + 1][1];
                    other[j + 1][0] = x;
                    other[j + 1][1] = y;
                    flag = false;
                }
            }
            if (flag == true){
                break;
            }
        }

        if(waysList.size() > 5){
            for(int i = 0; i < 5; i++){
                list.add(waysList.get((int)other[i][1]));
            }
        }else{
            list.addAll(waysList);
        }
        return list;
    }

}
