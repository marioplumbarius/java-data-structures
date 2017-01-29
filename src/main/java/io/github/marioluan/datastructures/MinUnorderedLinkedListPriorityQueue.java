package io.github.marioluan.datastructures;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Priority Queue implementation using an unordered linked list data type. <br>
 * 
 * @author marioluan
 * @param <T>
 *            the class type of elements to be handled.
 */
public class MinUnorderedLinkedListPriorityQueue<T extends Comparable<T>>
        implements Iterable<T> {

    private Node<T> head;
    private int     size;

    /**
     * Construct an empty priority queue.
     */
    public MinUnorderedLinkedListPriorityQueue() {
        this.head = null;
        this.size = 0;
    }

    /**
     * Is the priority queue empty?
     * 
     * @return returns if it is empty or not
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Return the number of items on the priority queue.
     * 
     * @return the size
     */
    public int size() {
        return this.size;
    }

    /**
     * Add the {@link T item} to the front of the priority queue.<br>
     * <strong>Time complexity:</strong> O(1)
     * 
     * @param item
     */
    public void insert(T item) {
        if (item == null)
            throw new NullPointerException();

        Node<T> oldHead = this.head;
        this.head = new Node<T>(item);
        this.head.next = oldHead;

        if (!this.isEmpty())
            oldHead.prev = this.head;

        this.size++;
    }

    /**
     * Remove and return the smallest item from the priority queue.<br>
     * <strong>Time complexity:</strong> O(N)
     * 
     * @return returns the first item
     */
    public T removeMin() {
        if (this.isEmpty())
            throw new NoSuchElementException();

        Node<T> min = this.head;
        Node<T> cursor = this.head;

        while (cursor != null) {
            if (Util.less(cursor, min))
                min = cursor;

            cursor = cursor.next;
        }

        if (min.hasPrev())
            min.prev.next = min.next;

        this.size--;

        return min.data;
    }

    /**
     * Return the iterator over items in order from front to end.
     * 
     * @return returns a new instance
     */
    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator<T>(this.head);
    }
}
