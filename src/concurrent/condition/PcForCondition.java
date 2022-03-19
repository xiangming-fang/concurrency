package concurrent.condition;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ProjectName: concurrency
 * @Package: concurrent.condition
 * @ClassName: PcForCondition
 * @Author: albert.fang
 * @Description: producter and consumer for condition
 * @Date: 2022/3/19 11:12
 */
public class PcForCondition {

    ReentrantLock lock = new ReentrantLock();
    Condition headCondition = lock.newCondition();
    Condition tailCondition = lock.newCondition();
    Queue<String> queue = new LinkedList<>();
    private final int maxsize = 16;

    public void producter() {
        try {
            lock.lock();
            while (maxsize == queue.size()) {
                tailCondition.await();
            }
            System.out.println( Thread.currentThread().getName() + " 生产产品");
            queue.add( Thread.currentThread().getName() + " 生产产品");
            headCondition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void consumer(){
        try{
            lock.lock();
            while (queue.size() == 0){
                headCondition.await();
            }
            String remove = queue.remove();
            System.out.println(Thread.currentThread().getName() + "消耗产品：【 " + remove + " 】");
            tailCondition.signalAll();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        PcForCondition blockingQueue = new PcForCondition();
        CountDownLatch countDownLatch = new CountDownLatch(4);
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                blockingQueue.producter();
            }
            countDownLatch.countDown();
        });
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                blockingQueue.producter();
            }
            countDownLatch.countDown();
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                blockingQueue.consumer();
            }
            countDownLatch.countDown();
        });
        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                blockingQueue.consumer();
            }
            countDownLatch.countDown();
        });
        thread.start();
        thread1.start();
        thread2.start();
        thread3.start();
        countDownLatch.await();
        System.out.println("主线程执行结束" + blockingQueue.queue.size());
    }


}
