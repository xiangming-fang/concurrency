package concurrent.thread.old.thread4;

/**
 * @ClassName: DepositThread
 * @Description: description of class
 * @Author: Albert
 * @CreateDate: 2020/1/6 9:21
 */
public class DepositThread extends Thread {
    // 模拟用户账户
    private Account account;
    // 当前存款线程所希望存的钱数
    private double depositAmount;

    public DepositThread(String name , Account account, double depositAmount){
        super(name);
        this.account = account;
        this.depositAmount = depositAmount;
    }

    public void run(){
        for (int i = 0; i < 100; i++) {
            account.deposit(depositAmount);
        }
    }
}
