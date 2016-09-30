package home16_2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Class to create Client
 *
 * @version 1.0
 * @autor Trotsenko Konstantin
 */
public class Client implements Runnable {
    private Long waitingTime;
    private BlockingQueue queueChairs;
    private String name;
    CountDownLatch latch = new CountDownLatch(1);

    public Client(BlockingQueue queueChairs, String name, Long waitingTime) {
        this.queueChairs = queueChairs;
        this.name = name;
        this.waitingTime = waitingTime;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        try {
            String name = this.getName();
            System.out.println(name + " go to salon");
            if (queueChairs.offer(this, waitingTime, TimeUnit.MILLISECONDS)){
                System.out.println(name + " go into salon and wait");
                latch.await();
                System.out.println(name + " go home lucky");
            } else {
                System.out.println(name + " go home angry");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
