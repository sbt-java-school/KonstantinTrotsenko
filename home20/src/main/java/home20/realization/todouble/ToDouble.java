package home20.realization.todouble;

import home20.base.ConverterValue;
import home20.base.ConverterValueAbstract;
import home20.realization.todouble.fromnumber.NumberToDouble;
import home20.realization.todouble.fromstring.StringToDouble;

import java.math.BigDecimal;

/**
 * Реализация конвертации объектов типа Double
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 * @see ConverterValue
 * @see ConverterValueAbstract
 */
public class ToDouble extends ConverterValueAbstract{
    public ToDouble() {
        super.addConverterValue(Integer.class, new NumberToDouble());
        super.addConverterValue(Long.class, new NumberToDouble());
        super.addConverterValue(Float.class, new NumberToDouble());
        super.addConverterValue(Double.class, new NumberToDouble());
        super.addConverterValue(BigDecimal.class, new NumberToDouble());
        super.addConverterValue(String.class, new StringToDouble());
    }

    @Override
    public Double convert(Object valueFrom) {
        ConverterValue<Double> value = super.getConverterValue(valueFrom.getClass());
        return value.convert(valueFrom);
    }
}
