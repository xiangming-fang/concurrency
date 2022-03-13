package concurrent.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ProjectName: concurrency
 * @Package: concurrent.lock
 * @ClassName: LockLock
 * @Author: albert.fang
 * @Description: Lock锁
 * @Date: 2022/3/13 16:23
 */
public class LockLock {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        try {
            lock.lock();
            System.out.println("lock lock");
        } finally {
            lock.unlock();
        }

    }
}
