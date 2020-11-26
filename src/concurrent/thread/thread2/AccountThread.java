package concurrent.thread.thread2;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: AccountThread
 * @Description: description of class
 * @Author: Albert
 * @CreateDate: 2020/1/5 12:19
 */
public class AccountThread implements Runnable {

    private final ReentrantLock lock = new ReentrantLock();

    // 定义要保证线程安全的方法
    public void m(){
        // 加锁
        lock.lock();
        // 需要执行线程安全的代码
        lock.unlock();
    }

    private static int count = 10;

    @Override
    public void run() {
//        getLock();
        synchronized (this){
            System.out.print(Thread.currentThread().getName()+"  ####   ");
            Thread.currentThread().suspend();
            System.out.print(Thread.currentThread().getName()+"****   ");
            count--;
            System.out.println(count);
        }

    }
    public static synchronized void getLock(){
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count--;
        System.out.println(count);
    }
    public static void main(String[] args) {
        AccountThread accountThread = new AccountThread();
        Thread thread1 = new Thread(accountThread,"线程1");
        Thread thread2 = new Thread(accountThread,"线程2");
        Thread thread3 = new Thread(accountThread,"线程3");
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
