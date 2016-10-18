package home20.realization.tofloat;

import home20.base.ConverterValue;
import home20.base.ConverterValueAbstract;
import home20.realization.tofloat.fromnumber.NumberToFloat;
import home20.realization.tofloat.fromstring.StringToFloat;

import java.math.BigDecimal;

/**
 * Реализация конвертации объектов типа Float
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 * @see ConverterValue
 * @see ConverterValueAbstract
 */
public class ToFloat extends ConverterValueAbstract{

    public ToFloat() {
        super.addConverterValue(Integer.class, new NumberToFloat());
        super.addConverterValue(Long.class, new NumberToFloat());
        super.addConverterValue(Float.class, new NumberToFloat());
        super.addConverterValue(Double.class, new NumberToFloat());
        super.addConverterValue(BigDecimal.class, new NumberToFloat());
        super.addConverterValue(String.class, new StringToFloat());
    }

    @Override
    public Float convert(Object valueFrom) {
        ConverterValue<Float> value = super.getConverterValue(valueFrom.getClass());
        return value.convert(valueFrom);
    }
}
