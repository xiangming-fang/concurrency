package concurrent.threadpool.forkjoinpool;

import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * @author: albert.fang
 * @date: 2020/11/25 10:09
 * @description: 用countdownlatch来保证主线程等到提交给线程池中的任务完成之后再执行。
 * 协调线程的工作，是线程按照所需顺序来执行
 */
public class CoordinateThreadOfCountDownLatch {
    private static CountDownLatch latch;
    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool pool = new ForkJoinPool();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            if (s.equals("exit")) break;
            int peak = Integer.valueOf(s);
            latch = new CountDownLatch(peak);
            System.out.printf("countdownlatch value is 【%d】\n",latch.getCount());
            pool.submit(new Task(0,peak));
            latch.await();
            System.out.printf("【%s】线程终于等到ForkJoinPool线程池中的任务执行完了，哈哈哈，开心.\n",Thread.currentThread().getName());
        }
        pool.shutdown();
        boolean terminated = pool.isTerminated();
        System.out.printf("线程池是否关闭：【%s】\n",terminated);
    }

    static class Task extends RecursiveAction {

        int n;
        int front;

        Task(int front, int n){
            this.front = front;
            this.n = n;
        }

        @Override
        protected void compute() {
            if ((n - front) < 5){
                for (int i = front; i < n; i++) {
                    System.out.println(Thread.currentThread().getName() + " number:" + i + " 大帅比");
                    // 一定要放在这下面，逻辑的最后处
                    // 原因：最后一次进来，立马对latch减去1，那么外面的main线程就能立马被唤醒，立即执行main线程逻辑
                    latch.countDown();
                }
            }
            else {
                Task frontHalfTask = new Task(front,(front + n) /2);
                Task backHalfTask = new Task((front + n) / 2, n);
                frontHalfTask.fork();
                backHalfTask.fork();
            }
        }
    }
}
