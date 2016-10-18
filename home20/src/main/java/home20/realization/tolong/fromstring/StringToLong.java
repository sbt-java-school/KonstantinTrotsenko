package home20.realization.tolong.fromstring;

import home20.base.ConverterValue;
import home20.base.ConverterValueAbstract;
import home20.realization.tolong.ToLong;

/**
 * Конкретная реализация конвертации объектов типа String в Long
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 * @see ConverterValue
 * @see ConverterValueAbstract
 * @see ToLong
 */
public class StringToLong extends ConverterValueAbstract {

    @Override
    public Long convert(Object valueFrom) {
        String value = (String) valueFrom;
        return Long.parseLong(value);
    }
}
