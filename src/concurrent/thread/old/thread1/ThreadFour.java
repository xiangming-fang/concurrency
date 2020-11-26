package concurrent.thread.old.thread1;

/**
 * @ClassName: ThreadFour
 * @Description: 多线程学习4 wait()和notify() 三线程打印ABC
 * @Author: 方翔鸣
 * @CreateDate: 2019/11/27 15:47
 */
public class ThreadFour implements Runnable{

    private String name;
    private Object prev;
    private Object self;

    private ThreadFour(String name,Object prev,Object self){
        this.name = name;
        this.prev = prev;
        this.self = self;
    }
    @Override
    public void run() {
        int count = 10;
        while (count > 0){
            synchronized (prev){
                synchronized (self){
                    System.out.println(name+System.currentTimeMillis());
                    count--;
                    self.notify();
                }
                try {
                    prev.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        ThreadFour thread1 = new ThreadFour("A",c,a);
        ThreadFour thread2 = new ThreadFour("B",a,b);
        ThreadFour thread3 = new ThreadFour("C",b,c);
        new Thread(thread1).start();
        Thread.sleep(100);
        new Thread(thread2).start();
        Thread.sleep(100);
        new Thread(thread3).start();
        Thread.sleep(100);
    }
}
