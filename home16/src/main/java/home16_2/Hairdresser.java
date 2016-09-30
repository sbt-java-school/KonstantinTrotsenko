package home16_2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Class Hairdresser to cut Client
 *
 * @version 1.0
 * @autor Trotsenko Konstantin
 */
public class Hairdresser implements Runnable {
    private BlockingQueue queueChairs;

    public Hairdresser(BlockingQueue queue) {
        this.queueChairs = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String name = Thread.currentThread().getName();
                while (queueChairs.isEmpty()){
                    System.out.println(name + " sleep 200 ms");
                    TimeUnit.MILLISECONDS.sleep(200);
                }
                Client client = (Client) queueChairs.take();
                System.out.println(name + " begin cut - " + client.getName());
                TimeUnit.MILLISECONDS.sleep(200);
                System.out.println(name + " finish cut - " + client.getName());
                client.latch.countDown();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
