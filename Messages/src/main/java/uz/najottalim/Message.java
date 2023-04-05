package uz.najottalim;

import java.util.Random;
class Main {
    public static void main(String[] args) {
        Message message = new Message();
        (new Thread(new Writer(message))).start();
        (new Thread(new Reader(message))).start();
    }
}
public class Message {
    private String message;
    private boolean empty = true;

    public synchronized String read() {
        while (empty) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        empty = true;
        notifyAll();
        return message;
    }
    public synchronized void write(String message) {
        while (!empty) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        empty = false;
        this.message = message;
        notifyAll();
    }
}
class Writer implements Runnable {
    private Message message;

    public Writer(Message message) {
        this.message = message;
    }
    @Override
    public void run() {
        String[] messages = new String[] {
                "first message",
                "second message",
                "third message",
                "fourth message",
                "fifth message"
        };

        Random random = new Random();
        for (String s : messages) {
            message.write(s);
            try {
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException ex) {

            }
        }
        message.write("Finished");
    }
}

class Reader implements Runnable {
    private Message message;

    public Reader(Message message) {
        this.message = message;
    }

    @Override
    public void run() {
        Random random = new Random();
        String s;
        while (!(s = message.read()).equalsIgnoreCase("Finished")) {
            System.out.println(s);
            try {
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException ex) {

            }
        }
    }
}