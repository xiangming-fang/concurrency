package concurrent.threadpool.fixedthreadpool;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author: albert.fang
 * @date: 2020/11/25 14:15
 * @description: 协调线程工作：cyclicbarrier
 * cyclicbarrier：作用于线程。
 * countdownlatch：作用于事件、动作、任务。
 */
public class FixedThreadPoolOfCyclicBarrier {
    public static void main(String[] args) {
        // 第二个参数相当于回调函数，等到相应线程之后触发执行
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> {
            System.out.printf("已经等到了【%d】个线程啦，可以开始走相应逻辑啦\n", 3);
        });
        CountDownLatch latch = new CountDownLatch(30);
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(21);
        for (int i = 0; i < 31; i++) {
            fixedThreadPool.submit(new Task(i+1,cyclicBarrier,latch));
        }
        try {
            latch.await();
            System.out.printf("线程【%s】继续走\n",Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            fixedThreadPool.shutdown();
            // 等待特定事件，制造异常.
            // 当有cyclicbarrier调用await阻塞线程时，这是调用reset方法，重置cyclicbarrier使其抛出brokenbarrierException
            try {
                Thread.sleep(1200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cyclicBarrier.reset();
        }
    }
    static class Task implements Runnable{

        int no;
        CyclicBarrier barrier;
        CountDownLatch latch;

        public Task(int no,CyclicBarrier barrier,CountDownLatch latch) {
            this.no = no;
            this.barrier = barrier;
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                System.out.printf("学号为【%d】的学生，走向校门口的3人共骑单车\n",no);
                Thread.sleep(new Random().nextInt(1000));
                System.out.printf("学号为【%d】的学生，已经到了校门口，等待其他人到达\n",no);
                // 要阻塞parties参数 - 1 这么多个线程
                barrier.await();
                System.out.printf("学号为【%d】的学生，已经骑上自行车了\n",no);
                latch.countDown();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
