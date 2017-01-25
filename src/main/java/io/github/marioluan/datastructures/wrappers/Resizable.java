package io.github.marioluan.datastructures.wrappers;

/**
 * Provides functionality to resize objects.
 * 
 * @author marioluan
 * @param <T>
 *            the class type of the object to be resized.
 */
public interface Resizable<T> {

    /**
     * Resize the underlying object T holding the elements and returns a copy of
     * it.
     * <strong>Time complexity:</strong> O(n)<br>
     * 
     * @param a
     *            the array to be resized
     * @param n
     *            the number of elements on the array
     * @param capacity
     *            the new capacity of the array
     * @return the resized copy of a
     */
    T resize(T a, int n, int capacity);
}
