package concurrent.thread.thread7;

/**
 * @ClassName: StopCarOperation
 * @Description: description of class
 * @Author: Albert
 * @CreateDate: 2020/1/7 9:25
 */
public class StopCarOperation implements Runnable{
    private Carport carport;
    public StopCarOperation(Carport carport){
        this.carport = carport;
    }
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            carport.carPark();
        }
    }
}
