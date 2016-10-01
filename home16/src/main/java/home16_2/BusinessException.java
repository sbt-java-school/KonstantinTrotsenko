package home16_2;

/**
 * Class to wrap RuntimeException
 *
 * @version 1.0
 * @autor Trotsenko Konstantin
 */
public class BusinessException extends RuntimeException {
    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
        System.out.printf(message);
        System.out.println();
    }
}
