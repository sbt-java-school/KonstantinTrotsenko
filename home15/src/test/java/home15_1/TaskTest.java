package home15_1;

import org.junit.*;

import java.util.concurrent.Callable;

/**
 * Class to test Task
 *
 * @version 1.0
 * @autor Trotsenko Konstantin
 */
public class TaskTest {
    @BeforeClass
    public static void setUpClass() {
        System.out.println("Begin testing Task.class");
    }

    @Before
    public void setUp() {
        System.out.println("Before");
    }

    @Test
    public void testTask() {
        Task<Object> task = new Task<>(() -> "From call");
        System.out.println(task.get());
    }
    @Test
    public void testTaskCash() {
        Callable callable = () -> "From call";
        Task<Object> task0 = new Task<>(callable);
        Task<Object> task1 = new Task<>(callable);
        System.out.println(task0.get());
        System.out.println(task1.get());
        Assert.assertEquals(task0.get(),task1.get());
    }

    @Test(expected = MyException.class)
    public void testTaskException() {
        Task<Object> task = new Task<>(() -> 1 / 0);
        System.out.println(task.get());
    }

    @Test(expected = MyException.class)
    public void testTaskExceptionCash() {
        Callable callable = () -> 1 / 0;
        Task<Object> task0 = new Task<>(callable);
        Task<Object> task1 = new Task<>(callable);
        try {
            System.out.println(task0.get());
        } catch (MyException e) {
        }
        System.out.println(task1.get());
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
