package home16;

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
        Salon salon = new Salon();
        salon.salonStart(2,10);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSalonException() throws InterruptedException {
        Salon salon = new Salon();
        salon.salonStart(-1,10);
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
