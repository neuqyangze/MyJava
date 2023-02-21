package com.yangze.boot.study.如何正确停止一个线程;

class MyThread5 extends Thread {
    public void run() {
        super.run();
        try {
            System.out.println("线程开始。。。");
            Thread.sleep(200000);
            System.out.println("线程结束。");
        } catch (InterruptedException e) {
            System.out.println("在沉睡中被停止，进入catch，用isInterrupted()方法的结果是：" + this.isInterrupted());
            e.printStackTrace();
        }
    }
}

public class Run5 {
    public static void main(String args[]) {
        Thread thread = new MyThread5();
        thread.start();
        thread.interrupt();
    }
}
