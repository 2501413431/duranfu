package com.example.demo.study.jdk8.suanfa;

/**
 * @Describe
 * @Auth duranfu
 * @Date 2019/4/10
 */
public class MianShiTi {

    public static void main(String[] args) {
        int[] arr = {1,2,4,6,8,2,3,4,5,6,7,8};
        int i = maxLeghth(arr);
        System.out.println(i);

    }


    /**
     * 反转字符串
     * @param str
     * @return
     */
    public static String reverseString (String str) {
        int i = 0;
        int j = str.length() - 1;
        char[] arr = str.toCharArray();
        while (i < j) {
            swap(arr, i, j);
            i++;
            j--;
        }
        return String.valueOf(arr);
    }

    public static void swap(char[] arr, int i , int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    /**
     * 字符串转int类型，不用现有函数
     * @param str
     * @return
     */
    public static int StrToInt(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        boolean isnevagite = str.charAt(0) == '-';
        int ret = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (i == 0 && (c == '-' || c == '+')) {
                continue;
            }
            if (c < '0' || c > '9') {
                return 0;
            }
            ret = ret * 10 + (c - '0');
        }
        return isnevagite ? -ret : ret;
    }

    /**
     * 波非那切数列
     * @param n 1,2,3,5，8,13
     * @return
     */
    public static int boFeiNaQi(int n) {
        if (n == 1 || n == 0) {
            return 1;
        } else {
            return boFeiNaQi(n-2) + boFeiNaQi(n-1);
        }
    }


    /******单链表反转start******/
    //https://www.cnblogs.com/byrhuangqiang/p/4311336.html
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return head;
        }
        //新建一个指向头节点的节点
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        //头加点变量名
        ListNode prev = dummy.next;
        //需要操作的节点的变量名
        ListNode pcur = prev.next;
        while (pcur != null) {
            prev.next = pcur.next;//头加点next指向第三个节点
            pcur.next = dummy.next;//第二个节点的next指向头节点
            dummy.next = pcur;//dummy的next指向第二个节点
            pcur = prev.next;//下一次该操作第三个节点
        }
        return dummy.next;
    }
    /******单链表反转end******/


    /**
     * int[] arr = {1,2,4,6,8,2,3,4}
     * 输出最长递增的长度
     * 有些算法面试的时候手写不出来，敲的话比较容易敲出来
     */
    public static int maxLeghth(int[] arr) {
        int n = 1;
        int max = 0;
        for (int i = 0; i < arr.length-1; i++) {
            if (arr[i] < arr[i+1]) {
                n++;
                if (n > max) {
                    max = n;
                }
                continue;
            }
            n = 1;
        }
        return max;
    }
}
/******单链表反转start******/
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}
/******单链表反转end******/
