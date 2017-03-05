package io.github.marioluan.datastructures.stack;

import java.util.NoSuchElementException;

/**
 * Generic representation of a LIFO (last-in-first-out) stack data structure.
 * 
 * @author marioluan
 * @param <T>
 *            the generic type of items from the stack
 */
public interface Stack<T> extends Iterable<T> {

    /**
     * Checks whether the stack is empty.
     * 
     * @return whether the stack is empty
     */
    boolean isEmpty();

    /**
     * Returns the size of the stack.
     * 
     * @return the size of the stack
     */
    int size();

    /**
     * Adds the {@link T item} to the top of the stack.
     *
     * @param item
     * @throws IllegalArgumentException
     *             if {@link T item} is null
     */
    void push(T item) throws IllegalArgumentException;

    /**
     * Removes and returns the item at the top of the stack.
     * 
     * @return the item at the top of the stack
     * @throws NoSuchElementException
     *             if the stack is empty
     */
    T pop() throws NoSuchElementException;

    /**
     * Returns the item at the top of the stack.
     * 
     * @return the item at the top of the stack
     * @throws NoSuchElementException
     *             if the stack is empty
     */
    T peek() throws NoSuchElementException;
}
