package concurrent.producterandcustomer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author: albert.fang
 * @date: 2020/9/23 17:18
 * @description: 用BlockingQueue实现生产者消费者模式
 */
public class ImplementByBlockingQueue {
    public static void main(String[] args) {
        BlockingQueue<Object> blockingQueue = new ArrayBlockingQueue<>(10);
        Runnable producter = () -> {
            while (true){
                try {
                    if (blockingQueue.size() == 10){
                        System.out.println("队列已满，阻塞生产，等待消费者消费~~~");
                    }
                    blockingQueue.put(new Object());
                    System.out.println(Thread.currentThread().getName() + "进来生产1个，还剩：" + blockingQueue.size() + " 个未消费");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(producter,"生产者1号").start();
//        new Thread(producter,"生产者2号").start();
        Runnable customer = () -> {
            while (true){
                try {
                    if (blockingQueue.size() == 0){
                        System.out.println("队列已空，阻塞消费，等待生产者生产~~~");
                    }
                    blockingQueue.take();
                    System.out.println(Thread.currentThread().getName() + "进来消费1个，还剩：" + blockingQueue.size() + " 个未消费");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(customer,"消费者1号").start();
//        new Thread(customer,"消费者2号").start();
    }
}
