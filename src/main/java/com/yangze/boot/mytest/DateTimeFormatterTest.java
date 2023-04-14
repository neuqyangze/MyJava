package com.yangze.boot.mytest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeFormatterTest {
    public void test() {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime); // 2019-11-20T15:04:29.017
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String strDate = localDateTime.format(dtf);
        System.out.println(strDate); // 2019/23/20 15:23:46

        LocalDate localDate = localDateTime.toLocalDate();
        System.out.println(localDate);
        LocalTime localTime = localDateTime.toLocalTime();
        System.out.println(localTime);

        LocalDate localDate1 = LocalDate.now();
        System.out.println(localDate1); // 2019-11-20

        LocalTime localTime1 = LocalTime.now();
        System.out.println(localTime1); // 15:14:17.081
        int hour = localTime1.getHour();
        int minute = localTime1.getMinute();
        int second = localTime1.getSecond();
        System.out.println("hour:" + hour + ", minute:" + minute + ", second:" + second);
    }
}
