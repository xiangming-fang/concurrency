package concurrent.thread.old.thread1;

/**
 * @ClassName: ThreadTwo
 * @Description: 多线程学习2 t.join() 等待t线程终止
 * @Author: 方翔鸣
 * @CreateDate: 2019/11/27 14:35
 */
class Thread3 extends Thread{
    private String name;
    public Thread3(String name){
        super(name);
        this.name = name;
    }

    /**
     * 线程执行的代码
     */
    public void run(){
        System.out.println(Thread.currentThread().getName() + "线程运行开始");
        for (int i = 0; i < 5; i++) {
            System.out.println("子线程"+name+"运行:"+i);
            try {
                Thread.sleep((int)Math.random() * 10);
//                System.out.println("子线程"+name+ "线程阻塞"+i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "线程运行结束");
    }
}

public class ThreadTwo {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName()+"主线程开始创建");
        Thread3 thread1 = new Thread3("A");
        Thread3 thread2 = new Thread3("B");
        thread1.start();
        thread2.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // join()方法了之后，thread1 和 thread2都在Main主线程之前执行完毕
        System.out.println(Thread.currentThread().getName()+"主线程运行结束");
    }
}
