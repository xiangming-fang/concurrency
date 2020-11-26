package concurrent.thread.thread2;

/**
 * @ClassName: Thread2
 * @Description: description of class
 * @Author: Albert
 * @CreateDate: 2020/1/5 11:22
 */
public class Thread2 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName()+"开始");
        Thread2 thread2 = new Thread2();
        Thread thread = new Thread(thread2,"前台线程");
        thread.start();
        try {
            thread.join(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"结束");
    }
}
