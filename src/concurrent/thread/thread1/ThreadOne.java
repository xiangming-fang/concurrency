package concurrent.thread.thread1;

/**
 * @ClassName: ThreadOne
 * @Description: 多线程学习1 继承Thread类和实现Runnable接口可创建线程
 * @Author: 方翔鸣
 * @CreateDate: 2019/11/27 13:35
 */
class Thread1 extends Thread{
    private static int  i=0;
    public Thread1(String name){
        super(name);
    }
    public void run(){
        synchronized (this){
            while(i<5){
                i++;
                System.out.println(i+"  MyThread.run "+this.getName());
            }
        }
    }
}

public class ThreadOne {
    public static void main(String[] args) {
        Thread1 mTh1 = new Thread1("A");
        Thread1 mTh2 = new Thread1("B");
        mTh1.start();
        mTh2.start();
    }
}
