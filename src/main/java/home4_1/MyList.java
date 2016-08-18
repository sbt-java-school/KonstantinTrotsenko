package home4_1;

import java.util.Collection;
import java.util.Iterator;

/**
 * Интерфейс MyList с заданными методами
 *
 * @version 1.0
 * @autor Trotsenko Konstantin
 */
public interface MyList<E> {

    boolean add(E e);

    void add(int index, E element);

    E get(int index);

    E remove(int index);

    Iterator<E> iterator();

    boolean addAll(Collection<? extends E> c);

    boolean copy(Collection<? extends E> c);
}
