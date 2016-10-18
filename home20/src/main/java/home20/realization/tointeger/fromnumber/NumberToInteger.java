package home20.realization.tointeger.fromnumber;

import home20.base.ConverterValue;
import home20.base.ConverterValueAbstract;
import home20.realization.tointeger.ToInteger;

/**
 * Конкретная реализация конвертации объектов типа Number в Integer
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 * @see ConverterValue
 * @see ConverterValueAbstract
 * @see ToInteger
 */
public class NumberToInteger extends ConverterValueAbstract {

    @Override
    public Integer convert(Object valueFrom) {
        Number value = (Number) valueFrom;
        return value.intValue();
    }
}
