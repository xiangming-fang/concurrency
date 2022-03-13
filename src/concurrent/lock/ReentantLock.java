package concurrent.lock;

/**
 * @ProjectName: concurrency
 * @Package: concurrent.lock
 * @ClassName: ReentantLock
 * @Author: albert.fang
 * @Description: 可重入锁
 * @Date: 2022/3/13 15:49
 */
public class ReentantLock {
    public static void main(String[] args) {
        // synchronized 是可重入锁
        synchronized (ReentantLock.class){
            System.out.println("获得第一个");
            synchronized (ReentantLock.class){
                System.out.println("获得第二个");
            }
        }
    }
}
