package home20.realization.tobigdecimal;

import home20.base.ConverterValue;
import home20.base.ConverterValueAbstract;
import home20.realization.tobigdecimal.fromnumber.NumberToBigDecimal;
import home20.realization.tobigdecimal.fromstring.StringToBigDecimal;

import java.math.BigDecimal;

/**
 * Реализация конвертации объектов типа BigDecimal
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 * @see ConverterValue
 * @see ConverterValueAbstract
 */
public class ToBigDecimal extends ConverterValueAbstract{

    public ToBigDecimal() {
        super.addConverterValue(Integer.class, new NumberToBigDecimal());
        super.addConverterValue(Long.class, new NumberToBigDecimal());
        super.addConverterValue(Float.class, new NumberToBigDecimal());
        super.addConverterValue(Double.class, new NumberToBigDecimal());
        super.addConverterValue(BigDecimal.class, new NumberToBigDecimal());
        super.addConverterValue(String.class, new StringToBigDecimal());
    }

    @Override
    public BigDecimal convert(Object valueFrom) {
        ConverterValue<BigDecimal> value = super.getConverterValue(valueFrom.getClass());
        return value.convert(valueFrom);
    }
}
