package concurrent.thread.old.thread5;

/**
 * @auther 方翔鸣
 * @date 2020/3/6 18:05
 * 解决DieLock类中的思索问题
 */
public class SolveDieLock {
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
            }
            synchronized (obj2){
                System.out.println(Thread.currentThread().getName()+
                        "获取obj2同步监视器");
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
            }
            synchronized (obj1){
                System.out.println(Thread.currentThread().getName()+
                        "获取obj1同步监视器");
            }
        },"线程2").start();
    }
}
