package home15_1;

/**
 * Class MyException to wrap RuntimeException
 *
 * @version 1.0
 * @autor Trotsenko Konstantin
 */
public class MyException extends RuntimeException {
    public MyException() {
    }

    public MyException(String msg) {
        super(msg);
        System.out.println(msg);
    }
}
