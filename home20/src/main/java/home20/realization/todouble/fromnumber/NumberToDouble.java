package home20.realization.todouble.fromnumber;

import home20.base.ConverterValue;
import home20.base.ConverterValueAbstract;
import home20.realization.todouble.ToDouble;

/**
 * Конкретная реализация конвертации объектов типа Number в Double
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 * @see ConverterValue
 * @see ConverterValueAbstract
 * @see ToDouble
 */
public class NumberToDouble extends ConverterValueAbstract {

    @Override
    public Double convert(Object valueFrom) {
        Number value = (Number) valueFrom;
        return value.doubleValue();
    }
}
