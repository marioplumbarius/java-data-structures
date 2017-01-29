package io.github.marioluan.datastructures;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Double-ended queue implementation using a doubly linked list data
 * structure.<br>
 * <strong>Space complexity:</strong> O(n)<br>
 * 
 * @author marioluan
 * @param <T>
 *            the class type of elements to be handled.
 */
public class Deque<T extends Comparable<T>> implements Iterable<T> {

    private Node<T> head;
    private Node<T> tail;
    private int     size;

    /**
     * Construct an empty deque.
     */
    public Deque() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Is the deque empty?
     * 
     * @return returns if it is empty or not
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Return the number of items on the deque.
     * 
     * @return the size
     */
    public int size() {
        return this.size;
    }

    /**
     * Add the {@link T data} to the front.<br>
     * <strong>Time complexity:</strong> O(1)<br>
     * 
     * @param data
     */
    public void addFirst(T data) {
        if (data == null)
            throw new NullPointerException();

        Node<T> oldHead = this.head;
        this.head = new Node<T>(data);
        this.head.next = oldHead;

        if (oldHead != null)
            oldHead.prev = this.head;

        // when the deque is empty, head == tail
        if (this.isEmpty())
            this.tail = this.head;

        this.size++;

    }

    /**
     * Add the {@link T data} to the end.<br>
     * <strong>Time complexity:</strong> O(1)<br>
     * 
     * @param data
     */
    public void addLast(T data) {
        if (data == null)
            throw new NullPointerException();

        Node<T> oldTail = this.tail;
        this.tail = new Node<T>(data);
        this.tail.prev = oldTail;

        if (oldTail != null)
            oldTail.next = this.tail;

        // when the deque is empty, head == tail
        if (this.isEmpty())
            this.head = this.tail;

        this.size++;
    }

    /**
     * Remove and return the data from the front.
     * <strong>Time complexity:</strong> O(1)<br>
     * 
     * @return returns the first data
     */
    public T removeFirst() {
        if (this.isEmpty())
            throw new NoSuchElementException();

        Node<T> oldHead = this.head;
        this.head = this.head.next;

        if (this.head != null)
            this.head.prev = null;

        this.size--;

        // when the deque becomes empty, head == tail
        if (this.isEmpty())
            this.tail = null;

        return oldHead.data;
    }

    /**
     * Remove and return the data from the end.
     * <strong>Time complexity:</strong> O(1)<br>
     * 
     * @return returns the last data
     */
    public T removeLast() {
        if (this.isEmpty())
            throw new NoSuchElementException();

        Node<T> oldTail = this.tail;
        this.tail = this.tail.prev;

        if (this.tail != null)
            this.tail.next = null;

        this.size--;

        // when the deque becomes empty, head == tail
        if (this.isEmpty())
            this.head = null;

        return oldTail.data;
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
