package home20.realization.tointeger.fromstring;

import home20.base.ConverterValue;
import home20.base.ConverterValueAbstract;
import home20.realization.tointeger.ToInteger;

/**
 * Конкретная реализация конвертации объектов типа String в Integer
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 * @see ConverterValue
 * @see ConverterValueAbstract
 * @see ToInteger
 */
public class StringToInteger extends ConverterValueAbstract {

    @Override
    public Integer convert(Object valueFrom) {
        String value = (String) valueFrom;
        return Integer.parseInt(value);
    }
}
