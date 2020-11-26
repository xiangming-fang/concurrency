package concurrent.thread.old.thread1;

/**
 * @ClassName: ThreadSeven
 * @Description: 线程学习7 守护线程
 * @Author: 方翔鸣
 * @CreateDate: 2019/11/28 14:54
 */
public class ThreadSeven implements Runnable{
    @Override
    public void run() {
        while (true){
            System.out.println(Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getPriority());
        Thread thread = new Thread(new ThreadSeven(),"美丽的线程");
        thread.setDaemon(true);
        System.out.println(thread.getPriority());
        thread.start();
//        concurrent.join();
    }
}
