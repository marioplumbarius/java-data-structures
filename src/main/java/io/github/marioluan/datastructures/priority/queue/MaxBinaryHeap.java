package io.github.marioluan.datastructures.priority.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

import io.github.marioluan.datastructures.Util;

/**
 * Complete binary tree implementation using a max-heap data structure.<br>
 * TODO: implement resizable functionality.
 * 
 * @author marioluan
 * @param <Key>
 *            the class type of the key being handled
 */
public class MaxBinaryHeap<Key> implements Iterable<Key> {

    private static final int LARGEST_KEY_INDEX = 1;
    private int              n;
    private Key[]            keys;

    /**
     * Construct an empty priority queue.
     * 
     * @param capacity
     */
    @SuppressWarnings("unchecked")
    public MaxBinaryHeap(int capacity) {
        this.keys = (Key[]) new Object[capacity + 1];
        this.n = 0;
    }

    /**
     * Is the heap empty?
     * 
     * @return returns whether it is empty or not
     */
    public boolean isEmpty() {
        return this.n == 0;
    }

    /**
     * Returns the size of the heap.
     * 
     * @return the size
     */
    public int size() {
        return this.n;
    }

    /**
     * Returns (but does not remove) the largest key from the heap.
     * 
     * @return the largest key
     */
    public Key max() {
        return this.keys[LARGEST_KEY_INDEX];
    }

    /**
     * Add the key to the heap by performing an up-heap operation.<br>
     * <strong>Time complexity:</strong> O(log n)
     * 
     * @param key
     */
    public void insert(Key key) {
        if (key == null)
            throw new NullPointerException();

        this.keys[++this.n] = key;
        this.swim(this.n);
    }

    /**
     * Remove and return the max key from the heap by performing a down-heap
     * operation.<br>
     * <strong>Time complexity:</strong> O(log n)
     * 
     * @return the largest key
     */
    public Key delMax() {
        if (this.isEmpty())
            throw new NoSuchElementException();

        Key max = this.keys[LARGEST_KEY_INDEX];
        Util.swap(this.keys, LARGEST_KEY_INDEX, this.n--);

        this.sink(LARGEST_KEY_INDEX);

        // prevent loitering
        this.keys[this.n + 1] = null;

        return max;
    }

    /**
     * Performs the up-head operation, by swapping the key k with keys lesser
     * than it. At the end. the heap will remain in order.<br>
     * <strong>Time complexity:</strong> O(log n)
     * 
     * @param k
     */
    private void swim(int k) {
        int child = k;
        int parent = child / 2;

        while (child > 1 && Util.less(this.keys[parent], this.keys[child])) {
            Util.swap(this.keys, child, parent);
            child = parent;
        }
    }

    /**
     * Performs the down-heap operation, by swapping the key k with keys greater
     * than it. At the end, the greater key will be at the top of the queue.<br>
     * <strong>Time complexity:</strong> O(log n)
     * 
     * @param k
     *            the parent key
     */
    private void sink(int k) {
        /**
         * PS: I could have used less variables and make the code less verbose,
         * but I chose to make use of human-readable variable names, so I can
         * easily recall what is going on when I get back to the code after some
         * time.
         */

        int parent = k;
        int leftChild = 2 * parent;
        int rightChild = leftChild + 1;

        // sets the left one as the greater one by default
        int greaterChild = leftChild;

        while (leftChild <= n) {

            // is right key greater than left key?
            if (leftChild < n
                    && Util.less(this.keys[leftChild], this.keys[rightChild]))
                greaterChild = rightChild;

            // when both right and left child are not greater than parent
            if (!Util.less(this.keys[parent], this.keys[greaterChild]))
                break;

            // moves the greater key up
            Util.swap(this.keys, parent, greaterChild);

            // update variable references
            parent = greaterChild;
            leftChild = 2 * parent;
            rightChild = leftChild + 1;
            greaterChild = leftChild;
        }
    }

    @Override
    public Iterator<Key> iterator() {
        return new BinaryHeapIterator<Key>();
    }

    /**
     * Implements an Iterator for a Binary Heap data structure.
     * 
     * @author marioluan
     * @param <Key>
     *            the class type of the keys
     */
    @SuppressWarnings("hiding")
    private class BinaryHeapIterator<Key> implements Iterator<Key> {

        private MaxBinaryHeap<Key> copyHeap;

        /**
         * Constructs the iterator by making a copy of the current instance.<br>
         * <strong>Time complexity:</strong> O(N), because the heap is already
         * in order, so we do not need to rearrange keys.
         */
        @SuppressWarnings({ "rawtypes", "unchecked" })
        public BinaryHeapIterator() {
            this.copyHeap = new MaxBinaryHeap(size());
            Util.copy(keys, this.copyHeap.keys, 0, size());
        }

        /**
         * {@inheritDoc}
         */
        public boolean hasNext() {
            return !this.copyHeap.isEmpty();
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
         * Returns the keys from the heap in descending order.
         */
        public Key next() {
            if (!hasNext())
                throw new NoSuchElementException();

            return copyHeap.delMax();
        }
    }
}
