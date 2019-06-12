package com.example.demo.Thread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Describe
 * @Auth duranfu
 * @Date 2019/5/12
 */
public class Test1 {
    public static void main(String[] args) {
        mapTest();
    }

    public static void mapTest() {

        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(3);
        list.add(4);

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == 3) {
                list.remove(i);
            }
        }

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
