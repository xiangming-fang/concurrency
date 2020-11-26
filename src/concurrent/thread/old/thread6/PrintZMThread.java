package concurrent.thread.old.thread6;

/**
 * @ClassName: PrintZMThread
 * @Description: description of class
 * @Author: Albert
 * @CreateDate: 2020/1/6 17:05
 */
public class PrintZMThread implements Runnable {
    private Print print;
    public PrintZMThread(Print print){
        this.print = print;
    }
    @Override
    public void run() {
        for (int i = 0; i < 52; i++) {
            print.printZM();
        }
    }
}
