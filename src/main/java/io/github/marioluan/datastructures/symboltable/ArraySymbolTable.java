package io.github.marioluan.datastructures.symboltable;

import edu.princeton.cs.algs4.Stack;
import io.github.marioluan.datastructures.Util;

/**
 * {@link SymbolTable} implementation using an ordered array data structure.
 * 
 * @author marioluan
 * @param <Key>
 *            the class type of the comparable key
 * @param <Value>
 *            the class type of the value
 */
public class ArraySymbolTable<Key extends Comparable<Key>, Value>
        implements SymbolTable<Key, Value> {

    private Key[]   keys;
    private Value[] values;
    private int     n;

    /**
     * Constructs an empty ordered array.
     * 
     * @param capacity
     *            the size of the array
     */
    @SuppressWarnings("unchecked")
    public ArraySymbolTable(int capacity) {
        this.keys = (Key[]) new Comparable[capacity + 1];
        this.values = (Value[]) new Object[capacity + 1];
        this.n = 0;
    }

    // Time complexity: O(n)
    @Override
    public void put(Key key, Value value) {
        if (key == null)
            throw new NullPointerException("key must not be null");

        int i = rank(key);

        // equal keys
        if (i < n && keys[i].compareTo(key) == 0) {
            if (value != null) {
                values[i] = value;
            } else {
                // removes the key-value pair from the table
                shift(i);
                keys[n - 1] = null;
                values[n - 1] = null;
                n--;
                // shrink size of array if necessary
                if (n > 0 && n == keys.length / 4)
                    resize(keys.length / 2);
            }

            return;
        }

        // double size of array if necessary
        if (n == keys.length)
            resize(2 * keys.length);

        // inserts a new key-value pair
        keys[n] = key;
        values[n] = value;
        shift(i, n);
        n++;
    }

    // Time complexity: O(log n)
    @Override
    public Value get(Key key) {
        if (key == null)
            throw new NullPointerException("key must not be null");

        if (isEmpty())
            return null;

        int i = rank(key);
        if (i < n && keys[i].compareTo(key) == 0)
            return values[i];

        return null;
    }

    // Time complexity: O(n)
    @Override
    public void delete(Key key) {
        put(key, null);
    }

    // Time complexity: O(log n)
    @Override
    public boolean contains(Key key) {
        return get(key) != null;
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public int size() {
        return n;
    }

    // Time complexity: O(n)
    @Override
    public Iterable<Key> keys() {
        Stack<Key> stack = new Stack<>();

        for (int i = n - 1; i > -1; i--)
            stack.push(keys[i]);

        return stack;
    }

    /**
     * Shifts all keys greater than the key at the last position of the table.
     * 
     * @param i
     *            starting index
     */
    private void shift(int lo, int hi) {
        while (keys[lo].compareTo(keys[hi]) > 0) {
            Util.swap(keys, lo, hi);
            Util.swap(values, lo, hi);
            lo++;
        }
    }

    /**
     * Shifts all keys greater than the key at the position i.
     * 
     * @param i
     *            starting index
     */
    private void shift(int i) {
        while (i + 1 < n) {
            Util.swap(keys, i, i + 1);
            Util.swap(values, i, i + 1);
            i++;
        }
    }

    /**
     * Returns the index of he key from the table by making a binary search.
     * 
     * @param key
     * @return the index found or a index to add the key
     */
    private int rank(Key key) {
        int lo = 0;
        int hi = n - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (keys[mid].compareTo(key) < 0)
                lo = mid + 1;
            else if (keys[mid].compareTo(key) > 0)
                hi = mid - 1;
            else
                return mid;
        }

        return lo;
    }

    /**
     * Resizes {@link #keys} and {@link #values}.
     * 
     * @param capacity
     *            the new capacity of the arrays
     */
    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        assert capacity >= n;

        Key[] tempKeys = (Key[]) new Comparable[capacity + 1];
        Value[] tempValues = (Value[]) new Object[capacity + 1];

        for (int i = 0; i < n; i++) {
            tempKeys[i] = keys[i];
            tempValues[i] = values[i];
        }

        keys = tempKeys;
        values = tempValues;
    }
}
