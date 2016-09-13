package home4_1;

import java.util.*;

/**
 * Класс MyMultiMap реализующий интерфейс MultiMap
 *
 * @version 1.0
 * @autor Trotsenko Konstantin
 */
public class MyMultiMap<K, V> implements MultiMap<K, V> {
    /**
     * Внутренная map для реализации MyMultiMap
     */
    private Map<K, List<V>> map = new HashMap<>();

    /**
     * Метод возвращает List по заданному key
     *
     * @param key ключ для поиска
     * @return список объектов с искомым key
     * @throws IllegalArgumentException исключение если не найден по key
     */
    @Override
    public List<V> get(K key) {
        List<V> list = map.get(key);
        if (list == null) {
            throw new IllegalArgumentException("not found with this key");
        }
        return list;
    }

    /**
     * Метод добавляет данные в Map
     *
     * @param key   ключ
     * @param value значение
     * @throws IllegalArgumentException исключение key is null
     */
    @Override
    public void put(K key, V value) {
        if (null == key) {
            throw new IllegalStateException("key is null");
        }
        if (map.containsKey(key)) {
            map.get(key).add(value);
        } else {
            List<V> list = new ArrayList<>();
            list.add(value);
            map.put(key, list);
        }
    }
}
