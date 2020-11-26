package concurrent.thread.thread5;

/**
 * @auther 方翔鸣
 * @date 2020/3/3 16:13
 */
public class Four implements Runnable {

    private static int num = 520;
    @Override
    public void run() {
        synchronized (this){
            for (int i = 0; i < 100000; i++) {
                num++;
            }
            System.out.println(num);
        }
    }

    public static void main(String[] args) {
        // 多线程共享资源的并发
        // 同一target对象创建多个线程，可用this做同步监视器
        Four four = new Four();
        Thread thread1 = new Thread(four,"线程1");
        Thread thread2 = new Thread(four,"线程2");
        Thread thread3 = new Thread(four,"线程3");
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
