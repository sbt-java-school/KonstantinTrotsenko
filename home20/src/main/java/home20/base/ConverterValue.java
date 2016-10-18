package home20.base;

/**
 * Базовый интерфейс для реализации определенной конвертации объектов
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 * @see ConverterImpl
 */
public interface ConverterValue<T> {

    /**
     * Метод дял конвертации
     *
     * @param value объект для конвертации
     * @return сконвертируемый объект
     */
    T convert(Object value);

    /**
     * Метод для добавления определенной вариаций конвртации
     *
     * @param resultClass    класс, в который необходимо сконвертировать
     * @param converterValue класс, в котором реализованна определенная конвертация
     */
    <T> void addConverterValue(Class<T> resultClass, ConverterValue<T> converterValue);

    /**
     * Метод для получения определенной вариаций конвртации
     *
     * @param resultClass класс, в который необходимо сконвертировать
     * @return класс, в котором реализованна определенная конвертация
     */
    T getConverterValue(Class<T> resultClass);

    /**
     * Метод для удаления определенной вариаций конвртации
     *
     * @param resultClass класс, в который необходимо сконвертировать
     */
    <T> void removeConverterValue(Class<T> resultClass);
}
