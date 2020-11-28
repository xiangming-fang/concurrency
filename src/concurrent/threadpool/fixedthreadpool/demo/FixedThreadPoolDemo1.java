package concurrent.threadpool.fixedthreadpool.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @auther：albert.fang
 * @date：2020/11/28 12:04
 * @description：测试单个提交给线程池的任务是线程池所有任务同时执行的还是单个线程执行的
 * result：通过submit提交给线程池的任务是给线程池里的单个线程执行的，而不是线程池中好几个任务一起执行该任务
 */
public class FixedThreadPoolDemo1 {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        pool.submit(()->{
            for (int i = 0; i < 100; i++) {
                System.out.printf("线程【%s】执行\n",Thread.currentThread().getName());
            }
        });
        pool.shutdown();
    }
}
