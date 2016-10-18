package home20.realization.tolong;

import home20.base.ConverterValue;
import home20.base.ConverterValueAbstract;
import home20.realization.tolong.fromnumber.NumberToLong;
import home20.realization.tolong.fromstring.StringToLong;

import java.math.BigDecimal;

/**
 * Реализация конвертации объектов типа Long
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 * @see ConverterValue
 * @see ConverterValueAbstract
 */
public class ToLong extends ConverterValueAbstract{

    public ToLong() {
        super.addConverterValue(Integer.class, new NumberToLong());
        super.addConverterValue(Long.class, new NumberToLong());
        super.addConverterValue(Float.class, new NumberToLong());
        super.addConverterValue(Double.class, new NumberToLong());
        super.addConverterValue(BigDecimal.class, new NumberToLong());
        super.addConverterValue(String.class, new StringToLong());
    }

    @Override
    public Long convert(Object valueFrom) {
        ConverterValue<Long> value = super.getConverterValue(valueFrom.getClass());
        return value.convert(valueFrom);
    }
}
