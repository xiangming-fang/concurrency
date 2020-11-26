package concurrent.thread.thread4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: Account
 * @Description: description of class
 * @Author: Albert
 * @CreateDate: 2020/1/6 9:07
 */
public class Account {
    // 创建lock对象
    private final ReentrantLock lock = new ReentrantLock();
    // 用lock.newCondition()创建condition实例
    private final Condition condition = lock.newCondition();
    // 封装账户编号、账户余额的两个成员变量
    private String accountNo;
    private double balance;
    // 标识账户中是否已有存款的旗标
    private boolean flag = false;
    public Account(){}
    // 构造器
    public Account(String accountNo,double balance){
        this.accountNo = accountNo;
        this.balance = balance;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    // 因为账户余额不允许随便修改，所以只为balance提供getter方法
    public double getBalance(){
        return this.balance;
    }

    /**
     *  取钱操作
     * @param drawAmount
     */
    public void draw(double drawAmount){
        lock.lock();
        if (!flag){
            try {
                condition.await();
                condition.signal();
                condition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
             // 执行取钱操作
            System.out.println(Thread.currentThread().getName() + " 取钱："+drawAmount);
            balance -= drawAmount;
            System.out.println("账户余额:"+balance);
            // 将标识账户是否已有存款的旗标设为false
            flag = false;
            // 唤醒其他线程
            condition.signalAll();
        }
        lock.unlock();
    }

    /**
     * 存钱
     * @param depositAmount
     */
    public void deposit(double depositAmount){
        lock.lock();
        if (flag){
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println(Thread.currentThread().getName()+" 存钱："+depositAmount);
            balance += depositAmount;
            flag = true;
            condition.signalAll();
        }
        lock.unlock();
    }
}
