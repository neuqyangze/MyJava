package com.yangze.boot.study.如何正确停止一个线程;

public class Run2 {
    public static void main(String args[]){
        Thread.currentThread().interrupt();

        // 方法interrupted()的确判断出当前线程是否是停止状态。但为什么第2个布尔值是false呢？ 官方帮助文档中对interrupted方法的解释：
        // 测试当前线程是否已经中断。线程的中断状态由该方法清除。换句话说，如果连续两次调用该方法，则第二次调用返回false。
        System.out.println("stop 1??" + Thread.interrupted());
        System.out.println("stop 2??" + Thread.interrupted());

        System.out.println("End");

        // stop 1??true
        // stop 2??false
        // End
    }
}
