package com.example.demo;


import java.lang.annotation.Annotation;
import java.math.BigDecimal;
import java.net.Inet4Address;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Describe
 * @Auth duranfu
 * @Date 2018/11/19
 */

public class Test {




    public static void main(String[] args) throws Exception {
        long b = Math.round(-11.6);
        System.out.println( "bbbbb" + b);


//        BigDecimal interest_rateBig = new BigDecimal("0.04");
//        BigDecimal svc_fee_cal_rateBig = new BigDecimal("0.0045");
//        BigDecimal acct_mgt_fee_cal_rateBig = new BigDecimal("0.0221");
//        BigDecimal year = new BigDecimal("12");
//        BigDecimal yearRate = interest_rateBig.add(svc_fee_cal_rateBig.multiply(year)).add(acct_mgt_fee_cal_rateBig.multiply(year));

//        BigDecimal yearRateb = yearRate.multiply(new BigDecimal("100"));

//        System.out.println(String.valueOf(yearRateb.setScale(0, BigDecimal.ROUND_UP).stripTrailingZeros().toPlainString()));
//        System.out.println(String.valueOf(yearRate.multiply(new BigDecimal("100")).stripTrailingZeros().toPlainString()));
//        result.putData("ye




//        BigDecimal interest_rateBig = new BigDecimal("4.103125");
//        BigDecimal svc_fee_cal_rateBig = new BigDecimal("0.004468974");
//        BigDecimal acct_mgt_fee_cal_rateBig = new BigDecimal("0.022192151");
//        BigDecimal year = new BigDecimal("12");
//        BigDecimal yearRate = interest_rateBig.add(svc_fee_cal_rateBig.multiply(year)).add(acct_mgt_fee_cal_rateBig.multiply(year));
//
//        BigDecimal yearRateb = yearRate.multiply(new BigDecimal("100"));
//
//        System.out.println(String.valueOf(yearRateb.setScale(0, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString()));
//        System.out.println(String.valueOf(yearRate.multiply(new BigDecimal("100")).stripTrailingZeros().toPlainString()));
//        result.putData("yearRate", String.valueOf(yearRate.multiply(new BigDecimal("100")).stripTrailingZeros().toPlainString()));







//        BigDecimal b = new BigDecimal("35.9999999722225");
//        BigDecimal b1 = b.setScale(0, BigDecimal.ROUND_HALF_UP);
//        System.out.println("======="+b1);

//        String aaa = "0.0444";
//
//        BigDecimal interest_rateBig = new BigDecimal(aaa);
//        BigDecimal a = new BigDecimal("10000");
//        String interest_rateStr = interest_rateBig.multiply(a).toString();
//        System.out.println(interest_rateStr.substring(0,interest_rateStr.indexOf(".")));


//        BigDecimal interest_rateBig = new BigDecimal(aaa);
//        BigDecimal a = new BigDecimal("10000");
//        BigDecimal multiply = interest_rateBig.multiply(a);
//        String s = multiply.toString();
//        int i = s.indexOf(".");
//        String subtract = s.substring(0,i);
//        System.out.println(subtract);

//        SimpleDateFormat sif = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
//        Date date1 = new Date();
//        Date date2 = sif.parse("2019-02-03 12:00:00");
//        System.out.println(date1.compareTo(date2));

//        test2();
    }


    public static void test2() {
        double moneyD = 9001;

        String monthIncome = "";
        moneyD = moneyD/3;
        System.out.println("除以三后的结果：" + moneyD);
        double ceil = Math.ceil(moneyD);//计算结果向上取整
        System.out.println("除以三后的结果向上取整：" + ceil);
        if (ceil < 3000) {
            ceil = 3000;
            monthIncome = String.valueOf((int)ceil);
            System.out.println("除以三后的结果小于3000，取3000");
        } else {
            String ceilStr = String.valueOf((int)ceil);
            String b1 = ceilStr.substring(0,ceilStr.length()-3);//前几位
            String b2 = ceilStr.substring(ceilStr.length()-3);//后三位
            //如果后三位大于0,则将后三位置000,前几位加1
            if (Integer.parseInt(b2) > 0) {
                int bInt = Integer.parseInt(b1);
                int bInt1 = bInt + 1;
                b1 = String.valueOf(bInt1);
                b2 = "000";
            }
            monthIncome = b1 + b2;
        }
        System.out.println("最终结果：" + monthIncome);
    }

    /**
     * 无额度：本次贷款金额/6
     */
    public static void test1(boolean haveQuota, String money) {
        String monthIncome = "";
        double temp = 0;//做除法后的值
        double moneyD = Double.parseDouble(money);
        if (haveQuota) {
            temp = moneyD/3;
            System.out.println("有额度除以3后：" + temp);
        } else {
            temp = moneyD/6;
            System.out.println("没有额度除以6后：" + temp);
        }
        double ceil = Math.ceil(temp);
        if (ceil < 3000) {
            System.out.println("结果不足3000，取3000");
            ceil = 3000;
            monthIncome = String.valueOf((int)ceil);
        } else {
//            String a = "213001";
            String ceilStr = String.valueOf((int)ceil);
            System.out.println("除以6后：" + ceilStr);
            String b1 = ceilStr.substring(0,ceilStr.length()-3);//前几位
            String b2 = ceilStr.substring(ceilStr.length()-3);//后三位
            System.out.println("原始：b1:" + b1 + "，b2:" + b2);
            if (Integer.parseInt(b2) > 0) {
                int bInt = Integer.parseInt(b1);
                int bInt1 = bInt + 1;
                b1 = String.valueOf(bInt1);
                System.out.println("千位进一后，b1:" + b1);
                b2 = "000";
            }
            monthIncome = b1 + b2;
        }
//
        System.out.println("最终结果：" + monthIncome);
    }
}











