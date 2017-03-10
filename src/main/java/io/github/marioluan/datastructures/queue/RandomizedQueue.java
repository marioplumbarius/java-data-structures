package io.github.marioluan.datastructures.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;
import io.github.marioluan.datastructures.wrappers.Resizable;

/**
 * Randomized queue implementation using an array.
 * 
 * @author marioluan
 * @param <T>
 *            the class type of elements to be handled.
 */
public class RandomizedQueue<T> implements Iterable<T> {

    private T[]                  a;
    private int                  n;
    private final Resizable<T[]> rs;
    private static final int     FOUR_TIMES = 4;
    private static final int     TWICE      = 2;

    /**
     * Construct an empty randomized queue.
     *
     * @param rs
     *            injection of a ResizableWrapper instance (useful for unit
     *            testing)
     */
    @SuppressWarnings("unchecked")
    public RandomizedQueue(Resizable<T[]> rs) {
        this.a = (T[]) new Object[2];
        this.n = 0;
        this.rs = rs;
    }

    /**
     * Is the queue empty?
     * 
     * @return returns whether it is empty or not
     */
    public boolean isEmpty() {
        return this.n == 0;
    }

    /**
     * Return the number of items on the queue.
     * 
     * @return the size
     */
    public int size() {
        return this.n;
    }

    /**
     * Add the {@link T item} to the queue.<br>
     * <strong>Time complexity:</strong> O(1)<br>
     * 
     * @param item
     */
    public void enqueue(T item) {
        if (item == null)
            throw new NullPointerException();

        // doubles size of array if necessary
        if (n == a.length)
            a = rs.resize(a, n, TWICE * a.length);

        this.a[this.n++] = item;
    }

    /**
     * Remove and return a random item.
     * <strong>Time complexity:</strong> O(1)<br>
     * 
     * @return returns an random item
     */
    public T dequeue() {
        if (this.isEmpty())
            throw new NoSuchElementException();

        int k = StdRandom.uniform(this.n);

        T item = a[k];
        // brings element from last position
        a[k] = a[n - 1];
        // avoids loitering
        a[n - 1] = null;

        this.n--;

        // shrink size of array if necessary
        if (n > 0 && n == a.length / FOUR_TIMES)
            a = rs.resize(a, n, a.length / TWICE);

        return item;
    }

    /**
     * Return (but do not remove) a random item.
     * <strong>Time complexity:</strong> O(1)<br>
     * 
     * @return returns the first item
     */
    public T sample() {
        if (this.isEmpty())
            throw new NoSuchElementException();

        int k = StdRandom.uniform(this.n);

        return a[k];
    }

    /**
     * Returns an independent iterator over items in random order.
     * 
     * @return the generated iterator
     */
    @Override
    public Iterator<T> iterator() {
        return new RandomQueueIterator();
    }

    /**
     * Implements an Iterator for a {@code RandomizedQueue} data structure.
     * 
     * @author marioluan
     */
    private class RandomQueueIterator implements Iterator<T> {
        private T[] nodes;
        private int m;

        /**
         * Construct the iterator by copying and shuffling the array.
         * <strong>Time complexity:</strong> O(n^2).<br>
         */
        public RandomQueueIterator() {
            nodes = copy(a);
            StdRandom.shuffle(nodes);
            m = 0;
        }

        /**
         * Copies and returns the copied array of y.
         * <strong>Time complexity:</strong> O(n).<br>
         * 
         * @param y
         * @return
         */
        @SuppressWarnings("unchecked")
        private T[] copy(T[] y) {
            T[] b = (T[]) new Object[n];

            for (int i = 0; i < n; i++)
                b[i] = y[i];

            return b;

        }

        /**
         * <strong>Time complexity:</strong> O(1).<br>
         */
        public boolean hasNext() {
            return this.m < nodes.length;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        /**
         * <strong>Time complexity:</strong> O(1).<br>
         */
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException();

            return nodes[m++];
        }
    }
}
