package concurrent.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ProjectName: concurrency
 * @Package: concurrent.cyclicbarrier
 * @ClassName: CyclicBarrierTest
 * @Author: albert.fang
 * @Description: 筹齐指定小伙伴再去一起玩
 * @Date: 2022/3/17 18:08
 */
public class CyclicBarrierTest {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(4,() -> {
            System.out.println("凑齐四个小伙伴了, go on");
        });
        ExecutorService executorService = Executors.newFixedThreadPool(12);
        for (int i = 0; i < 100; i++) {
            executorService.execute(() -> {
                try {
                    String s = Thread.currentThread().getName() + " 进来了";
                    System.out.println(s);
                    cyclicBarrier.await();
                    Thread.sleep(300);
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
