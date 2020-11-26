package concurrent.thread.thread5.objectSychronized;

/**
 * @auther 方翔鸣
 * @date 2020/3/13 18:29
 * a.f(),b.f()方法。a 调用的同时，b会不会走进去?  f()为加了sychronized关键字的普通方法
 */
public class ObjectSynchronizedTest {

    private String name;

    public ObjectSynchronizedTest(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public synchronized void f(ObjectSynchronizedTest ost) throws InterruptedException {
        System.out.println(ost.getName()+"进来了");
        Thread.sleep(10000);
    }

    public static void main(String[] args) throws InterruptedException {
        ObjectSynchronizedTest ost1 = new ObjectSynchronizedTest("A对象");
        ObjectSynchronizedTest ost2 = new ObjectSynchronizedTest("B对象");
        ost1.f(ost1);
        ost2.f(ost2);
    }
}
