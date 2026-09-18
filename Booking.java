class Ticket {

    boolean booked = false;

    synchronized void bookTicket() {
        if(!booked) {
            System.out.println(Thread.currentThread().getName() + " is booking the ticket");

            booked = true;

            System.out.println(Thread.currentThread().getName() + " booked the ticket");
        } else {
            System.out.println("Ticket already booked.");
        }
    }
}

public class Booking {
    public static void main(String[] args) {

        Ticket ticket = new  Ticket();

        Thread t1 = new Thread(() -> {
            ticket.bookTicket();
        }, "Thread 1");

        Thread t2 = new Thread(() -> {
            ticket.bookTicket();
        }, "Thread 2");

        t1.start();
        t2.start();
    }

    
}