package org.difly.owmsite.service;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {
    public static Date date (long inDate){
//        long unixSeconds = Long.parseLong(inDate); // from string
        long unixSeconds = inDate;
        Date date = new Date(unixSeconds*1000L);
        return date;
    }

    public static String simpleDate(long inDate){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm z").format(DateConverter.date(inDate));
//        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z").format(DateConverter.date(inDate));
    }
}
