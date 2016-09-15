package home11;

import org.junit.*;

import java.net.MalformedURLException;

/**
 * Class for test PluginManager
 *
 * @version 1.0
 * @autor Trotsenko Konstantin
 */
public class PluginManagerTest {
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
        PluginManager pluginManager = new PluginManager("C:\\tempppppp\\");
        pluginManager.load("PluginA", "home11.PluginImpl");
    }

    @Test(expected = NullPointerException.class)
    public void testNullPointer() throws Exception {
        System.out.println("Testing NullPointer");
        PluginManager pluginManager = new PluginManager(null);
        pluginManager.load(null, null);
    }

    @Test
    public void testCashClass() throws ClassNotFoundException, MalformedURLException,
            InstantiationException, IllegalAccessException {
        PluginManager pluginManager = new PluginManager("C:\\temp\\");
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
