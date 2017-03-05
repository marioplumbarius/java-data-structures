package io.github.marioluan.datastructures.stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * {@link Stack} implementation using a generic linked list data structure.<br>
 * All operations take constant time in the worst case, except the
 * {@link #iterator} method.
 * 
 * @author marioluan
 * @param <T>
 *            the generic type of items from the stack
 */
public class LinkedList<T> implements Stack<T> {

    private final class Node {
        private T    data;
        private Node next;

        private Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node head;
    private int  n;

    /**
     * Constructs an empty stack.
     */
    public LinkedList() {
        this.head = null;
        this.n = 0;
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

        head = new Node(item, head);
        n++;
    }

    @Override
    public T pop() throws NoSuchElementException {
        if (isEmpty())
            throw new NoSuchElementException();

        T data = head.data;
        head = head.next;
        n--;

        return data;
    }

    @Override
    public T peek() throws NoSuchElementException {
        if (isEmpty())
            throw new NoSuchElementException();

        return head.data;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator(head);
    }

    private final class LinkedListIterator implements Iterator<T> {

        private Node cursor;

        public LinkedListIterator(Node cursor) {
            this.cursor = cursor;
        }

        @Override
        public boolean hasNext() {
            return cursor != null;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException();

            T data = cursor.data;
            cursor = cursor.next;

            return data;
        }

    }
}
