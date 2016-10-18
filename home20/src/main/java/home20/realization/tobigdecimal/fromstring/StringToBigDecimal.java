package home20.realization.tobigdecimal.fromstring;

import home20.base.ConverterValue;
import home20.base.ConverterValueAbstract;
import home20.realization.tobigdecimal.ToBigDecimal;
import home20.realization.todouble.ToDouble;

import java.math.BigDecimal;

/**
 * Конкретная реализация конвертации объектов типа String в BigDecimal
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 * @see ConverterValue
 * @see ConverterValueAbstract
 * @see ToBigDecimal
 */
public class StringToBigDecimal extends ConverterValueAbstract {

    @Override
    public BigDecimal convert(Object valueFrom) {
        String value = (String) valueFrom;
        return new BigDecimal(value.replace(",","."));
    }
}
