package com.qf.logmonitor1706.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    private static SimpleDateFormat sdf;

    public static String getDateTime() {
        String format = "yyyy-MM-dd HH:mm:ss";
        sdf = new SimpleDateFormat(format);
        return sdf.format(new Date());
    }

}
