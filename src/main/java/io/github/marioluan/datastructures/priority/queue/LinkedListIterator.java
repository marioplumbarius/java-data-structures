package io.github.marioluan.datastructures.priority.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implements an Iterator for a LinkedList data type.
 * 
 * @author marioluan
 * @param <T>
 *            the class type of the data from nodes
 */
public class LinkedListIterator<T extends Comparable<T>>
        implements Iterator<T> {
    private Node<T> cursor;

    /**
     * Constructs the iterator by pointing its cursor to the head.
     */
    public LinkedListIterator(Node<T> head) {
        this.cursor = head;
    }

    /**
     * {@inheritDoc}
     */
    public boolean hasNext() {
        return this.cursor != null;
    }

    /**
     * Operation not permitted.
     * 
     * @throws UnsupportedOperationException
     */
    public void remove() {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    public T next() {
        if (!hasNext())
            throw new NoSuchElementException();

        T oldItem = this.cursor.data;
        this.cursor = this.cursor.next;

        return oldItem;
    }
}
