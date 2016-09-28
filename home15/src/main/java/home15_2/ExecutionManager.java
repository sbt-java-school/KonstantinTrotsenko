package home15_2;

/**
 * Interface to create ExecutionManager class
 *
 * @version 1.0
 * @autor Trotsenko Konstantin
 */
public interface ExecutionManager {
    Context execute(Runnable callback, Runnable... tasks);
}
