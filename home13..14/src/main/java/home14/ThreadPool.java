package home14;

/**
 * Class creates number of TaskExecutor instances
 *
 * @version 1.0
 * @autor Trotsenko Konstantin
 */
public class ThreadPool {
    BlockingQueue queue;

    public ThreadPool(int queueSize, int nThread) {
        if (queueSize <= 0 || nThread <= 0) {
            throw new IllegalArgumentException();
        }
        queue = new BlockingQueue(queueSize);
        String threadName = null;
        TaskExecutor task = null;
        for (int count = 0; count < nThread; count++) {
            threadName = "Thread-" + count;
            task = new TaskExecutor(queue);
            Thread thread = new Thread(task, threadName);
            thread.start();
        }
    }

    /**
     * Method to submit task to thread Pool
     *
     * @param task
     * @throws InterruptedException
     */
    public void submitTask(Runnable task) throws InterruptedException {
        queue.enqueue(task);
    }

}
