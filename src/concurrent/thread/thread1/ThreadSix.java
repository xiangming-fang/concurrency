package concurrent.thread.thread1;

/**
 * @ClassName: ThreadSix
 * @Description: 多线程学习6 interrupt
 * @Author: 方翔鸣
 * @CreateDate: 2019/11/28 10:00
 */
public class ThreadSix implements Runnable{


    @Override
    public void run() {
        System.out.println("开始执行run方法");
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(1000);
                System.out.println("线程正常休眠结束");
            } catch (InterruptedException e) {
                System.out.println("线程被打断休眠");
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(new ThreadSix(),"好看的线程");
        thread1.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 终端线程
        thread1.interrupt();
    }
}
