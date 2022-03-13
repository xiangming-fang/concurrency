package concurrent.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ProjectName: concurrency
 * @Package: concurrent.lock
 * @ClassName: ReadWriteLock
 * @Author: albert.fang
 * @Description: 读写锁
 * @Date: 2022/3/13 17:12
 */
public class ReadWriteLock {

    static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    static ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    static ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(ReadWriteLock::testReadLock).start();
        }
    }

    public static void testReadLock(){
        try {
            readLock.lock();
            System.out.println(Thread.currentThread().getName() + " read");
            Thread.sleep(100);
            System.out.println(Thread.currentThread().getName() + " exit");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
    }

}
