package home11;

import org.junit.*;

import java.net.MalformedURLException;

/**
 * Class for test PluginManagerWithStandartURLCLTest
 *
 * @version 1.0
 * @autor Trotsenko Konstantin
 */
public class PluginManagerWithStandartURLCLTest {
    @BeforeClass
    public static void setUpClass() {
        System.out.println("Begin testing PluginManager.class");
    }

    @Before
    public void setUp() {
        System.out.println("Before");
    }

    @Test(expected = ClassNotFoundException.class)
    public void testWrongURL() throws Exception {
        System.out.println("Testing wrong URL");
        PluginManagerWithStandartURLCL pluginManager = new PluginManagerWithStandartURLCL("C:\\tempppppp\\");
        pluginManager.load("PluginA", "home11.PluginImpl");
    }

    @Test(expected = NullPointerException.class)
    public void testNullPointer() throws Exception {
        System.out.println("Testing NullPointer");
        PluginManagerWithStandartURLCL pluginManager = new PluginManagerWithStandartURLCL(null);
        pluginManager.load(null, null);
    }

    @Test
    public void testCashClass() throws ClassNotFoundException, MalformedURLException,
            InstantiationException, IllegalAccessException {
        PluginManagerWithStandartURLCL pluginManager = new PluginManagerWithStandartURLCL("C:\\temp\\");
        Plugin pluginA = pluginManager.load("PluginA", "home11.PluginImpl");
        Plugin pluginB = pluginManager.load("PluginA", "home11.PluginImpl");
        Assert.assertEquals(pluginB.getClass(), pluginA.getClass());
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
