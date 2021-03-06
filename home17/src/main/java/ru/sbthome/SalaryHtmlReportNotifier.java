package ru.sbthome;

import ru.sbthome.builder.Builder;
import ru.sbthome.dao.SalaryPaymentDao;
import ru.sbthome.sender.Sender;

import java.time.LocalDate;
import java.util.List;

/**
 * Class to get data from database, create report and send it
 *
 * @version 1.0
 * @autor Trotsenko Konstantin
 * @see Builder
 * @see Sender
 * @see SalaryPaymentDao
 */
public class SalaryHtmlReportNotifier {

    private Builder builder;
    private Sender sender;
    private SalaryPaymentDao salaryPayment;

    public SalaryHtmlReportNotifier(Builder builder, Sender sender, SalaryPaymentDao salaryPayment) {
        this.builder = builder;
        this.sender = sender;
        this.salaryPayment = salaryPayment;
    }

    public void generateAndSendHtmlSalaryReport(String departmentId, LocalDate dateFrom,
                                                LocalDate dateTo, String recipients) {
        if (departmentId != null && dateFrom != null && dateTo != null && recipients != null) {
            List listOfSalaryPayments = salaryPayment.getSalaryPayments(departmentId, dateFrom, dateTo);
            StringBuilder resultingHtml = builder.buildSalaryPaymentReport(listOfSalaryPayments);
            sender.sendReport(recipients, resultingHtml);
        } else throw new SalaryHtmlReportNotifierException("Wrong arguments");
    }
}
