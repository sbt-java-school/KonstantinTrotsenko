package home13;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Class
 *
 * @version 1.0
 * @autor Trotsenko Konstantin
 */
public class SimpleThreadPool {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 12; i++) {
            Runnable workerThread = new WorkerThread(String.valueOf(i));
            executor.execute(workerThread);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("Finished all threads");
    }
}
