package home17.dao;

import home17.SalaryHtmlReportNotifierException;
import home17.model.SalaryPayment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;

/**
 * Class to getting data from database by JDBC
 *
 * @version 1.0
 * @autor Trotsenko Konstantin
 * @see SalaryPaymentDao
 */
public class SalaryPaymentJdbcDao implements SalaryPaymentDao {
    private Connection connection;
    private List listOfSalaryPayment;

    public SalaryPaymentJdbcDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List getSalaryPayments(String departmentId, LocalDate dateFrom, LocalDate dateTo) {
        try {
            // prepare statement with sql
            PreparedStatement preparedStatement = connection.prepareStatement("select emp.id as id, emp.name as name," +
                    " sum(salary) as salary from employee emp left join" +
                    "salary_payments sp on emp.id = sp.employee_id where emp.department_id = ? and" +
                    " sp.date >= ? and sp.date <= ? group by emp.id, emp.name");
            // inject parameters to sql
            preparedStatement.setString(0, departmentId);
            preparedStatement.setDate(1, new java.sql.Date(dateFrom.toEpochDay()));
            preparedStatement.setDate(2, new java.sql.Date(dateTo.toEpochDay()));
            // execute query and get the results
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listOfSalaryPayment.add(new SalaryPayment(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getDouble("salary")));
            }
        } catch (Exception e) {
            throw new SalaryHtmlReportNotifierException("Exception into getSalaryPayments");
        }
        return listOfSalaryPayment;
    }
}
