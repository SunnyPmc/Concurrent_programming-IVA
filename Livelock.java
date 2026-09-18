class Resource {
    boolean available = true;
}

public class Livelock {

    public static void main(String[] args) {
        
        Resource r1 = new Resource();
        Resource r2 = new Resource();

        Thread t1 = new Thread(() -> {
            while(true) {
                if(r1.available) {
                    System.out.println("Thread 1 got Resource 1");
                    r1.available = false;

                    if(r2.available) {
                        System.out.println("Thread 1 got Resource 2");
                        break;
                    }

                    //Give up resource
                    r1.available = true;
                    System.out.println("Thread 1 released Resource 1");
                }
            }
        });
    }
}