package concurrent.thread.thread1;

/**
 * @ClassName: ThreadEleven
 * @Description: 线程学习11 锁
 * @Author: 方翔鸣
 * @CreateDate: 2019/12/2 17:08
 */
public class ThreadEleven implements Runnable{

    Object o = new Object();

    private int a = 0;


    @Override
    public void run() {
        while(a < 100){
            a++;
            System.out.println(Thread.currentThread().getName()+"------"+a);
        }
    }

    public static void main(String[] args) {
        ThreadEleven threadEleven = new ThreadEleven();
        Thread thread1 = new Thread(threadEleven,"线程1");
        Thread thread2 = new Thread(threadEleven,"线程2");
        Thread thread3 = new Thread(threadEleven,"线程3");
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
