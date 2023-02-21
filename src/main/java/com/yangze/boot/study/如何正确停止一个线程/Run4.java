package com.yangze.boot.study.如何正确停止一个线程;

class MyThread4 extends Thread {
    public void run() {
        super.run();
        try {
            for (int i = 0; i < 500000; i++) {
                if (this.interrupted()) {
                    System.out.println("线程已经终止，for循环不再执行");
                    throw new InterruptedException();
                }
                System.out.println("i=" + (i + 1));
            }
            System.out.println("这是for循环外面的语句，也会被执行");
        } catch (Exception e) {
            System.out.println("进入MyThread4.java类中的catch了。。。");
            e.printStackTrace();
        }
    }
}

public class Run4 {
    public static void main(String args[]) {
        Thread thread = new MyThread4();
        thread.start();
        try {
            Thread.sleep(2000);
            thread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
