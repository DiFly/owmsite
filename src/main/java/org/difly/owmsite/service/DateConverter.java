package org.difly.owmsite.service;

import java.util.Date;

public class DateConverter {
    public static Date date (long inDate){
//        long unixSeconds = Long.parseLong(inDate); // from string
        long unixSeconds = inDate;
        Date date = new Date(unixSeconds*1000L);
        return date;
    }
}
