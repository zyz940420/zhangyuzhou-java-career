package com.zhangyuzhou.common;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

public class Test03 {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(3);

        for (int i = 0; i < 3; i++) {
            list.add(i);
        }
        System.err.println(JSON.toJSONString(list));

//        while (list.listIterator().hasNext()) {
//            list.remove(list.listIterator().nextIndex());
//            System.err.println(JSON.toJSON(list));
//        }

        for (Integer integer : list) {
            list.remove(integer);
            System.err.println(JSON.toJSON(list));
        }

//        for (int i = 0; i < list.size(); i++) {
//            list.remove(i);
//            System.err.println(JSON.toJSONString(list));
//        }


    }
}
