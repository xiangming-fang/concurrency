package concurrent.thread.old.thread5;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Eight{
    public static void main(String[] args) {
        Account account = new Account(600);
        Draw draw = new Draw(account,200);
        Deposit deposit = new Deposit(account,200);
        Thread thread = new Thread(draw);
        Thread thread1 = new Thread(deposit);
        thread.start();
        thread1.start();
    }
}

class Account{
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    private static int susplusMoney;

    public Account(int susplusMoney){
        this.susplusMoney = susplusMoney;
    }

    public void depositMoney(int money){
        lock.lock();
        if(susplusMoney > 0){
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            susplusMoney += money;
            System.out.println("存款成功，当前余额："+susplusMoney);
            condition.signalAll();
        }
        lock.unlock();
    }

    public void drawMoney(int money){
        lock.lock();
        if (susplusMoney <= 0){
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else{
            susplusMoney -= money;
            System.out.println("取款成功，当前余额："+susplusMoney);
            condition.signalAll();
        }
        lock.unlock();
    }
}

class Draw implements Runnable{

    private Account account;

    private int drawMoney;

    public Draw(Account account,int drawMoney){
        this.account = account;
        this.drawMoney = drawMoney;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            account.drawMoney(drawMoney);
        }
    }
}

class Deposit implements Runnable{

    private Account account;
    private int depositMoney;

    public Deposit(Account account,int depositMoney){
        this.account = account;
        this.depositMoney = depositMoney;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            account.depositMoney(depositMoney);
        }
    }
}