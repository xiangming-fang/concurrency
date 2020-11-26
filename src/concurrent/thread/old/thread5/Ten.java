package concurrent.thread.old.thread5;


/**
 * @auther 方翔鸣
 * @date 2020/3/3 19:28
 * practise：假设车库有3个车位（可以用boolean[]数组来表示车库）可以停车，写一个程序模拟多个用户开车离开、停车入库的效果。注意：车位有车时不能停车。
 */
public class Ten {
    public static void main(String[] args) {
        CarPort carPort = new CarPort();
        UseCar useCar = new UseCar(carPort);
        UseCar useCar1 = new UseCar(carPort);
        LeaveCar leaveCar = new LeaveCar(carPort);
        Thread thread = new Thread(useCar);
        Thread thread2 = new Thread(useCar1);
        Thread thread1 = new Thread(leaveCar);
        thread.start();
        thread1.start();
        thread2.start();
    }
}

class CarPort{
    // false 表示该车位无车
    boolean[] carPortStatus = {false,false,false};
    Object obj = new Object();
    // 停车
    public void useCarPort(){
        synchronized (obj){
            int a = 0;
            for (int i = 0; i < carPortStatus.length; i++) {
                if (carPortStatus[i] == false){
                    carPortStatus[i] = true;
                    System.out.println("***车位 "+i+" 占用");
                    obj.notify();
                    break;
                }
                if (carPortStatus[i] = true){
                    a++;
                }
            }
            System.out.println("***剩余车位："+(3-a));
            // 三个车位全部有车
            if (a == 3){
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
    // 开走
    public void leaveCarPort(){
        synchronized (obj){
            int a = 0;
            for (int i = 0; i < carPortStatus.length; i++) {
                if (carPortStatus[i] == true){
                    carPortStatus[i] = false;
                    System.out.println("@@@车位 "+i+" 腾开");
                    obj.notify();
                    break;
                }
                if (carPortStatus[i] == false){
                    a++;
                }
            }
            System.out.println("@@@剩余车位："+a);
            if (a == 3){
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class UseCar implements Runnable{

    private CarPort carPort;

    public UseCar(CarPort carPort){
        this.carPort = carPort;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            carPort.useCarPort();
        }
    }
}

class LeaveCar implements Runnable{

    private CarPort carPort;

    public LeaveCar(CarPort carPort){
        this.carPort = carPort;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            carPort.leaveCarPort();
        }
    }
}