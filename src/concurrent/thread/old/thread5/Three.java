package concurrent.thread.old.thread5;

/**
 * @auther 方翔鸣
 * @date 2020/3/3 15:29
 */
public class Three implements Runnable {
    @Override
    public void run() {
        System.out.println("why you are so 傻逼");
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        Three three = new Three();
        Thread thread = new Thread(three,"我是线程名");
        thread.start();
    }
}
