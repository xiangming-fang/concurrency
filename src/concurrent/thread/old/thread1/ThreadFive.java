package concurrent.thread.old.thread1;

/**
 * @ClassName: ThreadFive
 * @Description: 多线程学习5 volatile 不保证原子性，i++最为明显,其实要分成3步：
 * 1）读取volatile变量值到local；
 * 2）增加变量的值；
 * 3）把local的值写回，让其它的线程可见。
 * 容易脏读丢失数据
 * @Author: 方翔鸣
 * @CreateDate: 2019/11/28 8:52
 */
class Thread5 extends Thread{

    @Override
    public void run() {
        System.out.println("线程要执行的run方法");
    }

    public void start(){
        System.out.println("重写了start方法");
    }
}

public class ThreadFive {
    private static volatile int value = 0;

    private static class Thead6 implements Runnable{

        @Override
        public void run() {
            int count = 0;
            while (count < 4000){
                value ++;
                count ++;
            }
        }

    }
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Thead6(),"实现了Runable接口的线程1");
        Thread thread2 = new Thread(new Thead6(),"实现了Runable接口的线程2");
        thread1.start();
        thread2.start();
        thread1.join();
        System.out.println(value);
    }
}
