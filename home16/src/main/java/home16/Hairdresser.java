package home16;

import java.util.concurrent.Semaphore;

/**
 * Class Hairdresser to cau Client
 *
 * @version 1.0
 * @autor Trotsenko Konstantin
 */
public class Hairdresser implements Runnable {
    private Client client;

    /**
     * Semaphore for one hairdresser
     */
    private static Semaphore hairdresser = new Semaphore(1);

    public Hairdresser(Client client) {
        this.client = client;
    }

    /**
     * Cut client and lets him to go home
     */
    @Override
    public void run() {
        synchronized (client) {
            try {
                hairdresser.acquire();
                System.out.println("Hairdresser began to cut " + client.getName());
                Thread.sleep(400);
                System.out.println("Hairdresser finished cutting " + client.getName());
                hairdresser.release();
                client.latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
