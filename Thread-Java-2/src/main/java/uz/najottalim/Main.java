package uz.najottalim;

import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        CountDown countDown = new CountDown();

        CountDownThread countDownThread1 = new CountDownThread(countDown);
        countDownThread1.setName("Thread 1");
        CountDownThread countDownThread2 = new CountDownThread(countDown);
        countDownThread2.setName("Thread 2");

        countDownThread1.start();
        countDownThread2.start();
    }
}
class   CountDown {
    private int counter;
    public void  doCountDown() {
//        String color;
//        switch (Thread.currentThread().getName()) {
//            case "Thread 1" :
//                color = ThreadColor.ANSI_CYAN;
//                break;
//            case "Thread 2" :
//                color = ThreadColor.ANSI_RED;
//                break;
//            default:
//                color = ThreadColor.ANSI_GREEN;
//        }
        String color = switch(Thread.currentThread().getName()) {
            case "Thread 1" -> ThreadColor.ANSI_CYAN;
            case "Thread 2" -> ThreadColor.ANSI_RED;
            default -> ThreadColor.ANSI_GREEN;
        };
        synchronized (this) {
            for (counter = 10; counter > 0; counter--) {
                System.out.println(color + Thread.currentThread().getName() + ": i = " + counter);
            }
        }
    }
}
class CountDownThread extends Thread {
    private CountDown countDown;
    public CountDownThread(CountDown countDown) {
        this.countDown = countDown;
    }
    @Override
    public void run() {
        countDown.doCountDown();
    }
}
