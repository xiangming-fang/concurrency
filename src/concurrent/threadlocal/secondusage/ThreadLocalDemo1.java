package concurrent.threadlocal.secondusage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: albert.fang
 * @date: 2020/11/27 18:54
 * @description: threadlocal的第二种用法示例
 */
public class ThreadLocalDemo1 {
    public static void main(String[] args) {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
//        for (int i = 0; i < 10; i++) {
            fixedThreadPool.submit(() -> {
                for (int j = 0; j < 100; j++) {
                    System.out.printf("【%s】--- execute tasks\n",Thread.currentThread().getName());
                }
            });
//        }
        fixedThreadPool.shutdown();
    }
}
