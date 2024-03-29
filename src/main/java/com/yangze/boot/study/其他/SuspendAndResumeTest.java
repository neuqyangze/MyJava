package com.yangze.boot.study.其他;

/**
 * suspend方法的作用是挂起某个线程直到调用resume方法来恢复该线程，但是调用了suspend方法后并不会释放被挂起线程获取到的锁，
 * 正因如此就给suspend和resume这哥俩贴上了容易引发死锁的标签，当然这也正是导致suspend和resume退出历史舞台的罪魁祸首。
 * 同样我们看看java开发者为suspend的淘汰给出的理由：
 * <p>
 * 从中我们可以得出以下结论：
 * <p>
 * 1、suspend具有天然的死锁倾向
 * <p>
 * 2、当某个线程被suspend后，该线程持有的锁不会被释放，其他线程也就不能访问这些资源
 * <p>
 * 3、suspend某个线程后，如果在resume的过程中出现异常导致resume方法执行失败，则lock无法释放，导致死锁
 */
public class SuspendAndResumeTest {

    // 可以看到，整个程序卡的死死的，在调用resume恢复t1线程之前抛出了一个未知异常，
    // 导致t1一直挂起进而无法释放o1锁，而t2需要获取到o1锁后才能继续执行，但苦苦等待，
    // 奈何o1被t1拿捏的死死的，从此整个程序就陷入了无尽的等待中----死锁。
    public static void main(String[] args) throws InterruptedException {
        Object o1 = new Object();
        Object o2 = new Object();
        Thread t1 = new Thread(() -> {
            synchronized (o1) {
                System.out.println("t1获取到o1锁开始执行");
                try {
                    Thread.sleep(5000);// 模拟执行业务逻辑
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t1执行结束");
            }
        });
        t1.start();
        Thread t2 = new Thread(() -> {
            synchronized (o2) {
                System.out.println("t2获取到o2开始执行");
                try {
                    Thread.sleep(2000);// 执行耗时业务
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    System.out.println("t2获取到o1锁开始继续执行");
                }
                System.out.println("t2执行结束");
            }
        });
        t2.start();

        Thread.sleep(1000);
        t1.suspend();
        // 假设抛出了一个未知异常
        int i = 1 / 0;
        t1.resume();
    }
}

