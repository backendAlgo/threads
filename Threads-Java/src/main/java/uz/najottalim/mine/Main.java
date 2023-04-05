package uz.najottalim.mine;

import static uz.najottalim.mine.ThreadColor.*;

public class Main {
    public static void main(String[] args) {
        //1
        System.out.println(ANSI_PURPLE+"Hello from main thread");
        //2
        Thread anotherThread = new AnotherThread();
        anotherThread.setName("==AnotherThread==");
        anotherThread.start();
        //3
        new Thread() {
            @Override
            public void run() {
                System.out.println(ANSI_CYAN + "hello from anonymous class");
            }
        }.start();
        System.out.println(ANSI_PURPLE + "Hello again");
        //4
        Thread myRunnableThread = new Thread(new MyRunnable() {
            @Override
            public void run() {
                System.out.println("from runnable thread");
                try {
                    //5
                    anotherThread.join(2000);
                    System.out.println("Another thread is terminated so I'am running again");
                } catch (InterruptedException ex) {
                    System.out.println("Interrupted");
                }
            }
        });
        myRunnableThread.start();
//        anotherThread.interrupt();

    }
}
