package concurrent.thread.thread5;

/**
 * @auther 方翔鸣
 * @date 2020/3/3 17:05
 * 传统的线程通信：wait()、notify()、notifyAll()
 */
public class Seven {
    public static void main(String[] args) {
        Money money = new Money();
        DrawMoney drawMoney = new DrawMoney(money);
        DepositMoney depositMoney = new DepositMoney(money);
        Thread thread = new Thread(drawMoney,"取钱者");
        Thread thread1 = new Thread(depositMoney,"存钱者");
        thread.start();
        thread1.start();
    }
}

class Money{
    // 余额
    private static int surplusMoney = 9000;

    Object obj = new Object();



    public void drawMoney(int drawMoney){

        synchronized (obj){
            if (surplusMoney <= 0){
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else {
                if (surplusMoney >= drawMoney){
                    surplusMoney = surplusMoney - drawMoney;
                    System.out.println("取款成功******");
                    System.out.println("账户余额******"+surplusMoney);
                    obj.notifyAll();
                }else {
                    System.out.println("账户余额不足");
                }
            }
        }

    }

    public void depositMoney(int depositMoney){
        synchronized (obj){
            if (surplusMoney > 0){
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else {
                surplusMoney += depositMoney;
                System.out.println("存款成功@@@@@@");
                System.out.println("账户余额@@@@@@："+surplusMoney);
                obj.notifyAll();
            }

        }
    }

    public static int getSurplusMoney() {
        return surplusMoney;
    }

    public static void setSurplusMoney(int surplusMoney) {
        Money.surplusMoney = surplusMoney;
    }
}

class DrawMoney implements Runnable{

    private Money money;


    public DrawMoney(Money money){
        this.money = money;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            money.drawMoney(4500);
        }
    }
}

class DepositMoney implements Runnable{
    private Money money;
    public DepositMoney(Money money){
        this.money = money;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            money.depositMoney(4500);
        }
    }
}