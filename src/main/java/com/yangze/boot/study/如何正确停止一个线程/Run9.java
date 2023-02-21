package com.yangze.boot.study.如何正确停止一个线程;

// 使用stop()释放锁将会给数据造成不一致性的结果。如果出现这样的情况，程序处理的数据就有可能遭到破坏，最终导致程序执行的流程错误，一定要特别注意：
class SynchronizedObject {
    private String name = "a";
    private String password = "aa";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public synchronized void printString(String name, String password){
        try {
            this.name = name;
            Thread.sleep(100000);
            this.password = password;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyThread9 extends Thread {
    private SynchronizedObject synchronizedObject;
    public MyThread9(SynchronizedObject synchronizedObject){
        this.synchronizedObject = synchronizedObject;
    }

    public void run(){
        synchronizedObject.printString("b", "bb");
    }
}

public class Run9 {
    public static void main(String args[]) throws InterruptedException {
        SynchronizedObject synchronizedObject = new SynchronizedObject();
        Thread thread = new MyThread9(synchronizedObject);
        thread.start();
        Thread.sleep(500);
        thread.stop();
        System.out.println(synchronizedObject.getName() + "  " + synchronizedObject.getPassword());
    }
}
