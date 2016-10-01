package home17.builder;

import home17.model.SalaryPayment;

import java.util.List;

/**
 * Class to build html report
 *
 * @version 1.0
 * @autor Trotsenko Konstantin
 * @see Builder
 */
public class BuilderHtml implements Builder {
    @Override
    public StringBuilder buildSalaryPaymentReport(List results) {
        // create a StringBuilder holding a resulting html
        StringBuilder resultingHtml = new StringBuilder();
        resultingHtml.append("<html><body><table><tr><td>Employee</td><td>Salary</td></tr>");
        double totals = 0;
        for (Object payment : results) {
            // process each row of query results
            // add row start tag
            resultingHtml.append("<tr>");
            // appending employee name
            resultingHtml.append("<td>").append(((SalaryPayment) payment).getName()).append("</td>");
            // appending employee salary for period
            resultingHtml.append("<td>").append(((SalaryPayment) payment).getSalary()).append("</td>");
            // add row end tag
            resultingHtml.append("</tr>");
            // add salary to totals
            totals += ((SalaryPayment) payment).getSalary();
        }
        resultingHtml.append("<tr><td>Total</td><td>").append(totals).append("</td></tr>");
        resultingHtml.append("</table></body></html>");
        return resultingHtml;
    }
}
