package uz.najottalim.mine;

import static uz.najottalim.mine.ThreadColor.ANSI_RED;

public class AnotherThread extends Thread{
    @Override
    public void run() {
        System.out.println(ANSI_RED + "Hello from " + currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            System.out.println(ANSI_RED + "Another thread woke me up");
            return;
        }
        System.out.println(ANSI_RED + "3 seconds passed and I am awake");
    }
}
