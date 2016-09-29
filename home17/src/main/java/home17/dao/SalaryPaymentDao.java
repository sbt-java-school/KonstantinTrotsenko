package home17.dao;


import java.time.LocalDate;
import java.util.List;

/**
 * Interface for getting data from database
 *
 * @version 1.0
 * @autor Trotsenko Konstantin
 */
public interface SalaryPaymentDao {
    List getSalaryPayments(String departmentId, LocalDate dateFrom, LocalDate dateTo);
}
