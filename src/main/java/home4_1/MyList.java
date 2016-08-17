package home4_1;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

/**
 * Created by Airo on 17.08.2016.
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
