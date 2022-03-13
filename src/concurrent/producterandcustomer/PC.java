package concurrent.producterandcustomer;

import entity.User;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @ProjectName: concurrency
 * @Package: concurrent.producterandcustomer
 * @ClassName: PC
 * @Author: albert.fang
 * @Description: product and consumer
 * @Date: 2022/3/12 12:51
 */
public class PC {

    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingQueue<User> bq = new LinkedBlockingQueue<>();
        CountDownLatch cdl = new CountDownLatch(2);
        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                try {
                    bq.put(new User("fxm " + i));
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() ->{
            String name = Thread.currentThread().getName();
            while (true){
                try {
                    System.out.println(name + " 拿到 " + bq.take().toString());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
