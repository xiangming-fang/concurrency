package concurrent.thread.thread1;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: albert.fang
 * @date: 2020/12/1 14:47
 * @description: 自旋锁
 */
public class SpinLockDemo {
    public static void main(String[] args) {
        AtomicInteger integer = new AtomicInteger();
        integer.getAndSet(34);
        System.out.println(integer.get());
        System.out.println(integer.compareAndSet(34, 4));
        System.out.println(integer.get());
    }
}
