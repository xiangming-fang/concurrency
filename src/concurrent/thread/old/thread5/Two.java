package concurrent.thread.old.thread5;

/**
 * @ClassName: Two
 * @Description: description of class
 * @Author: Albert
 * @CreateDate: 2020/1/6 16:04
 */
public class Two extends Thread {

    public Two(String name){
        super.setName(name);
    }

    @Override
    public void run(){
//        while(true){
            System.out.println("why you are so 傻逼");
            System.out.println(this.getName());
            Thread.yield();
//        }
    }
    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        Two two = new Two("我也是线程名");
        Two two1 = new Two("我是守护线程呐");
        two1.setDaemon(true);
//        System.out.println(two1.getPriority());
//        System.out.println(two.getPriority());
        two1.start();
        two.start();
//        two.join();
//        Thread.sleep(10);
        System.out.println(Thread.currentThread().getName());
    }
}
