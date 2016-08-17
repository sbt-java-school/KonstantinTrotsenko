package home4_1;

import java.util.List;

/**
 * Created by Airo on 17.08.2016.
 */
interface MultiMap<K, V> {

    List<V> get(K key);

    Void put(K key, V value);
}
