package com.yangze.boot.study.如何正确停止一个线程;

public class Run3 {
    public static void main(String args[]){
        Thread thread = new MyThread();
        thread.start();
        thread.interrupt();
        System.out.println("stop 1??" + thread.isInterrupted());
        System.out.println("stop 2??" + thread.isInterrupted());
    }
}