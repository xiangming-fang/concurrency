package concurrent.thread.thread2;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @ClassName: Thread1
 * @Description: description of class
 * @Author: Albert
 * @CreateDate: 2020/1/3 13:28
 */
public class Thread1 implements Callable{
    @Override
    public Object call() throws Exception {
        System.out.println(Thread.currentThread().getName());
        return Thread.currentThread().getName();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Thread1 thread1 = new Thread1();

        FutureTask<Integer> futureTask = new FutureTask<Integer>(thread1);
        Thread thread = new Thread(futureTask,"有返回值的线程1");
        Thread thread2 = new Thread(futureTask,"有返回值的线程2");
        thread.start();

        thread2.start();
        thread.stop();
        System.out.println(futureTask.get());
    }
}
