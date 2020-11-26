package concurrent.threadpool.fixedthreadpool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author: albert.fang
 * @date: 2020/11/25 13:02
 * @description: 固定线程池 - 线程协调工作之countdownlatch
 * countdownlatch 可以让main线程等到线程池里的子线程跑完任务之后再执行。根据具体任务数来决定count。而不是线程数
 */
public class FixedThreadPoolOfCountDownLatch {
    public static void main(String[] args) {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(6);
        // 具体的count并不是根据线程池中有几个线程来定的，而是根据任务数来定的
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            fixedThreadPool.submit(() -> {
                try {
                    System.out.printf("线程【%s】准备休息了\n",Thread.currentThread().getName());
                    TimeUnit.MILLISECONDS.sleep(10);
                    System.out.printf("线程【%s】休息好了\n",Thread.currentThread().getName());
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        try {
            countDownLatch.await();
            System.out.printf("线程【%s】终于等到线程池中的子线程都跑完了，开心~~~\n",Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            fixedThreadPool.shutdown();
            boolean terminated = fixedThreadPool.isTerminated();
            System.out.printf("线程池是否关闭【%s】\n",terminated);
        }
    }
}
