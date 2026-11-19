abstract class Task {

    abstract void execute();
}

class MyTask extends Task {

    @override
    void execute() {
        for(int i = 1; i <= 5; i++) {
            System.out.println(Thread.currentThread().getName() + " is executing Task " + i);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // TODO: handle exception
                e.printStackTrace();
            }

        }
    }
}

public class Abstraction {
    public static void main(String[] args) {
        MyTask task = new MyTask();

        Thread t1 = new Thread(() -> task.execute(), "Thread 1");
        Thread t2 = new Thread(() -> task.execute(), "Thread 2");

        t1.start();
        t2.start();
    }
}