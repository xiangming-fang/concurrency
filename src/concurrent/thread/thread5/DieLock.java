package concurrent.thread.thread5;

/**
 * @auther 方翔鸣
 * @date 2020/3/6 17:54
 * 测试死锁
 * 死锁：由两个或两个以上的进程（线程）争夺同一资源造成的无限阻塞。
 */
public class DieLock {
    private static final Object obj1 = new Object();
    private static final Object obj2 = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (obj1){
                System.out.println(Thread.currentThread().getName()+"获取obj1" +
                        "同步监视器");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (obj2){
                    System.out.println(Thread.currentThread().getName()+
                            "获取obj2同步监视器");
                }
            }
        },"线程1").start();
        new Thread(() -> {
            synchronized (obj2){
                System.out.println(Thread.currentThread().getName()+"获取obj2" +
                        "同步监视器");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (obj1){
                    System.out.println(Thread.currentThread().getName()+
                            "获取obj1同步监视器");
                }
            }
        },"线程2").start();
    }
}
