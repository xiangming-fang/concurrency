package concurrent.thread.thread5;

/**
 * @auther 方翔鸣
 * @date 2020/3/3 18:58
 * practis：写2个线程，其中一个线程打印 1~52，另一个线程打印A~Z，打印顺序应该是12A34B56C...5152Z。该线程需要利用到线程通讯的知识。
 */
public class Nine {
    public static void main(String[] args) {
        Print print = new Print();
        PrintLetter printLetter = new PrintLetter(print);
        PrintNumber printNumber = new PrintNumber(print);
        Thread thread = new Thread(printLetter);
        Thread thread1 = new Thread(printNumber);
        thread.start();
        thread1.start();
    }
}

class Print{
    private static int count = 1;
    private static int number = 1;
    private static int askii = 65;
    Object obj = new Object();
    public void printLetter(){
        synchronized (obj){
            if (count % 3 == 0 && askii <= 90){
                System.out.print((char)askii);
                askii++;
                count++;
                obj.notifyAll();
            }else {
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void printNum(){
        synchronized (obj){
            if (count % 3 != 0 && number <= 52){
                System.out.print(number);
                number++;
                count++;
                obj.notifyAll();
            }else {
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class PrintLetter implements Runnable{

    private Print print;

    public PrintLetter(Print print){
        this.print = print;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            print.printLetter();
        }
    }
}

class PrintNumber implements Runnable{

    private Print print;

    public PrintNumber(Print print){
        this.print = print;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            print.printNum();
        }
    }
}