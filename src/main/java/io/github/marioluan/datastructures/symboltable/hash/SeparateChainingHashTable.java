package io.github.marioluan.datastructures.symboltable.hash;

import edu.princeton.cs.algs4.Stack;
import io.github.marioluan.datastructures.symboltable.SymbolTable;

/**
 * {@link SymbolTable} implementation using a separate chaining hash data
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
public class SeparateChainingHashTable<Key extends Comparable<Key>, Value>
        implements SymbolTable<Key, Value> {

    private static final class Node {
        private Object key;
        private Object value;
        private Node   next;

        private Node(Object key, Object value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private Node[]              buckets;
    private int                 n;
    private int                 m;
    private static final int    UNSIGNED_CONST = 0x7fffffff;
    private static final double FIVE           = 5.0;
    private static final int    FOUR_TIMES     = 4;
    private static final int    TWICE          = 2;

    /**
     * Constructs a new hash table with the given capacity.
     * 
     * @param capacity
     */
    public SeparateChainingHashTable(int capacity) {
        this.buckets = new Node[capacity + 1];
        this.n = 0;
        this.m = computeM();
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

        // doubles size of array if necessary
        if (size() == buckets.length)
            resize(TWICE * buckets.length);

        // handles insertion
        int i = hash(key);
        for (Node node = buckets[i]; node != null; node = node.next)
            // handles updates
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }

        // adds the node to the top of the bucket
        buckets[i] = new Node(key, value, buckets[i]);
        n++;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Value get(Key key) {
        int i = hash(key);

        for (Node node = buckets[i]; node != null; node = node.next)
            if (node.key.equals(key))
                return (Value) node.value;

        return null;
    }

    @Override
    public void delete(Key key) {
        if (key == null)
            throw new NullPointerException("key must not be null");

        if (isEmpty())
            return;

        int i = hash(key);
        Node parent = buckets[i];
        Node cursor = buckets[i];

        // finds the cursor
        while (!cursor.key.equals(key) && cursor != null) {
            parent = cursor;
            cursor = cursor.next;
        }

        // not found
        if (cursor == null)
            return;

        n--;

        // cursor at the top of the linked list
        if (cursor.key.equals(buckets[i].key)) {
            buckets[i] = cursor.next;
            return;
        }

        // cursor at the middle/bottom of the linked list
        parent.next = cursor.next;

        // shrink size of array if necessary
        if (n > 0 && n == buckets.length / FOUR_TIMES)
            resize(buckets.length / TWICE);
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

    @SuppressWarnings("unchecked")
    @Override
    public Iterable<Key> keys() {
        Stack<Key> keys = new Stack<>();

        for (int i = 0; i < buckets.length; i++) {
            Node bucket = buckets[i];

            while (bucket != null) {
                keys.push((Key) bucket.key);
                bucket = bucket.next;
            }
        }

        return keys;
    }

    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        Node[] copy = buckets;

        // resets the instance with the new capacity
        buckets = new Node[capacity + 1];
        n = 0;
        m = computeM();

        // copy all items from the old array
        for (int i = 0; i < copy.length; i++) {
            Node bucket = copy[i];

            // copy all items from the bucket
            while (bucket != null) {
                put((Key) bucket.key, (Value) bucket.value);
                bucket = bucket.next;
            }
        }

    }

    /**
     * Computes and returns the new value of m.
     *
     * @return the new value of m
     */
    private int computeM() {
        return (int) Math.ceil(buckets.length / FIVE);
    }
}
