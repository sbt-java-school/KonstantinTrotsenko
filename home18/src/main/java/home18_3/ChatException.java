package home18_3;

/**
 * Class wrap RuntimeException
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 */
public class ChatException extends RuntimeException {
    public ChatException() {
        super();
    }

    public ChatException(String message) {
        super(message);
    }
}
