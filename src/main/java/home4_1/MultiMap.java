package home4_1;

import java.util.List;

/**
 * Интерфейс MultiMap
 *
 * @version 1.0
 * @autor Trotsenko Konstantin
 */
interface MultiMap<K, V> {

    List<V> get(K key);

    void put(K key, V value);
}
