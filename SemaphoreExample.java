
import java.util.concurrent.Semaphore;

public class SemaphoreExample {

    public static void main(String[] args) {

        Semaphore printers = new Semaphore(2);

        Thread t1 = new Thread(() -> {
            try {
                printers.acquire();

                System.out.println("User 1 is printing");
                Thread.sleep(2000);
                System.out.println("User 1 finished printing");

                printers.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                printers.acquire();

                System.out.println("User 2 is printing");
                Thread.sleep(2000);
                System.out.println("User 2 finished printing");

                printers.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t3 = new Thread(() -> {
            try {
                printers.acquire();

                System.out.println("User 3 is printing");
                Thread.sleep(2000);
                System.out.println("User 3 finished printing");

                printers.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
        t3.start();
    }
}
