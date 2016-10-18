package home20.realization.tostring.fromnumber;

import home20.base.ConverterValue;
import home20.base.ConverterValueAbstract;
import home20.realization.tostring.ToString;

/**
 * Конкретная реализация конвертации объектов типа Number в String
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 * @see ConverterValue
 * @see ConverterValueAbstract
 * @see ToString
 */
public class NumberToString extends ConverterValueAbstract {

    @Override
    public String convert(Object valueFrom) {
        Number value = (Number) valueFrom;
        return value.toString();
    }
}
