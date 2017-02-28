package io.github.marioluan.datastructures.symboltable.hash;

import edu.princeton.cs.algs4.Stack;
import io.github.marioluan.datastructures.symboltable.SymbolTable;

/**
 * {@link SymbolTable} implementation using a linear probing hash data
 * structure with a fixed capacity.<br>
 * The following operations take O(lg N) on worst-case and O(3-5) (constant
 * time) on average under uniform hashing assumption:
 * <ul>
 * <li>get</li>
 * <li>put</li>
 * <li>delete</li>
 * </ul>
 * 
 * @author marioluan
 * @param <Key>
 *            the class type of the comparable key
 * @param <Value>
 *            the class type of the value
 */
public class LinearProbingHashTable<Key extends Comparable<Key>, Value>
        implements SymbolTable<Key, Value> {

    private Key[]            keys;
    private Value[]          values;
    private int              n;
    private int              m;
    private static final int UNSIGNED_CONST = 0x7fffffff;
    private static final int EIGHT_TIMES    = 8;
    private static final int TWICE          = 2;

    /**
     * Constructs a new hash table with the given capacity.
     * 
     * @param capacity
     */
    @SuppressWarnings("unchecked")
    public LinearProbingHashTable(int capacity) {
        this.keys = (Key[]) new Comparable[capacity];
        this.values = (Value[]) new Comparable[capacity];
        this.n = 0;
        this.m = capacity;
    }

    /**
     * Computes and returns a positive hash value based on the {@link Key key}.
     * 
     * @param key
     * @return a positive hash value based on the {@link Key key}
     */
    private int hash(Key key) {
        // forces the sign bit (the first one, from the 32) to 0 to avoid
        // negative indices
        return (key.hashCode() & UNSIGNED_CONST) % m;
    }

    @Override
    public void put(Key key, Value value) {
        if (key == null)
            throw new NullPointerException("key must not be null");

        // handles deletion
        if (value == null) {
            delete(key);
            return;
        }

        // doubles size of array if necessary (50% full)
        if (n >= m / TWICE)
            resize(TWICE * m);

        // handles insertion
        int i;
        for (i = hash(key); keys[i] != null; i = i + 1 % m) {
            // handles updates
            if (keys[i].equals(key)) {
                values[i] = value;
                return;
            }
        }

        // adds the key-value pair to the empty spot found
        keys[i] = key;
        values[i] = value;
        n++;
    }

    @Override
    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = i + 1 % m)
            if (keys[i].equals(key))
                return values[i];

        return null;
    }

    @Override
    public void delete(Key key) {
        if (key == null)
            throw new NullPointerException("key must not be null");

        if (isEmpty())
            return;

        // finds the position i of key
        int i = hash(key);
        while (keys[i] != null && !keys[i].equals(key))
            i = i + 1 % m;

        // not found
        if (keys[i] == null)
            return;

        // deletes the key-value pair from the table
        keys[i] = null;
        values[i] = null;

        // rehashes all keys from the cluster to avoid empty spots
        i = i + 1 % m;
        while (keys[i] != null) {
            Key tmpKey = keys[i];
            Value tmpValue = values[i];

            keys[i] = null;
            values[i] = null;
            n--;

            // rehash
            put(tmpKey, tmpValue);
            i = i + 1 % m;
        }

        n--;

        // shrink size of array if necessary (12.5% full or less)
        if (n > 0 && n <= m / EIGHT_TIMES)
            resize(m / TWICE);
    }

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

    @Override
    public Iterable<Key> keys() {
        Stack<Key> stack = new Stack<>();

        for (int i = 0; i < m; i++)
            if (keys[i] != null)
                stack.push(keys[i]);

        return stack;
    }

    private void resize(int capacity) {
        LinearProbingHashTable<Key, Value> tmp = new LinearProbingHashTable<>(
                capacity);

        // copy all items from the current array
        for (int i = 0; i < m; i++)
            if (keys[i] != null)
                tmp.put(keys[i], values[i]);

        keys = tmp.keys;
        values = tmp.values;
        m = tmp.m;

    }
}
