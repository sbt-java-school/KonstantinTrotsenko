package home17;

import home17.builder.Builder;
import home17.builder.BuilderHtml;
import home17.dao.SalaryPaymentDao;
import home17.dao.SalaryPaymentJdbcDao;
import home17.sender.Sender;
import home17.sender.SenderMail;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {SalaryPaymentJdbcDao.class, SenderMail.class, SalaryHtmlReportNotifier.class})
public class TestSalaryHtmlReportNotifier {
    private Connection someFakeConnection;
    private ResultSet mockResultSet;

    @BeforeClass
    public static void setUpClass() {
        System.out.println("Begin testing SalaryHtmlReportNotifier.class");
    }

    @Before
    public void setUp() {
        System.out.println("Before");
    }

    @Test
    public void test() throws Exception {
        // mock database related stuff
        someFakeConnection = mock(Connection.class);
        mockResultSet = getMockedResultSet(someFakeConnection);
        when(mockResultSet.getLong("id")).thenReturn(123l, 567l);
        when(mockResultSet.getString("name")).thenReturn("John Doe", "Jane Dow");
        when(mockResultSet.getDouble("salary")).thenReturn(100.0, 100.0, 50.0, 50.0);
        // mock mail related stuff
        MimeMessageHelper mockMimeMessageHelper = getMockedMimeMessageHelper();
        // set up parameters
        SalaryPaymentDao salaryPaymentJdbcDao = new SalaryPaymentJdbcDao(someFakeConnection);
        Builder builderHtml = new BuilderHtml();
        Sender senderMail = new SenderMail();
        SalaryHtmlReportNotifier notificator = new SalaryHtmlReportNotifier(builderHtml, senderMail, salaryPaymentJdbcDao);
        LocalDate dateFrom = LocalDate.of(2014, Month.JANUARY, 1);
        LocalDate dateTo = LocalDate.of(2014, Month.DECEMBER, 31);
        // execute
        notificator.generateAndSendHtmlSalaryReport("10", dateFrom, dateTo, "somebody@gmail.com");
        // verify results
        String expectedReportPath = "src/test/java/resources/expectedReport.html";
        assertActualReportEqualsTo(mockMimeMessageHelper, expectedReportPath);
    }

    private void assertActualReportEqualsTo(MimeMessageHelper mockMimeMessageHelper, String expectedReportPath)
            throws MessagingException, IOException {
        ArgumentCaptor<String> messageTextArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(mockMimeMessageHelper).setText(messageTextArgumentCaptor.capture(), anyBoolean());
        Path path = Paths.get(expectedReportPath);
        String expectedReportContent = new String(Files.readAllBytes(path));
        assertEquals(messageTextArgumentCaptor.getValue(), expectedReportContent);
    }

    private ResultSet getMockedResultSet(Connection someFakeConnection) throws SQLException {
        PreparedStatement someFakePreparedStatement = mock(PreparedStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);
        when(someFakeConnection.prepareStatement(anyString())).thenReturn(someFakePreparedStatement);
        when(someFakePreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, true, false);
        return mockResultSet;
    }

    private MimeMessageHelper getMockedMimeMessageHelper() throws Exception {
        JavaMailSenderImpl mockMailSender = mock(JavaMailSenderImpl.class);
        MimeMessage mockMimeMessage = mock(MimeMessage.class);
        when(mockMailSender.createMimeMessage()).thenReturn(mockMimeMessage);
        whenNew(JavaMailSenderImpl.class).withNoArguments().thenReturn(mockMailSender);
        MimeMessageHelper mockMimeMessageHelper = mock(MimeMessageHelper.class);
        whenNew(MimeMessageHelper.class).withArguments(any(), anyString()).thenReturn(mockMimeMessageHelper);
        return mockMimeMessageHelper;
    }

    @After
    public void tearDown() {
        System.out.println("After");
    }

    @AfterClass
    public static void tearDownClass() {
        System.out.println("Testing completed");
    }

}
