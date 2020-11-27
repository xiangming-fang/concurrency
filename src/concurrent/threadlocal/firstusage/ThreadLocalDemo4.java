package concurrent.threadlocal.firstusage;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: albert.fang
 * @date: 2020/11/27 17:40
 * @description: 对ThreadLocalDemo3的演化
 * feature：
 * 1、通过加锁的方式来保证安全；排队状态，一个一个执行
 */
public class ThreadLocalDemo4 {
    private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    private static final Object object = new Object();

    public static void main(String[] args) {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(1000);
        try {
            Instant begin = Instant.now();
            for (int i = 0; i < 1000; i++) {
                int finalI = i;
                fixedThreadPool.submit(() -> {
                    System.out.println(date((long) finalI));
                    latch.countDown();
                });
            }
            latch.await();
            Instant end = Instant.now();
            System.out.printf("消耗时间是【%s】ms\n", Duration.between(begin,end).toMillis());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fixedThreadPool.shutdown();
        }
    }

    private static String date(Long time){
        Date date = new Date(time * 1000);
        String format = null;
        synchronized (object){
            format = sdf.format(date);
        }
        return format;
    }
}
