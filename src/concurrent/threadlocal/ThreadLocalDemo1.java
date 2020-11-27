package concurrent.threadlocal;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * @author: albert.fang
 * @date: 2020/11/26 15:56
 * @description: threadlocal工具的示例1
 * feature:
 * 1、每一个线程new一个SimpleDateFormat对象,不会产生线程安全问题
 */
public class ThreadLocalDemo1 {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(1000);
        Instant begin = Instant.now();
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            new Thread( () ->{
                System.out.println(date((long) finalI));
                latch.countDown();
            }).start();
        }
        try {
            latch.await();
            Instant end = Instant.now();
            System.out.printf("消耗时间是【%s】ms\n", Duration.between(begin,end).toMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private static String date(Long time){
        Date date = new Date(time * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
        return sdf.format(date);
    }
}
