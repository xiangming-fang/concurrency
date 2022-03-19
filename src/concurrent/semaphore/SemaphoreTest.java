package concurrent.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @ProjectName: concurrency
 * @Package: concurrent.semaphore
 * @ClassName: SemaphoreTest
 * @Author: albert.fang
 * @Description: 信号量：控制可以进入临界区的线程数
 * @Date: 2022/3/17 17:28
 */
public class SemaphoreTest {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        ExecutorService executor = Executors.newFixedThreadPool(19);
        for (int i = 0; i < 100; i++) {
            executor.execute(() -> {
                try {
                    if (semaphore.tryAcquire(2)) {
                        System.out.println(Thread.currentThread().getName() + " 进来了");
                        Thread.sleep(3);
                        System.out.println(Thread.currentThread().getName() + " 释放信号量");
                        semaphore.release(1);
                    }else {
                        System.out.println(Thread.currentThread().getName() + "没有拿到许可证");
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        System.out.println("主线程");
        executor.shutdown();
    }
}
