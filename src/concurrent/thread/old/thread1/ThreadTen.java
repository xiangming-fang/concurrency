package concurrent.thread.old.thread1;

/**
 * @ClassName: ThreadTen
 * @Description: 线程学习10 两种创建线程方式的比较
 * @Author: 方翔鸣
 * @CreateDate: 2019/11/29 17:10
 */
public class ThreadTen {

    static class MyThread implements Runnable{
        private int i=0;
        public void run(){
            while(i<10){
                i++;
                System.out.println(i+"  MyThread.run "+Thread.currentThread().getName());
            }
        }
    }

    public static void main(String[] args) {
        MyThread myThread1 = new MyThread();
        MyThread myThread2 = new MyThread();
        MyThread myThread3 = new MyThread();
        Thread thread1 = new Thread(myThread1,"线程1");
        Thread thread2 = new Thread(myThread2,"线程2");
        Thread thread3 = new Thread(myThread3,"线程3");
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
