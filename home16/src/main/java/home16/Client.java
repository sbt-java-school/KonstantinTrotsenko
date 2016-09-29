package home16;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Class to create Client
 *
 * @version 1.0
 * @autor Trotsenko Konstantin
 */
public class Client implements Runnable {
    private String name;

    /**
     * Semaphore to get to Salon
     */
    private Semaphore semaphoreSalon;
    /**
     * Latch to go home
     */
    CountDownLatch latch = new CountDownLatch(1);

    public Client(String name, Semaphore semaphoreSalon) {
        this.name = name;
        this.semaphoreSalon = semaphoreSalon;
    }

    public String getName() {
        return name;
    }

    /**
     * Client try to get to salon 400 milliseconds and if it's successfully go home
     * lucky else angry
     */
    @Override
    public void run() {
        try {
            System.out.println(name + " go to salon");
            if (semaphoreSalon.tryAcquire(400, TimeUnit.MILLISECONDS)) {
                System.out.println(name + " go into salon and wait");
                Hairdresser hairdresser = new Hairdresser(this);
                Thread thread = new Thread(hairdresser);
                thread.start();
                latch.await();
                semaphoreSalon.release();
                System.out.println(name + " go home lucky");
            } else {
                System.out.println(name + " go home angry");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
