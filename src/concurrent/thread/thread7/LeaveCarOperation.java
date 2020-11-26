package concurrent.thread.thread7;

/**
 * @ClassName: LeaveCarOperation
 * @Description: description of class
 * @Author: Albert
 * @CreateDate: 2020/1/7 9:27
 */
public class LeaveCarOperation implements Runnable{
    private Carport carport;
    public LeaveCarOperation(Carport carport){
        this.carport = carport;
    }
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            carport.leave();
        }
    }
}
