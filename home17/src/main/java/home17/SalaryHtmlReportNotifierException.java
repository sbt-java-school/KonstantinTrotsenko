package home17;

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
        System.out.println(message);
    }

    public SalaryHtmlReportNotifierException(String message, Throwable cause) {
        super(message, cause);
    }
}
