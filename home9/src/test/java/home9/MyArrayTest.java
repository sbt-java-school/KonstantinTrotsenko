package home9;

import org.junit.*;
import java.util.Iterator;

/**
 * Test class for MyArray.class to check Iterator work
 *
 * @version 1.0
 * @autor Trotsenko Konstantin
 */
public class MyArrayTest {

    private MyArray<Integer> myArray;

    @BeforeClass
    public static void setUpClass() {
        System.out.println("Begin testing MyArray.class");
    }

    @Before
    public void setUp() {
        System.out.println("Before");
    }

    @Test(expected = NullPointerException.class)
    public void testEmptyArray() throws Exception {
        System.out.println("Test null collection");
        myArray = new MyArray<>(null);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testRemoveArray() throws Exception {
        myArray = new MyArray<>(new Integer[]{1, 2});
        Iterator iterator = myArray.iterator();
        System.out.println("Test remove() method");
        iterator.remove();

    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testNextArrayOneElement() throws Exception {
        myArray = new MyArray<>(new Integer[]{1});
        Iterator iterator = myArray.iterator();
        System.out.println("Test next() method");
        iterator.next();
        iterator.next();
    }

    @Test
    public void testHasNextTrue() {
        myArray = new MyArray<>(new Integer[]{1, 2});
        Iterator iterator = myArray.iterator();
        System.out.println("Test hasNext() method");
        Assert.assertTrue(iterator.hasNext());
    }

    @Test
    public void testHasNextFalse() {
        myArray = new MyArray<>(new Integer[]{1});
        Iterator iterator = myArray.iterator();
        iterator.next();
        System.out.println("Test hasNext() method");
        Assert.assertFalse(iterator.hasNext());
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