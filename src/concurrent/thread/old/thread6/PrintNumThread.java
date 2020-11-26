package concurrent.thread.old.thread6;

/**
 * @ClassName: PrintNumThread
 * @Description: description of class
 * @Author: Albert
 * @CreateDate: 2020/1/6 17:03
 */
public class PrintNumThread implements Runnable {

    private Print print;

    public PrintNumThread(Print print){
        this.print = print;
    }
    @Override
    public void run() {
        for (int i = 0; i < 52; i++) {
            print.printNum();
        }
    }
}
