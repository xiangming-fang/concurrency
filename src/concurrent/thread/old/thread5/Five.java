package concurrent.thread.old.thread5;

/**
 * @auther 方翔鸣
 * @date 2020/3/3 16:24
 */
public class Five implements Runnable {
    private static int love = 520;
    @Override
    public void run() {
        synchronized (Five.class){
            for (int i = 0; i < 10000; i++) {
                love++;
            }
            try {
                // sleep(),yield(),suspend()方法会让出cpu时间片，但是不会让出同步监视器
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(love);
        }
    }

    public static void main(String[] args) {
        // 多个target对象创建多线程，资源共享并发，可用当前类作为同步监视器
        Five five1 = new Five();
        Five five2 = new Five();
        Five five3 = new Five();
        Thread thread1 = new Thread(five1,"线程1");
        Thread thread2 = new Thread(five2,"线程2");
        Thread thread3 = new Thread(five3,"线程3");
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
