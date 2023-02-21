package com.yangze.boot.study.如何正确停止一个线程;

class MyThread extends Thread {
    public void run() {
        super.run();
        for (int i = 0; i < 500000; i++) {
            i++;
            // System.out.println("i=" + (i + 1));
        }
    }
}

public class Run {
    public static void main(String args[]) {
        Thread thread = new MyThread();
        thread.start();
        try {
            Thread.sleep(2000);
            // interrupt()方法的使用效果并不像for+break语句那样，马上就停止循环。调用interrupt方法是在当前线程中打了一个停止标志，并不是真的停止线程。
            thread.interrupt();

            // 这个当前线程是main，它从未中断过，所以打印的结果是两个false
            System.out.println("stop 1??" + thread.interrupted());
            System.out.println("stop 2??" + thread.interrupted());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
