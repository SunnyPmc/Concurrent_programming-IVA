import java.util.LinkedList;
import java.util.Queue;

class PrinterQueue {

    Queue<String> queue = new LinkedList<>();
    int capacity = 3;

    synchronized void addJob(String Job) throws InterruptedException {
        while(queue.size() == capacity) {
            System.out.println("Producer: Queue is full. Checking..");
            wait();
        }
        queue.add(Job);
        System.out.println("Job produced: "+ Job);
        notify();
    }

    synchronized void printJob() throws InterruptedException {
        while(queue.isEmpty()) {
            System.out.println("Consumer: Queue is empty.Checking again..");
            wait();
        }
        String job = queue.remove();
        System.out.println("Printed job: " + job);
        notify();
    }
}

class Producer extends Thread {
    PrinterQueue printer;

    Producer(PrinterQueue printer) {
        this.printer = printer;
    }

    public void run() {
        for(int i = 1; i <= 5; i++) {
            try {
                printer.addJob("Job " + i);
            } catch (InterruptedException e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
    }
}

class Consumer extends  Thread {
    PrinterQueue printer;

    Consumer(PrinterQueue printer) {
        this.printer = printer;
    }

    public void run() {
        for(int i = 1; i <= 5; i++) {
        try {
            printer.printJob();
        } catch (InterruptedException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
    }
}

public class PC2 {
    public static void main(String[] args) {

        PrinterQueue printer = new PrinterQueue();

        Producer producer = new Producer(printer);
        Consumer consumer = new Consumer(printer);

        producer.start();
        consumer.start();
    }
}