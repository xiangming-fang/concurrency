package concurrent.thread.thread5;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @auther 方翔鸣
 * @date 2020/3/3 16:53
 * ReentrantLock 重入锁保证线程同步
 */
public class Six implements Runnable{
    private static int count = 520;
    private final ReentrantLock lock = new ReentrantLock();
    @Override
    public void run() {
        lock.lock();
        for (int i = 0; i < 10000; i++) {
            count++;
        }
        System.out.println(count);
        lock.unlock();
    }

    public static void main(String[] args) {
        Six six = new Six();
        Thread thread1 = new Thread(six,"线程1");
        Thread thread2 = new Thread(six,"线程2");
        Thread thread3 = new Thread(six,"线程3");
        System.out.println(thread1.getThreadGroup());
        System.out.println(thread2.getThreadGroup());
        System.out.println(thread3.getThreadGroup());
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
