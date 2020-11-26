package concurrent.threadpool.singleThreadscheduledExecutor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author: albert.fang
 * @date: 2020/11/25 15:55
 * @description: 单例、定时、线程池；为啥这个线程池也是叫Executor，因为它也是只有一个
 * 1、和scheduledThreadPool类似，区别是，SingleThreadScheduledExecutor只有一个线程
 */
public class SingleThreadScheduledExecutor {
    public static void main(String[] args) {
        ScheduledExecutorService singleScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        // 1、延时用法
        singleScheduledExecutor.schedule(() -> {
            System.out.printf("延时10ms之后，线程【%s】执行该任务\n",Thread.currentThread().getName());
        },100, TimeUnit.MILLISECONDS);

        // 2、周期性执行，指定第一次延迟多久，后面以多少周期执行，周期是以任务开始开始计时，不管任务自身执行多久
        singleScheduledExecutor.scheduleAtFixedRate(() -> {
            System.out.printf("延时100ms之后，线程【%s】执行该任务，并且以后每隔1000ms执行一次（以任务开始开始计时，不管任务自身执行多久）\n",Thread.currentThread().getName());
        },10,1000,TimeUnit.MILLISECONDS);

        // 3、周期性执行，指定第一次延迟多久，后面以多少周期执行，周期是以任务结束开始计时
        singleScheduledExecutor.scheduleWithFixedDelay(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("延时100ms之后，线程【%s】执行该任务，并且以后每隔1000ms执行一次（以任务结束开始计时）\n",Thread.currentThread().getName());
        },10,1000,TimeUnit.MILLISECONDS);

        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        singleScheduledExecutor.shutdown();
    }
}
