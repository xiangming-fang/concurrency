package concurrent.thread.old.thread1;

/**
 * @ClassName: ThreadThree
 * @Description: 多线程学习3 t.yield() 暂停当前正在执行的线程对象，并执行其他线程.让线程状态从运行——就绪状态。可是并不能100%保证t线程的让步，因为t.yield()使得t线程重新回到就绪状态，和其他就绪状态线程一样等待cpu的调用。(谁也说不准，cpu下次的调用还会不会是它)
 * @Author: 方翔鸣
 * @CreateDate: 2019/11/27 14:57
 */
class Thread4 extends Thread{
    public Thread4(String name){
        super(name);
    }
    public void run(){
        for (int i = 0; i < 50; i++) {
            if (i == 30){
                this.yield();
            }
            System.out.println(""+this.getName()+"------"+i);
        }
    }
}

public class ThreadThree {
    public static void main(String[] args) {
        Thread4 thread1 = new Thread4("曹孟德");
        Thread4 thread2 = new Thread4("张飞");
        thread1.setPriority(Thread.MAX_PRIORITY);
        thread2.setPriority(Thread.MIN_PRIORITY);
        thread1.start();
        thread2.start();
    }
}
