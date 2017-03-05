package io.github.marioluan.datastructures.stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

import io.github.marioluan.datastructures.Util;
import io.github.marioluan.datastructures.wrappers.Resizable;

/**
 * {@link Stack} implementation using a generic dynamic array data
 * structure.<br>
 * All operations take constant time in the worst case, except the
 * {@link #iterator} method.
 * 
 * @author marioluan
 * @param <T>
 *            the generic type of items from the stack
 */
public class DynamicArray<T> implements Stack<T> {

    private static final int     DEFAULT_CAPACITY = 2;
    private T[]                  array;
    private int                  n;
    private final Resizable<T[]> rs;
    private static final int     FOUR_TIMES       = 4;
    private static final int     TWICE            = 2;

    /**
     * Constructs an empty stack.
     * 
     * @param rs
     *            the {@link Resizable} dependency which will take care of
     *            array resizing
     */
    @SuppressWarnings("unchecked")
    public DynamicArray(Resizable<T[]> rs) {
        this.rs = rs;
        this.array = (T[]) new Object[DEFAULT_CAPACITY];
        this.n = 0;
    }

    private DynamicArray(Resizable<T[]> rs, T[] array, int n) {
        this.rs = rs;
        this.array = array;
        this.n = n;
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public void push(T item) throws IllegalArgumentException {
        if (item == null)
            throw new IllegalArgumentException("item must not be null");

        // doubles size of array if necessary
        if (n == array.length)
            array = rs.resize(array, n, TWICE * array.length);

        array[n++] = item;
    }

    @Override
    public T pop() throws NoSuchElementException {
        if (isEmpty())
            throw new NoSuchElementException();

        T item = array[n - 1];

        // avoids loitering
        array[--n] = null;

        // shrink size of array if necessary
        if (n > 0 && n == array.length / FOUR_TIMES)
            array = rs.resize(array, n, array.length / TWICE);

        return item;
    }

    @Override
    public T peek() throws NoSuchElementException {
        if (isEmpty())
            throw new NoSuchElementException();

        return array[n - 1];
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator(array);
    }

    private final class ArrayIterator implements Iterator<T> {

        private DynamicArray<T> copy;

        @SuppressWarnings("unchecked")
        private ArrayIterator(T[] array) {
            T[] arrayCopy = (T[]) new Object[n];
            Util.copy(array, arrayCopy, 0, n - 1);

            this.copy = new DynamicArray<>(rs, arrayCopy, n);
        }

        @Override
        public boolean hasNext() {
            return !copy.isEmpty();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException();

            return copy.pop();
        }
    }
}
