package com.lql.graduation.util;

public class TimeUtil {



    public static Integer millTimeToMinue(Long millTime){


          double sec =Math.ceil(millTime/1000);
            Double minue = Math.ceil(sec/60);
        return  minue.intValue();

    }




}
