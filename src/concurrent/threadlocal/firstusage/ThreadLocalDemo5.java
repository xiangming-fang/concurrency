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
 * @description: 对ThreadLocalDemo4的演化
 * feature：
 * 1、取消加锁，通过ThreadLocal来保证多个线程使用同一个SimpleDateFormat对象是安全的
 */
public class ThreadLocalDemo5 {

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
        return ThreadLocalOfSimpleDateFormat.threadLocal.get().format(date);
    }
}

class ThreadLocalOfSimpleDateFormat{
    public static ThreadLocal<SimpleDateFormat> threadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("mm:ss"));
}
