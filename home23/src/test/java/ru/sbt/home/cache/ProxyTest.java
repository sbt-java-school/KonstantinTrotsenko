package ru.sbt.home.cache;

import org.junit.*;
import ru.sbt.home.cache.core.CalculatorImpl;
import ru.sbt.home.cache.core.Calculator;
import ru.sbt.home.cache.proxy.ProxyUtils;
import ru.sbt.home.refactoring.core.User;
import ru.sbt.home.refactoring.dao.DaoDemo;

/**
 * Класс для тестирования кеширования Proxy
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 */
public class ProxyTest {
    private DaoDemo daoDemo = new DaoDemo();
    private User user;

    @BeforeClass
    public static void  setUpClass() {
        System.out.println("Begin testing");
    }

    @Before
    public void setUp() {
        System.out.println("Before");
    }

    @Test
    public void proxyTest()  {
        Calculator calculator = new CalculatorImpl();
        Calculator proxyCalculator = (Calculator) ProxyUtils.makeCached(calculator);
        System.out.println(proxyCalculator.getFact(20));
        System.out.println(proxyCalculator.getFact(20));
        System.out.println(proxyCalculator.getFib(30));
    }

    @After
    public void tearDown() {
        System.out.println("After");
    }

    @AfterClass
    public  static void tearDownClass() {
        System.out.println("Testing completed");
    }
}
