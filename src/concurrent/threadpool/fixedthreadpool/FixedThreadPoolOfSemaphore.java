package concurrent.threadpool.fixedthreadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author: albert.fang
 * @date: 2020/11/25 11:16
 * @description: 固定线程池 - 线程协调工作之semaphore信号量，信号量里可设定具体几个permit许可证。
 * 信号量semaphore并不能保证线程（主线程和线程池中的子线程）有序的执行
 */
public class FixedThreadPoolOfSemaphore {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        ExecutorService executorService = Executors.newFixedThreadPool(6);
        Semaphore semaphore = new Semaphore(6);
        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> {
                try {
                    semaphore.acquire();
                    System.out.println("线程【" + Thread.currentThread().getName()+"】要睡觉了");
                    TimeUnit.MILLISECONDS.sleep(2);
                    System.out.println("线程【" + Thread.currentThread().getName()+"】睡醒了");
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
        semaphore.tryAcquire(6);
        System.out.printf("现在轮到【%s】",Thread.currentThread().getName());
        semaphore.release(6);
    }
}
