package entity;

/**
 * @author: albert.fang
 * @date: 2020/12/2 10:52
 * @description: 用户信息，用于测试使用
 */
public class User {
    private String name;
    private int age;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
