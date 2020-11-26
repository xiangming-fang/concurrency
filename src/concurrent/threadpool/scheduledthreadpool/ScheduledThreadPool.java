package concurrent.threadpool.scheduledthreadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author: albert.fang
 * @date: 2020/11/25 15:19
 * @description: 线程池中的线程，定时执行
 * feature：
 * 1、线程池中的线程，延时执行 schedule()
 * 2、线程池中的线程以任务开始时间点周期性执行 scheduleAndFixedRate
 * 3、线程池中的线程以任务结束时间点周期性执行 scheduleWithFixedDelay
 */
public class ScheduledThreadPool {
    public static void main(String[] args) {
        ScheduledExecutorService schduledPool = Executors.newScheduledThreadPool(10);
        // 1、延时用法
        schduledPool.schedule(() -> {
            System.out.printf("延时10ms之后，线程【%s】执行该任务\n",Thread.currentThread().getName());
        },100, TimeUnit.MILLISECONDS);

        // 2、周期性执行，指定第一次延迟多久，后面以多少周期执行，周期是以任务开始开始计时，不管任务自身执行多久
        schduledPool.scheduleAtFixedRate(() -> {
            System.out.printf("延时100ms之后，线程【%s】执行该任务，并且以后每隔1000ms执行一次（以任务开始开始计时，不管任务自身执行多久）\n",Thread.currentThread().getName());
        },10,1000,TimeUnit.MILLISECONDS);

        // 3、周期性执行，指定第一次延迟多久，后面以多少周期执行，周期是以任务结束开始计时
        schduledPool.scheduleWithFixedDelay(() -> {
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
        schduledPool.shutdown();
    }
}
