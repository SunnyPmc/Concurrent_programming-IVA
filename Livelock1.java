class Person {
    boolean moving = true;
}

public  class Livelock1 {
    public static void main(String[] args) {

        Person p1 = new Person();
        Person p2 = new Person();

        Thread t1 = new Thread(() -> {
            while (p1.moving) {
                System.out.println("Person 1: You go.");
            }
        });
        Thread t2 = new Thread(() -> {
            while (p2.moving) {
                System.out.println("Person 2:No, You go.");
            }
        });

        t1.start();
        t2.start();
    }
}