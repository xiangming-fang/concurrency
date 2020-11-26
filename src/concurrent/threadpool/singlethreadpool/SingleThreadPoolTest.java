package concurrent.threadpool.singlethreadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: albert.fang
 * @date: 2020/11/25 15:44
 * @description: 单例线程池：单例线程池为啥不叫pool而是executor，因为就只有一个嘛，就不叫的那么大了（pool池），就叫executor（执行者）
 * feature:
 * 1、核心线程数和最大线程数始终只要一个
 */
public class SingleThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            singleThreadExecutor.submit(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("线程【%s】开始执行任务\n",Thread.currentThread().getName());
            });
        }
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        singleThreadExecutor.shutdown();
    }
}
