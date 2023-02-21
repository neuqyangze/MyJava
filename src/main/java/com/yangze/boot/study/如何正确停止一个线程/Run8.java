package com.yangze.boot.study.如何正确停止一个线程;

class MyThread8 extends Thread {
    public void run(){
        super.run();
        try {
            // stop()方法已经作废，因为如果强制让线程停止有可能使一些清理性的工作得不到完成。
            // 另外一个情况就是对锁定的对象进行了解锁，导致数据得不到同步的处理，出现数据不一致的问题。
            // 使用stop()释放锁将会给数据造成不一致性的结果。如果出现这样的情况，程序处理的数据就有可能遭到破坏，最终导致程序执行的流程错误，一定要特别注意：
            this.stop();
        } catch (ThreadDeath e) {
            System.out.println("进入异常catch");
            e.printStackTrace();
        }
    }
}

public class Run8 {
    public static void main(String args[]) throws InterruptedException {
        Thread thread = new MyThread8();
        thread.start();
    }
}
