package com.flyhaze.sample.guava;

import com.google.common.base.CaseFormat;

public class StringSample {

    public static void main(String[] args) {
        System.out.println(CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, "UserName"));
    }
}
