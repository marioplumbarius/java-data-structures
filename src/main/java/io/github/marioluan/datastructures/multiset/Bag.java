package io.github.marioluan.datastructures.multiset;

import io.github.marioluan.datastructures.priority.queue.LinkedListIterator;
import io.github.marioluan.datastructures.Node;

import java.util.Iterator;

/**
 * Implementation of a multiset using a LinkedList.
 *
 * @param <T> class type of the items contained in the bag
 */
public class Bag<T extends Comparable<T>> implements Iterable<T> {
    private static final String CANNOT_BE_NULL_MSG = "item cannot be null";
    private Node<T> head;
    private int size;

    /**
     * Adds the item to the bag.
     *
     * @param item to be added
     * @throws IllegalArgumentException when item is null
     */
    public void add(T item) throws IllegalArgumentException {
        if (item == null)
            throw new IllegalArgumentException(CANNOT_BE_NULL_MSG);

        Node<T> node = new Node<>(item);
        Node<T> next = head;
        head = node;
        head.next = next;

        if (next != null)
            next.prev = head;

        size++;
    }

    /**
     * Returns whether the bag is empty or not.
     *
     * @return whether the bag is empty or not
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of items in the bag.
     *
     * @return number of items in the bag
     */
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator(head);
    }
}
