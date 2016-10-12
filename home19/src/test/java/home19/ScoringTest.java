package home19;

import home19.base.Scoring;
import org.junit.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to test Scoring.class
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 */
public class ScoringTest {
    private Scoring scoring = new Scoring();
    private Map<String, Double> goodClient = new HashMap<>();
    private Map<String, Double> badClient = new HashMap<>();
    private double[] result;

    @BeforeClass
    public static void setUpClass() {
        System.out.println("Begin testing Scoring.class");
    }

    @Before
    public void setUp() {
        System.out.println("Before");

        goodClient.put("City", 7000d);
        goodClient.put("Salary", 30000d);
        goodClient.put("Age", 0.20d);
        goodClient.put("MaritalStatus", 0.20d);
        goodClient.put("Car", 0.10d);
        goodClient.put("Children", 0.10d);
        goodClient.put("Dependents", 0.20d);
        goodClient.put("Ownership", 0.20d);
        goodClient.put("PeriodMonth", 25d);
        goodClient.put("AmountOfCredit", 240000d);

        badClient.put("City", 4000d);
        badClient.put("Salary", 15000d);
        badClient.put("Age", 0.1d);
        badClient.put("MaritalStatus", 0.1d);
        badClient.put("Car", 0.1d);
        badClient.put("Children", 0.0d);
        badClient.put("Dependents", 0.0d);
        badClient.put("Ownership", 0.0d);
        badClient.put("PeriodMonth", 12d);
        badClient.put("AmountOfCredit", 240000d);
    }

    @Test
    public void testGoogClient() throws InterruptedException {
        result = scoring.startScoring(goodClient);
        Assert.assertEquals(result[0], 23000.0d, 0);
        Assert.assertEquals(result[1], 9600.0d, 0);
        Assert.assertTrue(result[2]>0.2);
        Assert.assertEquals(result[3], 1.0d, 0);
        Assert.assertTrue(result[4]>0.2);
    }

    @Test
    public void testBadClient() throws InterruptedException {
        result = scoring.startScoring(badClient);
        Assert.assertEquals(result[0], 11000.0d, 0);
        Assert.assertEquals(result[1], 20000.0d, 0);
        Assert.assertTrue(result[2]<0.2);
        Assert.assertTrue(result[3]<0.301);
        Assert.assertTrue(result[4]<0.2);
    }

    @Test(expected = NullPointerException.class)
    public void testException() throws InterruptedException {
        badClient.remove("Salary");
        result = scoring.startScoring(badClient);
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
