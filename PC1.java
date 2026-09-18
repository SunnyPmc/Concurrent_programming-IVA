class Buffer {
    int item;
    boolean available = false; //false for empty buffer, true for full buffer

    synchronized void produce(int value) {
        while(available) {
            // System.out.println("Producer: Buffer is full. Checking again...");
            try {
                wait();
            } catch (InterruptedException e) {
                // TODO: handle exception
                System.out.println(e);
            }
        }

        item = value;
        available = true;

        System.out.println("Producer produced: " + item);
        notify();
    }

    synchronized void consume() {
        while(!available) {
            // System.out.println("Consumer: Buffer is empty. Checking again...");
            try {
                wait();
            } catch (InterruptedException e) {
                // TODO: handle exception
                System.out.println(e);
            }
        }

        System.out.println("consumer consumed: " + item);
        available = false;
        notify();
    }
}

class Producer extends Thread {
    Buffer buffer;

    Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        for(int i = 1; i <= 5; i++) {
            buffer.produce(i);
        }
    }
}

class Consumer extends  Thread {
    Buffer buffer;

    Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        for(int i = 1; i <= 5; i++) {
            buffer.consume();
        }
    }
}

public class PC1 {
    public static void main(String[] args) {
        Buffer buffer = new Buffer();

        Producer producer = new Producer(buffer);
        Consumer consumer = new Consumer(buffer);

        producer.start();
        consumer.start();
    }
}