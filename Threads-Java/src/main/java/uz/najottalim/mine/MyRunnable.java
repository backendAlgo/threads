package uz.najottalim.mine;

import static uz.najottalim.mine.ThreadColor.ANSI_GREEN;

public class MyRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println(ANSI_GREEN + "Runnable class");
    }
}
