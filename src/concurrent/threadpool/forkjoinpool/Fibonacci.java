package concurrent.threadpool.forkjoinpool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author: albert.fang
 * @date: 2020/11/24 17:57
 * @description: 利用ForkJoinPool来完成斐波那契数列（有返回值）
 */
public class Fibonacci {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        for (int i = 0; i <= 10; i++) {
            FiboTask fiboTask = new FiboTask(i);
            pool.submit(fiboTask);
            try {
                System.out.println(fiboTask.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    static class FiboTask extends RecursiveTask<Integer>{

        int n;

        public FiboTask(int n) {
            this.n = n;
        }

        @Override
        protected Integer compute() {
            if (n < 2 && n >= 0){
                return n;
            }
            else {
                FiboTask first = new FiboTask(n - 1);
                FiboTask second = new FiboTask(n - 2);
                first.fork();
                second.fork();
                return first.join() + second.join();
            }
        }
    }
}
