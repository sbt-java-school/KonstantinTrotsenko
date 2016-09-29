package home16;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Class Salon
 *
 * @version 1.0
 * @autor Trotsenko Konstantin
 */
public class Salon {

    /**
     * Create simple salon with one hairdresser
     * @param salonSize count chairs into salon
     * @param clients count clients in a day
     */
    public void salonStart(int salonSize, int clients) {
        if (salonSize <= 0 || clients <= 0) {
            throw new IllegalArgumentException();
        }
        Semaphore semaphoreSalon = new Semaphore(salonSize);
        for (int i = 0; i < clients; i++) {
            Client client = new Client("Client " + i, semaphoreSalon);
            Thread thread = new Thread(client);
            thread.start();
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
