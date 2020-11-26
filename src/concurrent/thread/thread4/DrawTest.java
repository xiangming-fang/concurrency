package concurrent.thread.thread4;

/**
 * @ClassName: DrawTest
 * @Description: description of class
 * @Author: Albert
 * @CreateDate: 2020/1/6 9:28
 */
public class DrawTest {
    public static void main(String[] args) {
        // 创建一个账户
        Account account = new Account("123345",0);
        new DrawThread("取钱者",account,890).start();
        new DepositThread("存钱者1号",account,890).start();
        new DepositThread("存钱者2号",account,890).start();
        new DepositThread("存钱者3号",account,890).start();
    }
}
