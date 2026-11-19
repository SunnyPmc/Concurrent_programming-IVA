import java.util.concurrent.locks.ReentrantLock;

class BankAccount {

    int balance = 5000;

    synchronized void withdraw(int amount) {
        System.out.println(Thread.currentThread().getName() + " is withdrawing");

        balance = balance - amount;

        System.out.println("Balance: " + balance);
    }
}

public class LockEx {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();

        ReentrantLock lock = new ReentrantLock();

        Thread t1  = new Thread(() -> {
            lock.lock();

        try {
            account.withdraw(1000);
        } finally {
            lock.unlock();
        }
        }, "Thread 1");

        Thread t2  = new Thread(() -> {
            lock.lock();

        try {
            account.withdraw(1000);
        } finally {
            lock.unlock();
        }
        }, "Thread 2");

        t1.start();
        t2.start();


    }
}