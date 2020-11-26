package concurrent.thread.old.thread6;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: Print
 * @Description: description of class
 * @Author: Albert
 * @CreateDate: 2020/1/6 16:47
 */
public class Print {

    // 创建lock对象
    private final ReentrantLock lock = new ReentrantLock();
    // 创建condition实例
    private final Condition condition = lock.newCondition();

    // 打印数字还是字母
    private boolean flag = true;

    // 第几次打印字母
    private static int countZM = 1;

    // 第几次打印数字
    private static int countNum = 1;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    /**
     * 打印26个字母
     */
    public void printZM(){
        lock.lock();
            if (flag){
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else {
                String str = "abcdefghijklmnopqrstuvwxyz";
                char[] chars = str.toCharArray();
                System.out.print(chars[countZM-1]);
                countZM++;
                this.setFlag(true);
                condition.signalAll();
            }
        lock.unlock();
    }

    public void printNum(){
        lock.lock();
            if (!flag){
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else {
                System.out.print(countNum*2 - 1);
                System.out.print(countNum*2);
                countNum++;
                this.setFlag(false);
                condition.signalAll();
            }
        lock.unlock();
    }
}
