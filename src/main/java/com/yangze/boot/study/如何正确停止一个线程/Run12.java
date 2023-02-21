package com.yangze.boot.study.如何正确停止一个线程;

public class Run12 {

    volatile static boolean flag = false;

    public static void main(String[] args) throws InterruptedException {
        Object o1 = new Object();
        Thread t1 = new Thread(() -> {
            synchronized (o1) {
                try {
                    System.out.println("t1获取到锁");
                    while (!flag)
                        Thread.sleep(5000); // 执行业务逻辑
                    System.out.println("t1结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        Thread.sleep(1000);
        Thread t2 = new Thread(() -> {
            synchronized (o1) {
                try {
                    System.out.println("t2获取到锁");
                    Thread.sleep(5000); // 执行业务逻辑
                    System.out.println("t2结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t2.start();
        flag = true;
    }
}
