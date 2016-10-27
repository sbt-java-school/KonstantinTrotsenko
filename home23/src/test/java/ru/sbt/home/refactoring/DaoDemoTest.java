package ru.sbt.home.refactoring;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.*;
import org.junit.runners.MethodSorters;
import ru.sbt.home.refactoring.core.User;
import ru.sbt.home.refactoring.dao.DaoDemo;

/**
 * Класс для тестирования DaoDemo
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DaoDemoTest {
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
    public void aDaoDemoCreateTest()  {
        daoDemo.createUser("userTest", "passwordTest");
        user = daoDemo.findByLogin("userTest");
        Assert.assertNotNull(user);
    }

    @Test
    public void bDaoDemoEqualPasswordTest()  {
        user = daoDemo.findByLogin("userTest");
        Assert.assertEquals(DigestUtils.md5Hex("passwordTest"), user.getPasswordMd5());
    }

    @Test
    public void cDaoDemoUpdateTest()  {
        user = daoDemo.findByLogin("userTest");
        daoDemo.updateUser("userTestUpdate", "userPasswordUpdate", user.getId());
        user = daoDemo.findByLogin("userTestUpdate");
        Assert.assertNotNull(user);
    }

    @Test
    public void dDaoDemoEqualPasswordAfterUpdateTest()  {
        user = daoDemo.findByLogin("userTestUpdate");
        Assert.assertEquals(DigestUtils.md5Hex("userPasswordUpdate"), user.getPasswordMd5());
    }

    @Test
    public void eDaoDemoDeleteTest()  {
        user = daoDemo.findByLogin("userTestUpdate");
        daoDemo.deleteUser(user.getId());
        user = daoDemo.findByLogin("userTestUpdate");
        Assert.assertNull(user);
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
