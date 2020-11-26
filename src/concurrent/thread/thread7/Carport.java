package concurrent.thread.thread7;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: Carport
 * @Description: description of class
 * @Author: Albert
 * @CreateDate: 2020/1/7 8:50
 */
public class Carport {
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    private static int count = 3;


    // 三个车位,默认false,false代表可停车
    boolean[] carPorts = new boolean[3];

    /**
     * 车主停车
     */
    public void carPark(){
        lock.lock();
        for (int i = 0; i < carPorts.length; i++) {
            if (!carPorts[i]){
                carPorts[i] = true;
                System.out.println("车主已占用"+(i+1)+"号停车位");
                count--;
                System.out.println("还剩"+count+"个车位");
                condition.signalAll();
                break;
            }else if (carPorts[0] && carPorts[1] && carPorts[2]){
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        lock.unlock();
    }

    /**
     * 车主把车开走
     */
    public void leave(){
        lock.lock();
        for (int i = 0; i < carPorts.length; i++) {
            if (carPorts[i]){
                carPorts[i] = false;
                System.out.println("车主已将车开走,"+(i+1)+"号停车位可停");
                count++;
                System.out.println("还剩"+count+"个车位");
                condition.signalAll();
                break;
            }else if (!carPorts[0] && !carPorts[1] && !carPorts[2]){
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        lock.unlock();
    }
}
