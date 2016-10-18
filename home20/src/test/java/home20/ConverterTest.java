package home20;

import home20.base.Converter;
import home20.base.ConverterImpl;
import home20.base.ConverterValue;
import home20.realization.tobigdecimal.ToBigDecimal;
import home20.realization.todouble.ToDouble;
import home20.realization.tofloat.ToFloat;
import home20.realization.tointeger.ToInteger;
import home20.realization.tolong.ToLong;
import home20.realization.tostring.ToString;
import org.junit.*;

import java.math.BigDecimal;

/**
 * Класс для тестирования Конвертера
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 */
public class ConverterTest {
    private static Converter CONVERTER = new ConverterImpl();
    private Class objectToConvert;
    private Double someDouble = 10.01D;
    private Long someLong = 10000000L;
    private Float someFloat = 10.02F;
    private String someString = "10";
    private String someBadString = "10//";
    private BigDecimal someBigDecimal = new BigDecimal(100000000000000.01);

    @BeforeClass
    public static void  setUpClass() {
        System.out.println("Begin testing");
        CONVERTER.addConverterTo(Integer.class, new ToInteger());
        CONVERTER.addConverterTo(Long.class, new ToLong());
        CONVERTER.addConverterTo(Float.class, new ToFloat());
        CONVERTER.addConverterTo(Double.class, new ToDouble());
        CONVERTER.addConverterTo(BigDecimal.class, new ToBigDecimal());
        CONVERTER.addConverterTo(String.class, new ToString());
    }

    @Before
    public void setUp() {
        System.out.println("Before");
    }

    @Test
    public void testDoubleToInteger() {
        objectToConvert = Integer.class;
        Object object = CONVERTER.convert(someDouble, objectToConvert).getClass();
        Assert.assertSame(object, objectToConvert);
    }

    @Test
    public void testBigDecimalToInteger()  {
        objectToConvert = Integer.class;
        Object object = CONVERTER.convert(someBigDecimal, objectToConvert).getClass();
        Assert.assertSame(object, objectToConvert);
    }

    @Test
    public void testLongToDouble()  {
        objectToConvert = Double.class;
        Object object = CONVERTER.convert(someLong, objectToConvert).getClass();
        Assert.assertSame(object, objectToConvert);
    }

    @Test
    public void testFloatToBidDecimal()  {
        objectToConvert = BigDecimal.class;
        Object object = CONVERTER.convert(someFloat, objectToConvert).getClass();
        Assert.assertSame(object, objectToConvert);
    }

    @Test
    public void testStringToInteger()  {
        objectToConvert = Integer.class;
        Object object = CONVERTER.convert(someString, objectToConvert).getClass();
        Assert.assertSame(object, objectToConvert);
    }

    @Test(expected = NumberFormatException.class)
    public void testBadStringToInteger()  {
        objectToConvert = Integer.class;
        Object object = CONVERTER.convert(someBadString, objectToConvert).getClass();
        Assert.assertSame(object, objectToConvert);
    }

    @Test
    public void testDoubleToString()  {
        objectToConvert = String.class;
        Object object = CONVERTER.convert(someDouble, objectToConvert).getClass();
        Assert.assertSame(object, objectToConvert);
    }

    @Test
    public void testBigDecimalToString()  {
        objectToConvert = String.class;
        Object object = CONVERTER.convert(someDouble, objectToConvert).getClass();
        Assert.assertSame(object, objectToConvert);
    }

    @Test(expected = NullPointerException.class)
    public void testDoubleException() throws InterruptedException {
        CONVERTER.removeConverterTo(objectToConvert);
        Object object = CONVERTER.convert(someDouble, objectToConvert).getClass();
        Assert.assertSame(object, objectToConvert);
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


