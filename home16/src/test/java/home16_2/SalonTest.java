package home16_2;

import home16_2.*;
import org.junit.*;

/**
 * Class to test Salon.class
 *
 * @version 1.0
 * @autor Trotsenko Konstantin
 */
public class SalonTest {
    @BeforeClass
    public static void setUpClass() {
        System.out.println("Begin testing Salon.class");
    }

    @Before
    public void setUp() {
        System.out.println("Before");
    }

    @Test
    public void testSalon() throws InterruptedException {
        Salon salon = new Salon(4, 2);
        salon.startGoingClient(10);
    }

    @Test (expected = BusinessException.class)
    public void testSalonException() throws InterruptedException {
        Salon salon = new Salon(-5, 2);
        salon.startGoingClient(10);
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
