package home20.realization.todouble.fromstring;

import home20.base.ConverterValue;
import home20.base.ConverterValueAbstract;
import home20.realization.todouble.ToDouble;

/**
 * Конкретная реализация конвертации объектов типа String в Double
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 * @see ConverterValue
 * @see ConverterValueAbstract
 * @see ToDouble
 */
public class StringToDouble extends ConverterValueAbstract {

    @Override
    public Double convert(Object valueFrom) {
        String value = (String) valueFrom;
        return Double.parseDouble(value);
    }
}
