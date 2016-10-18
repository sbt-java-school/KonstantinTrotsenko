package home20.realization.tostring;

import home20.base.ConverterValue;
import home20.base.ConverterValueAbstract;
import home20.realization.tostring.fromnumber.NumberToString;
import home20.realization.tostring.fromstring.StringToString;

import java.math.BigDecimal;

/**
 * Реализация конвертации объектов типа String
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 * @see ConverterValue
 * @see ConverterValueAbstract
 */
public class ToString extends ConverterValueAbstract {

    public ToString() {
        super.addConverterValue(Integer.class, new NumberToString());
        super.addConverterValue(Long.class, new NumberToString());
        super.addConverterValue(Float.class, new NumberToString());
        super.addConverterValue(Double.class, new NumberToString());
        super.addConverterValue(BigDecimal.class, new NumberToString());
        super.addConverterValue(String.class, new StringToString());
    }

    @Override
    public String convert(Object valueFrom) {
        ConverterValue<String> value = super.getConverterValue(valueFrom.getClass());
        return value.convert(valueFrom);
    }
}
