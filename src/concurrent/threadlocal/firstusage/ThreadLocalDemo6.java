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
 * @description: 对ThreadLocalDemo5的错误演化
 * feature：
 * 1、给放在ThreadLocal里的对象前面加上static.（不安全了，不是每个线程独享的了）
 * 2、也就是说，如果放入ThreadLocal里的对象是共享的，也就是static修饰的，那么使用ThreadLocal毫无意义
 */
public class ThreadLocalDemo6 {
    static SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");

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
        return Holder.threadLocal.get().format(date);
    }
}

class Holder{
    public static ThreadLocal<SimpleDateFormat> threadLocal = ThreadLocal.withInitial(() -> ThreadLocalDemo6.sdf);
}
