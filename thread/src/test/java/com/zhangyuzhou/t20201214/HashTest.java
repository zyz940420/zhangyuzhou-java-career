package com.zhangyuzhou.t20201214;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * @author zhangyuzhou
 * @description
 * @date DATE:2020-12-14 14:44
 */
public class HashTest {

    public static void main(String[] args) {
//        Map<String,Object> table = new Hashtable<>();
        HashMap<Integer, List<Object>> table = new HashMap<>();

        for (int i = 0; i < 40; i++) {
            if(i == 11){
                table.put(i,Arrays.asList(i));
            }else{
                table.put(i,Arrays.asList(i));
            }
        }

        table.put(null, Arrays.asList(null,null,null));
        System.err.println(table);
    }

}
