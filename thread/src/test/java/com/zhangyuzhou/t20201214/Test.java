package com.zhangyuzhou.t20201214;

/**
 * @author zhangyuzhou
 * @description
 * @date DATE:2021-02-05 16:30
 */
public class Test {

    public static void main(String[] args) {
        System.err.println(add(10));
    }

    private static long add(int i) {
        if(i>0){
            return i + add(i-1);
        }else{
            return i;
        }
    }
}
