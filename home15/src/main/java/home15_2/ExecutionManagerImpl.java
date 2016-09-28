package home15_2;

import java.util.List;
import java.util.concurrent.*;

/**
 * Realization ExecutionManager with method execute
 *
 * @version 1.0
 * @autor Trotsenko Konstantin
 */
public class ExecutionManagerImpl implements ExecutionManager {
    private ExecutorService executorService;
    private volatile Runnable[] tasksArray;
    private volatile Runnable callbackGlobal;
    private List interruptedList;
    private int countsTasksWithException = 0;

    @Override
    public Context execute(Runnable callback, Runnable... tasks) {
        tasksArray = tasks;
        callbackGlobal = callback;
        new Thread(new WorkerWithTasks()).start();
        Context context = new ContextImpl();
        return context;
    }

    private class WorkerWithTasks implements Runnable {
        @Override
        public void run() {
            //executorService = Executors.newFixedThreadPool(4);
            executorService = new ThreadPoolExecutor(1, 4, 60, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>()) {

                @Override
                protected void afterExecute(Runnable r, Throwable t) {
                    super.afterExecute(r, t);
                    if (t == null && r instanceof Future<?>) {
                        try {
                            Future<?> future = (Future<?>) r;
                            if (future.isDone()) {
                                future.get();
                            }
                        } catch (CancellationException ce) {
                            t = ce;
                        } catch (ExecutionException ee) {
                            t = ee.getCause();
                            countsTasksWithException++;
                        } catch (InterruptedException ie) {
                            Thread.currentThread().interrupt(); // ignore/reset
                        }
                    }
                    if (t != null) {
                        System.out.println(t);
                    }
                }
            };
            for (Runnable task : tasksArray) {
                executorService.execute(task);
            }
            executorService.shutdown();
            while (!executorService.isTerminated()) {
            }
            System.out.println("Finished all tasks!");
            callbackGlobal.run();
        }
    }

    /**
     * Realization Context
     *
     * @see Context
     */
    private class ContextImpl implements Context {
        /**
         * Method to get completed task count
         *
         * @return completed task count
         */
        @Override
        public int getCompletedTaskCount() {
            return (int) ((ThreadPoolExecutor) executorService).getCompletedTaskCount();
        }

        /**
         * Method to get failed task count
         *
         * @return failed task count
         */
        @Override
        public int getFailedTaskCount() {
            return countsTasksWithException;
        }

        /**
         * Method to get interrupted task count
         *
         * @return interrupted task count
         */
        @Override
        public int getInterruptedTaskCount() {
            return interruptedList.size();
        }

        /**
         * Method to interrupt
         */
        @Override
        public void interrupt() {
            interruptedList = executorService.shutdownNow();
        }

        /**
         * Method to check finished or not
         *
         * @return true if finished or interrupted, else false
         */
        @Override
        public boolean isFinished() {
            return (executorService.isTerminated() || executorService.isShutdown());
        }
    }

}
