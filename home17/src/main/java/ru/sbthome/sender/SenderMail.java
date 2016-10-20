package ru.sbthome.sender;

import ru.sbthome.SalaryHtmlReportNotifierException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;

/**
 * Class for sending report by mail
 *
 * @version 1.0
 * @autor Trotsenko Konstantin
 * @see Sender
 */
public class SenderMail implements Sender {
    @Override
    public void sendReport(String recipients, StringBuilder resultingHtml) {
        // now when the report is built we need to send it to the recipients list
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        // we're going to use google mail to send this message
        mailSender.setHost("mail.google.com");
        // construct the message
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(recipients);
            // setting message text, last parameter 'true' says that it is HTML format
            helper.setText(resultingHtml.toString(), true);
            helper.setSubject("Monthly department salary report");
            // send the message
            mailSender.send(message);
        } catch (Exception e) {
            throw new SalaryHtmlReportNotifierException("Exception into sendReport", e);
        }
    }
}
