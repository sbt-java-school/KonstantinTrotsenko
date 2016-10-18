package home20.realization.tobigdecimal.fromnumber;

import home20.base.ConverterValue;
import home20.base.ConverterValueAbstract;
import home20.realization.tobigdecimal.ToBigDecimal;

import java.math.BigDecimal;

/**
 * Конкретная реализация конвертации объектов типа Number в BigDecimal
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 * @see ConverterValue
 * @see ConverterValueAbstract
 * @see ToBigDecimal
 */
public class NumberToBigDecimal extends ConverterValueAbstract {

    @Override
    public BigDecimal convert(Object valueFrom) {
        Number value = (Number) valueFrom;
        return new BigDecimal(value.toString());
    }
}
