/**
 * Sample
 * DateTimeFormatSample.java
 */
package com.flyhaze.sample.data;

import java.text.DateFormat;
import java.time.Instant;
import java.util.Date;

/**
 * DateTimeFormatSample
 *
 * @author liaowei
 * @date 创建时间：2018年3月31日 下午4:33:20 
 * @since jdk1.8
 */
public class DateTimeFormatSample {

    public static void main(String[] args) {
        Date date = Date.from(Instant.now());
        DateFormat dateFormat;
        dateFormat = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL);
        System.out.println(dateFormat.format(date));
        dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        System.out.println(dateFormat.format(date));
        dateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
        System.out.println(dateFormat.format(date));
        dateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
        System.out.println(dateFormat.format(date));
    }
}
