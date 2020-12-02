package concurrent.thread.thread1;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: albert.fang
 * @date: 2020/12/1 12:47
 * @description: 可重入锁概念讲解
 * 可重入锁：一个持有该锁的线程在不释放该锁的情况下 可执行多个持有该锁方法或代码块
 * 注意点：如果一个线程获得该锁的次数和释放该锁的次数不一致，那么会导致死锁
 */
public class ReentrantLockDemo {
    private static ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(()->{
                try {
                    System.out.println("正在休息等待获取锁");
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.lock();
                System.out.println("获得到了可重入锁,执行逻辑");
                lock.lock();
                System.out.println("再次得到，进入");
                lock.unlock();
            }).start();
        }
    }
}
