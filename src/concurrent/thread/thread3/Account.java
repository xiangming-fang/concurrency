package concurrent.thread.thread3;

/**
 * @ClassName: Account
 * @Description: description of class
 * @Author: Albert
 * @CreateDate: 2020/1/6 9:07
 */
public class Account {
    // 封装账户编号、账户余额的两个成员变量
    private String accountNo;
    private double balance;
    // 标识账户中是否已有存款的旗标
    private boolean flag = false;
    private Object obj = new Object();
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
    public  void draw(double drawAmount){
        synchronized (obj) {
            if (!flag) {
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                // 执行取钱操作
                System.out.println(Thread.currentThread().getName() + " 取钱：" + drawAmount);
                balance -= drawAmount;
                System.out.println("账户余额:" + balance);
                // 将标识账户是否已有存款的旗标设为false
                flag = false;
                // 唤醒其他线程
                obj.notifyAll();
            }
        }
    }

    /**
     * 存钱
     * @param depositAmount
     */
    public void deposit(double depositAmount){
        synchronized (obj){
            if (flag){
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else {
                System.out.println(Thread.currentThread().getName()+" 存钱："+depositAmount);
                balance += depositAmount;
                flag = true;
                obj.notifyAll();
            }
        }
    }
}
