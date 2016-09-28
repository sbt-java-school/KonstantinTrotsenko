package home15_1;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Class Task to realize one method get
 *
 * @version 1.0
 * @autor Trotsenko Konstantin
 */
public class Task<T> {

    private final Callable<? extends T> callable;
    private static ConcurrentHashMap<Callable<?>, Object> resultMap = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<Callable<?>, MyException> exceptionMap = new ConcurrentHashMap<>();
    ;

    public Task(Callable<? extends T> callable) {
        this.callable = callable;
    }

    /**
     * Method return result of callable.call or throw exception
     *
     * @return callable.call result
     */
    public T get() {
        if (exceptionMap.get(callable) != null) {
            System.out.println("From cash!");
            throw exceptionMap.get(callable);
        }
        if (resultMap.get(callable) == null) {
            synchronized (this) {
                if (resultMap.get(callable) == null) {
                    try {
                        resultMap.put(callable, callable.call());
                    } catch (Exception e) {
                        exceptionMap.put(callable, new MyException("Exception"));
                        throw exceptionMap.get(callable);
                    }
                }
            }
        }
        return (T) resultMap.get(callable);
    }
}

