package home16_2;

import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Class Salon
 *
 * @version 1.0
 * @autor Trotsenko Konstantin
 */
public class Salon {
    private BlockingQueue queueChairs;

    public Salon(int chairsForWait, int hairdressers) {
        if (chairsForWait <= 0 || hairdressers <= 0) {
            throw new BusinessException("Illegal argument Exception");
        }
        queueChairs = new ArrayBlockingQueue(chairsForWait);
        String hairdresserName = null;
        Hairdresser hairdresser = null;
        for (int count = 0; count < hairdressers; count++) {
            hairdresserName = "Hairdresser - " + count;
            hairdresser = new Hairdresser(queueChairs);
            Thread thread = new Thread(hairdresser, hairdresserName);
            thread.start();
        }
    }

    /**
     * Method to create Clients
     *
     * @param clientNumber
     */
    public void startGoingClient(int clientNumber) {
        if (clientNumber >= 0) {
            throw new BusinessException("Illegal argument Exception");
        }
        String clientName = null;
        Client client = null;
        for (int count = 0; count < clientNumber; count++) {
            clientName = "Clinet - " + count;
            client = new Client(queueChairs, clientName, 400l);
            Thread thread = new Thread(client);
            thread.start();
        }
    }
}
