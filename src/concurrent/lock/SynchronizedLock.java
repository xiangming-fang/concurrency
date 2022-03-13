package concurrent.lock;

/**
 * @ProjectName: concurrency
 * @Package: concurrent.lock
 * @ClassName: SynchronizedLock
 * @Author: albert.fang
 * @Description: Synchronized 锁
 * @Date: 2022/3/13 16:14
 */
public class SynchronizedLock {

    public static final Object lock = new Object();

    public static void main(String[] args) {
        synchronized (lock){
            System.out.println("获得到了锁lock");
        }
    }
}
