package home20.base;

import java.util.HashMap;
import java.util.Map;

/**
 * Реализация интерфейса для определенной конвертации объектов
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 * @see ConverterValue
 */
public abstract class ConverterValueAbstract implements ConverterValue {
    /**
     * Map для хранения класса - в который необходима конвертация и класса, реализующего конвертацию
     */
    private Map<Class, ConverterValue> converters = new HashMap<>();

    @Override
    public void addConverterValue(Class resultClass, ConverterValue converterValue) {
        converters.put(resultClass, converterValue);
    }

    @Override
    public ConverterValue getConverterValue(Class resultClass) {
        return converters.get(resultClass);
    }

    @Override
    public void removeConverterValue(Class resultClass) {
        converters.remove(resultClass);
    }
}
