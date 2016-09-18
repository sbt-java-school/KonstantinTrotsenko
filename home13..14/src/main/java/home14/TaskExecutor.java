package home14;

/**
 * Class execute the task (By calling run method of task).
 *
 * @version 1.0
 * @autor Trotsenko Konstantin
 */
public class TaskExecutor implements Runnable {
    BlockingQueue queue;

    public TaskExecutor(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String name = Thread.currentThread().getName();
                Runnable task = queue.dequeue();
                System.out.println("Task Started by " + name);
                task.run();
                System.out.println("Task Finished by " + name);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
