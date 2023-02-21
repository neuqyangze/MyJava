package com.yangze.boot.study.如何正确停止一个线程;

public class Run13 {

    public static void main(String[] args) throws InterruptedException {
        Object o1 = new Object();
        Thread t1 = new Thread(() -> {
            synchronized (o1) {
                System.out.println("t1获取到锁");
                while (!Thread.currentThread().isInterrupted()) {
                    for (int i = 0; i < 100; i++) {
                        if (i == 50)
                            System.out.println();
                        System.out.print(i + " ");
                    }
                    System.out.println();
                }
                System.out.println("t1结束");
            }
        });
        t1.start();
        Thread t2 = new Thread(() -> {
            synchronized (o1) {
                try {
                    System.out.println("t2获取到锁");
                    Thread.sleep(2000);// 执行业务逻辑
                    System.out.println("t2结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t2.start();
        Thread.sleep(1);
        t1.interrupt();
    }
}
