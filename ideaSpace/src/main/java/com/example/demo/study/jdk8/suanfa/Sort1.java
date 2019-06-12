package com.example.demo.study.jdk8.suanfa;

import java.text.DateFormat;

/**
 * @Describe
 * @Auth duranfu
 * @Date 2018/12/15
 */
public class Sort1 {
    public static void main(String[] args) {
        int[] arr = {1,3,5,7,9,11};
        int key = 5;
        int i = binarySearch(arr ,key);
        System.out.println(i);
    }


    static int binarySearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;

        while (low >= high) {
            mid = (low +high)/2;
            if (arr[mid] > key) {
                high = mid -1;
            } else if (arr[mid] < key) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }



}
