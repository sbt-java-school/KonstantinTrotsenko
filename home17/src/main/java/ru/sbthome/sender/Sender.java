package ru.sbthome.sender;

/**
 * Interface for sending report
 *
 * @version 1.0
 * @autor Trotsenko Konstantin
 */
@FunctionalInterface
public interface Sender {
    void sendReport(String recipients, StringBuilder resultingHtml);
}
