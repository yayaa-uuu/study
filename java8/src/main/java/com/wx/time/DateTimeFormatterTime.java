package com.wx.time;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeFormatterTime {
    public static void main(String[] args) {
        LocalDateTime localDateTime=LocalDateTime.now();
        String format = localDateTime.format(DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(format);
    }
}
