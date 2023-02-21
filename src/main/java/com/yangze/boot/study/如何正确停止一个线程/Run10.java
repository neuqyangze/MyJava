package com.yangze.boot.study.如何正确停止一个线程;

// 将方法interrupt()与return结合使用也能实现停止线程的效果：
// 不过还是建议使用“抛异常”的方法来实现线程的停止，因为在catch块中还可以将异常向上抛，使线程停止事件得以传播。
class MyThread10 extends Thread {
    public void run(){
        while (true){
            if(this.isInterrupted()){
                System.out.println("线程被停止了！");
                return;
            }
            System.out.println("Time: " + System.currentTimeMillis());
        }
    }
}

public class Run10 {
    public static void main(String args[]) throws InterruptedException {
        Thread thread = new MyThread10();
        thread.start();
        Thread.sleep(2000);
        thread.interrupt();
    }
}
