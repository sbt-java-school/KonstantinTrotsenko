package home14;

import org.junit.*;

/**
 * Class to test ThreadPool
 *
 * @version 1.0
 * @autor Trotsenko Konstantin
 */
public class ThreadPoolTest {
    @BeforeClass
    public static void setUpClass() {
        System.out.println("Begin testing ThreadPool.class");
    }

    @Before
    public void setUp() {
        System.out.println("Before");
    }

    @Test
    public void testThreadPool() throws InterruptedException {
        ThreadPool threadPool = new ThreadPool(2,4);
        for(int taskNumber = 1 ; taskNumber <= 15; taskNumber++) {
            SomethingTask task = new SomethingTask(taskNumber);
            threadPool.submitTask(task);
        }
    }

    @Test (expected = IllegalArgumentException.class)
    public void testEmptyThreadPool() throws InterruptedException {
        ThreadPool threadPool = new ThreadPool(0,4);
        for(int taskNumber = 1 ; taskNumber <= 15; taskNumber++) {
            SomethingTask task = new SomethingTask(taskNumber);
            threadPool.submitTask(task);
        }
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
