package home13;

import java.util.concurrent.TimeUnit;

/**
 * Class to create thread and do work
 *
 * @version 1.0
 * @autor Trotsenko Konstantin
 */
public class WorkerThread implements Runnable {
    private String command;

    public WorkerThread(String command) {
        this.command = command;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" Start. Command: "+command);
        doCommand();
        System.out.println(Thread.currentThread().getName()+" Stop.");
    }

    /**
     * Method simulate smt work
     */
    private void doCommand() {
        try {
            TimeUnit.SECONDS.sleep(2L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
