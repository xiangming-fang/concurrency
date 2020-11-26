package concurrent.thread.thread6;

/**
 * @ClassName: PrintTest
 * @Description: description of class
 * @Author: Albert
 * @CreateDate: 2020/1/6 17:06
 */
public class PrintTest {
    public static void main(String[] args) {
        Print print = new Print();
        PrintNumThread printNumThread = new PrintNumThread(print);
        PrintZMThread printZMThread = new PrintZMThread(print);
        Thread threadNum = new Thread(printNumThread,"我是打印数字的");
        Thread threadZM = new Thread(printZMThread,"我是打印字母的");
        threadNum.start();
        threadZM.start();
    }
}
