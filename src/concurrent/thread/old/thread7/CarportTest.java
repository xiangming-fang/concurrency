package concurrent.thread.old.thread7;

/**
 * @ClassName: CarportTest
 * @Description: description of class
 * @Author: Albert
 * @CreateDate: 2020/1/7 9:17
 */
public class CarportTest {
    public static void main(String[] args) {
        Carport carport = new Carport();
        StopCarOperation sco1 = new StopCarOperation(carport);
        new Thread(sco1).start();
        LeaveCarOperation lco1 = new LeaveCarOperation(carport);
        new Thread(lco1).start();
    }
}
