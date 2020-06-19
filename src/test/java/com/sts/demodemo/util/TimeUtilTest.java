package com.sts.demodemo.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TimeUtilTest {

    TimeUtil timeUtil = new TimeUtil();
    @Test
    public void timehandle() {
        System.out.println(timeUtil.timehandleToInt("08：30：08"));
        System.out.println(timeUtil.timehandleToString(30608));
    }
}