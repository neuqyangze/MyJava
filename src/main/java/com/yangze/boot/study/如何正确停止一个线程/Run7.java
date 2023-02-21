package com.yangze.boot.study.如何正确停止一个线程;

class MyThread7 extends Thread {
    private int i = 0;
    public void run(){
        super.run();
        try {
            while (true){
                System.out.println("i=" + i);
                i++;
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class Run7 {
    public static void main(String args[]) throws InterruptedException {
        Thread thread = new MyThread7();
        thread.start();
        Thread.sleep(2000);
        thread.stop();
    }
}
