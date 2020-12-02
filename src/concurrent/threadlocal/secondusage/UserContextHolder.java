package concurrent.threadlocal.secondusage;

import entity.User;

/**
 * @author: albert.fang
 * @date: 2020/12/2 10:53
 * @description: 当前登录用户在上下文中持有什么
 */
public class UserContextHolder {
    static ThreadLocal<User> userHolder = new ThreadLocal<>();
}
