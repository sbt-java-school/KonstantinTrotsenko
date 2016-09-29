package home17.sender;

/**
 * Interface for sending report
 *
 * @version 1.0
 * @autor Trotsenko Konstantin
 */
public interface Sender {
    void sendReport(String recipients, StringBuilder resultingHtml);
}
