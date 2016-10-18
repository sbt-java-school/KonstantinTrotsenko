package home20.base;

import java.util.HashMap;
import java.util.Map;

/**
 * Реализация интерфейса для конвертации объектов
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 * @see Converter
 */
public class ConverterImpl implements Converter {
    /**
     * Map для хранения класса - в который необходима конвертация и класса, реализующего конвертацию
     */
    private Map<Class, ConverterValue> converters = new HashMap<>();

    @Override
    public <T> T convert(Object value, Class<T> resultClass) {
        ConverterValue<T> converterValue = converters.get(resultClass);
        return converterValue.convert(value);
    }

    @Override
    public <T> void addConverterTo(Class<T> resultClass, ConverterValue converterTo) {
        converters.put(resultClass, converterTo);
    }

    @Override
    public <T> void removeConverterTo(Class<T> resultClass) {
        converters.remove(resultClass);
    }

}
