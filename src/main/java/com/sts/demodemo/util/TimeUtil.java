package com.sts.demodemo.util;

import org.springframework.stereotype.Component;

@Component
public class TimeUtil {
    //将时间字符串转化为整数

    /**
     * @param s
     * @return int
     */
    public int timehandleToInt(String s){
        String[] str = s.split("：");
        int a = Integer.parseInt(str[0]);
        int b = Integer.parseInt(str[1]);
        int c = Integer.parseInt(str[2]);
        int sum = a * 60 * 60 + b * 60 + c;
        return sum;
    }


    //将整型转化为时间字符串

    /**
     * @param n
     * @return String
     */
    public String timehandleToString(int n){
        int a = n / 60 / 60;
        int b = n / 60 % 60;
        int c = n % 60;
        StringBuffer str = new StringBuffer();
        if(a < 10 && a >= 0){
            str.append("0"+a+"：");
        }else {
            str.append(a+"：");
        }

        if(b < 10 && b >= 0){
            str.append("0"+b+"：");
        }else {
            str.append(b+"：");
        }

        if(c < 10 && c >= 0){
            str.append("0"+c+"：");
        }else {
            str.append(c+"：");
        }
        return str.toString();
    }
}
