package com.yangze.boot.study.如何正确停止一个线程;

class MyThread6 extends Thread {
    public void run() {
        super.run();
        try {
            System.out.println("线程开始。。。");
            for (int i = 0; i < 10000; i++) {
                System.out.println("i=" + i);
            }
            Thread.sleep(200000);
            System.out.println("线程结束。。。");
        } catch (InterruptedException e) {
            System.out.println("先停止，再遇到sleep，进入catch异常");
            e.printStackTrace();
        }

    }
}

public class Run6 {
    public static void main(String args[]) {
        Thread thread = new MyThread6();
        thread.start();
        thread.interrupt();
    }
}
