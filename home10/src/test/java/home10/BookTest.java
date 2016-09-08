package home10;

import org.junit.*;

import java.io.*;

/**
 * Test class for Book.class to check Serialization Proxy Pattern
 *
 * @version 1.0
 * @autor Trotsenko Konstantin
 */
public class BookTest {

    private String fileName = "book.ser";
    private Book bookAfterSer = null;
    private Book bookBeforeSer = new Book("Faust", "Goethe", 250);

    @BeforeClass
    public static void setUpClass() {
        System.out.println("Begin testing Book.class");
    }

    @Before
    public void setUp() {
        System.out.println("Before");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(bookBeforeSer);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()))) {
            bookAfterSer = (Book) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testProxySerializationObject() {
        Assert.assertNotEquals(bookAfterSer, bookBeforeSer);
    }

    @Test
    public void testProxySerializationStructure() {
        Assert.assertEquals(bookAfterSer.getTitle(), bookBeforeSer.getTitle());
        Assert.assertEquals(bookAfterSer.getAuthor(), bookBeforeSer.getAuthor());
        Assert.assertEquals(bookAfterSer.getCountSheets(), bookBeforeSer.getCountSheets());
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
