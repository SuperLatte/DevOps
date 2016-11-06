package com.devops.utils;

import java.util.Calendar;

/**
 * Created by super on 2016/11/6.
 */
public class TimeGetter {
    public static int getCurrentTime() {
        Calendar cal = Calendar.getInstance();
        return (int) (cal.getTimeInMillis()/1000);
    }
}
