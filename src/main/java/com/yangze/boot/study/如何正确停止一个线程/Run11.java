package com.yangze.boot.study.如何正确停止一个线程;

// 可以看到，当线程t1在获取到o1和o2两个锁开始执行，在还没有执行结束的时候，主线程调用了t1的stop方法中断了t1的执行，
// 释放了t1线程获取到的所有锁，中断后t2获取到了o1和o2锁，开始执行直到结束，而t1却夭折在了sleep的时候，sleep后的代码没有执行。
//
// 因此使用stop我们在不知道线程到底运行到了什么地方，暴力的中断了线程，如果sleep后的代码是资源释放、重要业务逻辑等比较重要的代码的话，
// 亦或是其他线程依赖t1线程的运行结果，那直接中断将可能造成很严重的后果。
public class Run11 {

    public static void main(String[] args) throws InterruptedException {
        Object o1 = new Object();
        Object o2 = new Object();
        Thread t1 = new Thread(() -> {
            synchronized (o1) {
                synchronized (o2) {
                    try {
                        System.out.println("t1获取到锁");
                        Thread.sleep(5000);
                        System.out.println("t1结束");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t1.start();
        Thread.sleep(1000);
        Thread t2 = new Thread(() -> {
            synchronized (o1) {
                synchronized (o2) {
                    try {
                        System.out.println("t2获取到锁");
                        Thread.sleep(5000);
                        System.out.println("t2结束");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t2.start();
        t1.stop();
    }
}
