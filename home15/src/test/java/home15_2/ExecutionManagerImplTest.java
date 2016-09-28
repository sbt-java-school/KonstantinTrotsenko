package home15_2;

import org.junit.*;

import java.util.concurrent.TimeUnit;

/**
 * Class to test ExecutionManagerImpl
 *
 * @version 1.0
 * @autor Trotsenko Konstantin
 */
public class ExecutionManagerImplTest {
    @BeforeClass
    public static void setUpClass() {
        System.out.println("Begin testing ExecutionManagerImpl.class");
    }

    @Before
    public void setUp() {
        System.out.println("Before");
    }

    @Test
    public void testExecutionManagerImpl() {
        Runnable callback = () -> {
            System.out.println("It's callback!");
        };
        Runnable[] tasks = new Runnable[100];
        for (int i = 0; i < tasks.length; i++) {
            tasks[i] = () -> {
                try {
                    System.out.println(Thread.currentThread().getName());
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
        }
        ExecutionManagerImpl executionManager = new ExecutionManagerImpl();
        Context context = executionManager.execute(callback, tasks);
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(context.getCompletedTaskCount());
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
