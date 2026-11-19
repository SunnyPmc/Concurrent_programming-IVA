class BankAccount {

    int balance = 5000;

    synchronized void withdraw(int amount) {
        System.out.println(Thread.currentThread().getName() + " is withdrawing");

        balance = balance - amount;

        System.out.println("Balance: " + balance);
    }
}

public class SynEx {
    public static void main(String[] args) {

        BankAccount account = new BankAccount();

        Thread t1 = new Thread(() -> {
            account.withdraw(1000);
        }, "Thread 1");

        Thread t2 = new Thread(() -> {
            account.withdraw(2000);
        }, "Thread 2");

        t1.start();
        t2.start();
    }
}