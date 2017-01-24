package io.github.marioluan.datastructures;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Doubly linked list implementation of a double-ended queue data type.<br>
 * <strong>Space complexity:</strong> O(n)<br>
 * 
 * @author marioluan
 * @param <Item>
 *            the class type of elements to be handled.
 */
public class Deque<Item> implements Iterable<Item> {

    /**
     * Representation of items from a Deque.
     * 
     * @author marioluan
     */
    private class DoublyLinkedNode {
        private Item             item;
        private DoublyLinkedNode next;
        private DoublyLinkedNode prev;
    }

    private DoublyLinkedNode head;
    private DoublyLinkedNode tail;
    private int              size;

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
     * Add the {@link Item item} to the front.<br>
     * <strong>Time complexity:</strong> O(1)<br>
     * 
     * @param item
     */
    public void addFirst(Item item) {
        if (item == null)
            throw new NullPointerException();

        DoublyLinkedNode oldHead = this.head;
        this.head = new DoublyLinkedNode();
        this.head.item = item;
        this.head.next = oldHead;

        if (oldHead != null)
            oldHead.prev = this.head;

        // when the deque is empty, head == tail
        if (this.isEmpty())
            this.tail = this.head;

        this.size++;

    }

    /**
     * Add the {@link Item item} to the end.<br>
     * <strong>Time complexity:</strong> O(1)<br>
     * 
     * @param item
     */
    public void addLast(Item item) {
        if (item == null)
            throw new NullPointerException();

        DoublyLinkedNode oldTail = this.tail;
        this.tail = new DoublyLinkedNode();
        this.tail.item = item;
        this.tail.prev = oldTail;

        if (oldTail != null)
            oldTail.next = this.tail;

        // when the deque is empty, head == tail
        if (this.isEmpty())
            this.head = this.tail;

        this.size++;
    }

    /**
     * Remove and return the item from the front.
     * <strong>Time complexity:</strong> O(1)<br>
     * 
     * @return returns the first item
     */
    public Item removeFirst() {
        if (this.isEmpty())
            throw new NoSuchElementException();

        DoublyLinkedNode oldHead = this.head;
        this.head = this.head.next;

        if (this.head != null)
            this.head.prev = null;

        this.size--;

        // when the deque becomes empty, head == tail
        if (this.isEmpty())
            this.tail = null;

        return oldHead.item;
    }

    /**
     * Remove and return the item from the end.
     * <strong>Time complexity:</strong> O(1)<br>
     * 
     * @return returns the last item
     */
    public Item removeLast() {
        if (this.isEmpty())
            throw new NoSuchElementException();

        DoublyLinkedNode oldTail = this.tail;
        this.tail = this.tail.prev;

        if (this.tail != null)
            this.tail.next = null;

        this.size--;

        // when the deque becomes empty, head == tail
        if (this.isEmpty())
            this.head = null;

        return oldTail.item;
    }

    /**
     * Return the iterator over items in order from front to end.
     * 
     * @return returns a new instance
     */
    @Override
    public Iterator<Item> iterator() {
        return new LinkedListIterator();
    }

    /**
     * Implements an Iterator for a LinkedList data type.
     * <strong>Time complexity:</strong> O(n)<br>
     * 
     * @author marioluan
     */
    private class LinkedListIterator implements Iterator<Item> {
        private DoublyLinkedNode current;

        /**
         * Constructs the iterator by pointing its cursor to the head.
         */
        public LinkedListIterator() {
            this.current = head;
        }

        public boolean hasNext() {
            return this.current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();

            Item oldItem = this.current.item;

            // moves the cursor
            this.current = this.current.next;

            return oldItem;
        }
    }
}
