package home9;

import java.util.Iterator;

/**
 * Simple realization collection with Iterator
 *
 * @version 1.0
 * @autor Trotsenko Konstantin
 */

public class MyArray<Type> implements IMyArray {

    private Type[] arrayList;

    private int currentSize;

    public MyArray(Type[] newArray) {
        if (newArray==null||newArray.length==0){
            throw new NullPointerException();
        } else{
        this.arrayList = newArray;
        this.currentSize = arrayList.length;}
    }

    /**
     * Method to realize Iterator with its methods
     * @see IMyArray
     * @return iterator
     */
    @Override
    public Iterator<Type> iterator() {
        Iterator<Type> it = new Iterator<Type>() {

            private int currentIndex = 0;

            public boolean hasNext() {
                return currentIndex < currentSize && arrayList[currentIndex] != null;
            }

            public Type next() {
                return arrayList[currentIndex++];
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }
}