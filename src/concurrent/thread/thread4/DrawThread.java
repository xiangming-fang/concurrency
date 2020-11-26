package concurrent.thread.thread4;

/**
 * @ClassName: DrawThread
 * @Description: description of class
 * @Author: Albert
 * @CreateDate: 2020/1/6 9:21
 */
public class DrawThread extends Thread {
    // 模拟用户账户
    private Account account;
    // 当前取钱线程所希望取的钱数
    private double drawAmount;

    public DrawThread(String name , Account account, double drawAmount){
        super(name);
        this.account = account;
        this.drawAmount = drawAmount;
    }
    // 重复执行100次取钱操作
    public void run(){
        for (int i = 0; i < 100; i++) {
            account.draw(drawAmount);
        }
    }
}
