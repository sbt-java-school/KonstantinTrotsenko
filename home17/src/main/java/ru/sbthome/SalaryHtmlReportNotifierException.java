package ru.sbthome;

/**
 * Class to wrap RuntimeException
 *
 * @version 1.0
 * @autor Trotsenko Konstantin
 */
public class SalaryHtmlReportNotifierException extends RuntimeException {
    public SalaryHtmlReportNotifierException() {
        super();
    }

    public SalaryHtmlReportNotifierException(String message) {
        super(message);
    }

    public SalaryHtmlReportNotifierException(String message, Throwable cause) {
        super(message, cause);
    }
}
