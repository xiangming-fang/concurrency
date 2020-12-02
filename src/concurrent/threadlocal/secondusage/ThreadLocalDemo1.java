package concurrent.threadlocal.secondusage;

import entity.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: albert.fang
 * @date: 2020/11/27 18:54
 * @description: threadlocal的第二种用法示例
 * feature：
 * 1、每个线程内需要独立保存的信息，例如用户信息。
 * 2、避免一次请求对相同信息的多次传参。
 */
public class ThreadLocalDemo1 {
    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            if (i == 0){
                new Thread(()->{
                    User user = new User("albert.fang");
                    UserContextHolder.userHolder.set(user);
                    new Service1().service1();
                }).start();
            }
            else {
                new Thread(()->{
                    new Service2().service2();
                }).start();
            }
        }
    }
}

class Service1{
    public void service1(){
        User user = UserContextHolder.userHolder.get();
        System.out.println("当前登录的用户是：" + user.getName());
        new Service2().service2();
    }
}

class Service2{
    public void service2(){
        System.out.println("改变当前登录的用户信息……");
        UserContextHolder.userHolder.set(new User("林就远"));
        User user = UserContextHolder.userHolder.get();
        System.out.println("当前登录的用户变为：" + user.getName());
        // 移除ThreadLocalMap中的一个Entry，避免造成内存泄露：当一个对象不再有用的时候，内存却不被回收，就造成了内存泄露
        UserContextHolder.userHolder.remove();
    }
}


