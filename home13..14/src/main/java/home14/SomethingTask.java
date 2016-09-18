package home14;

/**
 * Class simulates the task
 *
 * @version 1.0
 * @autor Trotsenko Konstantin
 */
public class SomethingTask implements Runnable {
    private int number;
    public SomethingTask(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        System.out.println("Start executing of task number :"+ number);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("End executing of task number :"+ number);
    }
}
