package com.yangze.boot.mytest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * SimpleDateFormat线程安全测试
 * 〈功能详细描述〉
 *
 * @author 17090889
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本]（可选）
 */
public class SimpleDateFormatTest {

    ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(10, 100, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<>(1000), new MyThreadFactory("SimpleDateFormatTest"));

    static class MyThreadFactory implements ThreadFactory {

        private final AtomicInteger mThreadNum = new AtomicInteger(1);

        private String threadNamePrefix;

        public MyThreadFactory(String threadNamePrefix) {
            this.threadNamePrefix = threadNamePrefix;
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, threadNamePrefix + "-my-thread-" + mThreadNum.getAndIncrement());
            System.out.println(t.getName() + " has been created");
            return t;
        }
    }

    public void test() {
        // while (true) {
        for (int i = 0; i < 1030; i++) {
            poolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String dateString = simpleDateFormat.format(new Date());
                    System.out.println("dateString === " + dateString);
                    try {
                        Date parseDate = simpleDateFormat.parse(dateString);
                        String dateString2 = simpleDateFormat.format(parseDate);
                        System.out.println(dateString.equals(dateString2));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        // 指示当所有线程执行完毕后关闭线程池和工作线程，如果不调用此方法，jvm不会自动关闭
        poolExecutor.shutdown();

        try {
            // 等待线程执行完毕，不能超过2*60秒，配合shutDown
            poolExecutor.awaitTermination(2*60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    // }

}
