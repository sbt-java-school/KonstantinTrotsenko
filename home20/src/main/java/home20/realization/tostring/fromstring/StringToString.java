package home20.realization.tostring.fromstring;

import home20.base.ConverterValue;
import home20.base.ConverterValueAbstract;
import home20.realization.tostring.ToString;

/**
 * Конкретная реализация конвертации объектов типа String в String (по необходимомсти)
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 * @see ConverterValue
 * @see ConverterValueAbstract
 * @see  ToString
 */
public class StringToString extends ConverterValueAbstract {

    @Override
    public String convert(Object valueFrom) {
        String value = (String) valueFrom;
        return value;
    }
}
