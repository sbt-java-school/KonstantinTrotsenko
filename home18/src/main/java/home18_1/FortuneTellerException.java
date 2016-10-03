package home18_1;

/**
 * Class wrap RuntimeException
 *
 * @vesion 1.0
 * @autor Trotsenko Konstantin
 */
public class FortuneTellerException extends RuntimeException {
    public FortuneTellerException() {
        super();
    }

    public FortuneTellerException(String message) {
        super(message);
    }
}
