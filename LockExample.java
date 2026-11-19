
import java.util.concurrent.locks.ReentrantLock;

public class LockExample {

    static int balance = 5000;

    public static void main(String[] args) {

        ReentrantLock lock = new ReentrantLock();

        Thread t1 = new Thread(() -> {

            lock.lock();

            try {
                System.out.println("Depositing NPR 1000");

                balance = balance + 1000;

                System.out.println("Balance: " + balance);

            } finally {
                lock.unlock();
            }
        });

        Thread t2 = new Thread(() -> {

            lock.lock();

            try {
                System.out.println("Withdrawing NPR 500");

                balance = balance - 500;

                System.out.println("Balance: " + balance);

            } finally {
                lock.unlock();
            }
        });

        t1.start();
        t2.start();
    }
}
