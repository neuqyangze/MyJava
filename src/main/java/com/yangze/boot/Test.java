package com.yangze.boot;

public class Test {

    public static void main(String[] args) {
        // for (int i = 0; i < 1000000000; i++) {
        //     System.out.println("i====" + i);
        // }
        int days=Math.round(45*80/100f);
        System.out.println("days === " + days);

        long days2= Math.round((System.currentTimeMillis()-1680237490310L)/(24*60*60*1000*1.0));
        System.out.println("days2 === " + days2);
    }
}
