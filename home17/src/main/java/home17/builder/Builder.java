package home17.builder;

import java.util.List;

/**
 * Interface for building report
 *
 * @version 1.0
 * @autor Trotsenko Konstantin
 */
public interface Builder {
    StringBuilder buildSalaryPaymentReport(List results);
}
