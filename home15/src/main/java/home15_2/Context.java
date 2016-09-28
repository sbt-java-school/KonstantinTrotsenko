package home15_2;

/**
 * Interface to create Context class
 *
 * @version 1.0
 * @autor Trotsenko Konstantin
 */
public interface Context {
    int getCompletedTaskCount();

    int getFailedTaskCount();

    int getInterruptedTaskCount();

    void interrupt();

    boolean isFinished();
}
