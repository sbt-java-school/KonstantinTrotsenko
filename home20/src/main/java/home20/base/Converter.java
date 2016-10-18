package home20.base;

/**
 * Базовый интерфейс для конвертации объектов
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 */
public interface Converter {

    /**
     * Метод для конвертации объектов
     *
     * @param value       объект который необходимо конвертировать
     * @param resultClass класс, в который необходимо сконвертировать value
     * @return возвращает сконвертируемй объект
     */
    <T> T convert(Object value, Class<T> resultClass);

    /**
     * Метод для добавления вариаций конвертации
     *
     * @param resultClass класс, в который необходимо сконвертировать
     * @param converterTo класс, в котором реализована конвертация
     */
    <T> void addConverterTo(Class<T> resultClass, ConverterValue converterTo);

    /**
     * Метод для удаления вариаций конвертации
     *
     * @param resultClass класс, в который необходимо сконвертировать
     */
    <T> void removeConverterTo(Class<T> resultClass);
}
