package concurrent.threadpool.fixedthreadpool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author: albert.fang
 * @date: 2020/11/25 13:36
 * @description: countdownlatch2：先阻塞线程池中执行任务的子线程，通过主线程来唤醒子线程的抢夺
 * countdownlatch：阻塞一个或一组线程，等到预设的条件达成发生。
 */
public class FixedThreadPoolOfCountDownLatch2 {
    public static void main(String[] args) {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(1);
        for (int i = 0; i < 10; i++) {
            fixedThreadPool.submit(() -> {
                try {
                    System.out.printf("【%s】线程进来了，可是马上就要被阻塞了~\n",Thread.currentThread().getName());
                    latch.await();
                    System.out.printf("【%s】线程被唤醒了，开心~\n",Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        try {
            // 等待6秒，以至于让线程池中的线程都被阻塞
            TimeUnit.SECONDS.sleep(6);
            latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            fixedThreadPool.shutdown();
            try {
                boolean isshutdown = fixedThreadPool.awaitTermination(1, TimeUnit.HOURS);
                System.out.printf("线程池是否被关闭【%s】",isshutdown);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
