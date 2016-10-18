package home20.realization.tofloat.fromnumber;

import home20.base.ConverterValue;
import home20.base.ConverterValueAbstract;
import home20.realization.tofloat.ToFloat;

/**
 * Конкретная реализация конвертации объектов типа Number в Float
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 * @see ConverterValue
 * @see ConverterValueAbstract
 * @see ToFloat
 */
public class NumberToFloat extends ConverterValueAbstract {

    @Override
    public Float convert(Object valueFrom) {
        Number value = (Number) valueFrom;
        return value.floatValue();
    }
}
