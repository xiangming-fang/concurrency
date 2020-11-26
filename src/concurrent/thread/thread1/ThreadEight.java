package concurrent.thread.thread1;

/**
 * @ClassName: ThreadEight
 * @Description: 线程学习8 线程优先级
 * @Author: 方翔鸣
 * @CreateDate: 2019/11/28 16:12
 */
public class ThreadEight implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(new ThreadEight(),"魅力线程1");
        Thread thread2 = new Thread(new ThreadEight(),"魅力线程2");
        Thread thread3 = new Thread(new ThreadEight(),"魅力线程3");
        thread1.setPriority(1);
        thread2.setPriority(5);
        thread3.setPriority(10);
        thread1.start();
        thread2.start();
        thread3.start();
        Thread.currentThread().interrupt();
    }
}
