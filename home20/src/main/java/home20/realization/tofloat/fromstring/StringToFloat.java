package home20.realization.tofloat.fromstring;

import home20.base.ConverterValue;
import home20.base.ConverterValueAbstract;
import home20.realization.tofloat.ToFloat;

/**
 * Конкретная реализация конвертации объектов типа String в Float
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 * @see ConverterValue
 * @see ConverterValueAbstract
 * @see ToFloat
 */
public class StringToFloat extends ConverterValueAbstract {

    @Override
    public Float convert(Object valueFrom) {
        String value = (String) valueFrom;
        return Float.parseFloat(value);
    }
}
