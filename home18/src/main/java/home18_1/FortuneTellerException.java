package home18_1;

/**
 * Class wrap RuntimeException
 *
 * @version 1.0
 * @author Trotsenko Konstantin
 */
public class FortuneTellerException extends RuntimeException {
    public FortuneTellerException() {
        super();
    }

    public FortuneTellerException(String message) {
        super(message);
    }
}
