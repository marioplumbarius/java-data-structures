package io.github.marioluan.datastructures.symboltable;

import edu.princeton.cs.algs4.Stack;

/**
 * {@link SymbolTable} implementation using an unordered linked list data
 * structure.
 * 
 * @author marioluan
 * @param <Key>
 *            the class type of the comparable key
 * @param <Value>
 *            the class type of the value
 */
public class LinkedListSymbolTable<Key extends Comparable<Key>, Value>
        implements SymbolTable<Key, Value> {

    @SuppressWarnings("hiding")
    private class Node<Key, Value> {
        public final Key        key;
        public Value            value;
        public Node<Key, Value> next;
        public Node<Key, Value> prev;

        Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }

        public boolean hasNext() {
            return next != null;
        }

        public boolean hasPrev() {
            return prev != null;
        }
    }

    private Node<Key, Value> head;
    private int              size;

    // Time complexity: O(n)
    @Override
    public void put(Key key, Value value) {
        if (key == null)
            throw new NullPointerException("key must not be null");

        Node<Key, Value> newNode = new Node<>(key, value);

        // first time adding new node
        if (isEmpty()) {
            if (value != null) {
                head = newNode;
                size++;
            }

            return;
        }

        // tries to find the node with the given key
        Node<Key, Value> cursor = head;
        while (!cursor.key.equals(key) && cursor.hasNext())
            cursor = cursor.next;
        // if the key already exists in the table
        if (cursor.key.equals(key)) {
            if (value == null) {
                // removes it from table
                if (cursor.hasPrev())
                    cursor.prev.next = cursor.next;
                else
                    head = cursor.next;
                size--;
                return;
            } else {
                // only adds new key with not null values
                if (value != null)
                    cursor.value = value;
                return;
            }
        }

        // inserts a new node into the table
        Node<Key, Value> tmp = head;
        head = newNode;
        head.next = tmp;

        size++;
    }

    // Time complexity: O(n)
    @Override
    public Value get(Key key) {
        if (key == null)
            throw new NullPointerException("key must not be null");

        if (isEmpty())
            return null;

        Node<Key, Value> cursor = head;
        while (!cursor.key.equals(key) && cursor.hasNext())
            cursor = cursor.next;

        if (cursor.key.equals(key))
            return cursor.value;

        return null;
    }

    @Override
    public void delete(Key key) {
        put(key, null);
    }

    @Override
    public boolean contains(Key key) {
        return get(key) != null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterable<Key> keys() {
        Stack<Key> keys = new Stack<>();

        if (isEmpty())
            return keys;

        Node<Key, Value> headCopy = head;

        keys.push(headCopy.key);

        while (headCopy.hasNext()) {
            headCopy = headCopy.next;

            keys.push(headCopy.key);
        }

        return keys;
    }
}
