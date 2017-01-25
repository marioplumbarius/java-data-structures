package io.github.marioluan.datastructures.wrappers;

/**
 * Implements {@link Resizable} for array data types.
 * 
 * @author marioluan
 * @param <T>
 *            the type class of the items from the array.
 */
public class ArrayResizable<T> implements Resizable<T[]> {

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public T[] resize(T[] a, int n, int capacity) {
        assert capacity >= n;

        T[] temp = (T[]) new Object[capacity];
        for (int i = 0; i < n; i++)
            temp[i] = a[i];

        return temp;
    }
}
