package com.example.demo.study.jdk8.jdk8;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by admin on 2018/8/7.
 */
public class LambdaDemo {
	public static void main(String[] args) {

	}

	public static void test() {

	}

	public static void sort() {
		ArrayList<Integer> list = new ArrayList<>();
		Collections.sort(list, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1.compareTo(o2);
			}
		});

		Collections.sort(list, (s1,s2)->s1.compareTo(s2));
	}
}
