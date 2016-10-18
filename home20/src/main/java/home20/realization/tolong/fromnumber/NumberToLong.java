package home20.realization.tolong.fromnumber;

import home20.base.ConverterValue;
import home20.base.ConverterValueAbstract;
import home20.realization.tolong.ToLong;

/**
 * Конкретная реализация конвертации объектов типа Number в Long
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 * @see ConverterValue
 * @see ConverterValueAbstract
 * @see ToLong
 */
public class NumberToLong extends ConverterValueAbstract {

    @Override
    public Long convert(Object valueFrom) {
        Number value = (Number) valueFrom;
        return value.longValue();
    }
}
