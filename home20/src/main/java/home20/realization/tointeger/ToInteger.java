package home20.realization.tointeger;

import home20.base.ConverterValue;
import home20.base.ConverterValueAbstract;
import home20.realization.tointeger.fromnumber.NumberToInteger;
import home20.realization.tointeger.fromstring.StringToInteger;
import home20.realization.tolong.fromstring.StringToLong;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Реализация конвертации объектов типа Integer
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 * @see ConverterValue
 * @see ConverterValueAbstract
 */
public class ToInteger extends ConverterValueAbstract{

    public ToInteger() {
        super.addConverterValue(Integer.class, new NumberToInteger());
        super.addConverterValue(Long.class, new NumberToInteger());
        super.addConverterValue(Float.class, new NumberToInteger());
        super.addConverterValue(Double.class, new NumberToInteger());
        super.addConverterValue(BigDecimal.class, new NumberToInteger());
        super.addConverterValue(String.class, new StringToInteger());
    }

    @Override
    public Integer convert(Object valueFrom) {
        ConverterValue<Integer> value = super.getConverterValue(valueFrom.getClass());
        return value.convert(valueFrom);
    }
}
