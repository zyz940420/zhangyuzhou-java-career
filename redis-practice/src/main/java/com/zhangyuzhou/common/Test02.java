package com.zhangyuzhou.common;

import java.util.ArrayList;
import java.util.List;

public class Test02 {

    public static void main(String[] args) {
//        List<Object> list = new ArrayList<>(Integer.MAX_VALUE);

//        Integer[] temp = new Integer[Integer.MAX_VALUE];

        int[] temp2 = {};

        String s = new String("test");
        method1(s);
        System.err.println(s);
    }

    private static void method1(String s) {
        s = "0";
        System.err.println(s);
    }
}
