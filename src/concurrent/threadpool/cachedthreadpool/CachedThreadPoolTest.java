package concurrent.threadpool.cachedthreadpool;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: albert.fang
 * @date: 2020/11/25 15:00
 * @description: 缓存线程池
 * feature：
 * 1、理论上可创建无数个线程，实际上是Integer.Max_Value个。
 * 2、没有核心线程数。
 * 3、检测线程池中的线程是否休闲了60s，是的话回收该线程。
 */
public class CachedThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        CountDownLatch latch = new CountDownLatch(1000);
        for (int i = 0; i < 1000; i++) {
            cachedThreadPool.submit(() -> {
                try {
                    System.out.printf("线程【%s】正在等待……\n",Thread.currentThread().getName());
                    Thread.sleep(new Random().nextInt(100));
                    System.out.printf("线程【%s】开始执行\n",Thread.currentThread().getName());
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        try {
            latch.await();
            System.out.printf("线程【%s】继续执行\n",Thread.currentThread().getName());
            cachedThreadPool.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
