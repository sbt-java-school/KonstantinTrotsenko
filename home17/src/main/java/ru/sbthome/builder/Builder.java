package ru.sbthome.builder;

import java.util.List;

/**
 * Interface for building report
 *
 * @version 1.0
 * @autor Trotsenko Konstantin
 */
@FunctionalInterface
public interface Builder {
    StringBuilder buildSalaryPaymentReport(List results);
}
