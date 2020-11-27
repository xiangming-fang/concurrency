package concurrent.threadlocal;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: albert.fang
 * @date: 2020/11/27 17:29
 * @description: 对ThreadLocalDemo1的优化，利用线程池来创建线程
 * feature:
 * 1、用线程池创建线程
 */
public class ThreadLocalDemo2 {
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
        SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
        return sdf.format(date);
    }
}
