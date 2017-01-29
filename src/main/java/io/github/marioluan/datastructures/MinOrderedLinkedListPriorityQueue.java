package io.github.marioluan.datastructures;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Priority Queue implementation using an ordered linked list data type.
 * 
 * @author marioluan
 * @param <T>
 *            the class type of elements to be handled.
 */
public class MinOrderedLinkedListPriorityQueue<T extends Comparable<T>>
        implements Iterable<T> {

    private Node<T> head;
    private int     size;

    /**
     * Construct an empty priority queue.
     */
    public MinOrderedLinkedListPriorityQueue() {
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
     * Add the {@link T data} to the priority queue keeping the natural order
     * of nodes.<br>
     * <strong>Time complexity:</strong> O(N)
     * 
     * @param data
     */
    public void insert(T data) {
        if (data == null)
            throw new NullPointerException();

        Node<T> newNode = new Node<T>(data);

        if (this.head == null) {
            this.head = newNode;
        } else {
            Node<T> cursor = this.head;
            Node<T> min = this.head;

            // finds the min node
            while (cursor != null) {
                if (Util.less(cursor, newNode)) {
                    min = cursor;
                    cursor = cursor.next;
                }
            }

            // sets the newNode to its position
            newNode.next = min.next;
            min.next = newNode;
        }

        this.size++;
    }

    /**
     * Remove and return the smallest item from the priority queue. <br>
     * <strong>Time complexity:</strong> O(1)
     * 
     * @return returns the first item
     */
    public T removeMin() {
        if (this.isEmpty())
            throw new NoSuchElementException();

        Node<T> oldHead = this.head;

        if (this.head.hasNext())
            this.head = this.head.next;

        this.size--;

        return oldHead.data;
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
