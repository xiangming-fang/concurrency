package concurrent.threadpool.forkjoinpool;


import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

/**
 * @author: albert.fang
 * @date: 2020/11/24 17:54
 * @description: ForkJoinPool.getCommonPool()、无返回值
 */
public class CommonPool {
    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool pool = ForkJoinPool.commonPool();
        pool.submit(new Task(0,100));
        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.HOURS);
        System.out.println(Thread.currentThread().getName() + "【我是来捣乱的】");
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
