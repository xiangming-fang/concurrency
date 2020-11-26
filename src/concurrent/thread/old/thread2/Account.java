package concurrent.thread.old.thread2;

import java.util.Scanner;

/**
 * @ClassName: Account
 * @Description: description of class
 * @Author: Albert
 * @CreateDate: 2020/1/5 12:00
 */
public class Account {
    private String name;
    private Integer balance;

    public Account(String name,Integer balance){
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public Integer getBalance(){
        return balance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    /**
     * 取钱操作
     * @param account
     * @param howMuch
     */
    public void withdrawMoney(Account account,Integer howMuch){
        if (account.getBalance() >= howMuch){
            account.setBalance(account.getBalance() - howMuch);
            System.out.println("取钱成功，卡内余额还剩："+account.getBalance());
            return;
        }
        System.out.println("卡内余额不足，取钱失败");
    }



    public static void main(String[] args) {
        // 创建一个账户,名位张三,余额2000
        Account account = new Account("张三",2000);
        Scanner sc = new Scanner(System.in);
        int money = 0;
        try {
            money = Integer.parseInt(sc.next());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        account.withdrawMoney(account,money);
        System.out.println(account.getName()+"余额还有："+account.getBalance());
    }
}
