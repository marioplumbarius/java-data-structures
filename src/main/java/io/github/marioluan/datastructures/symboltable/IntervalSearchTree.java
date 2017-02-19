package io.github.marioluan.datastructures.symboltable;

import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.Queue;

/**
 * 1D interval search tree implementation using a symbol table data
 * structure.<br>
 * <strong>Nondegeneracy assumption:</strong> No two intervals have the same
 * left endpoint.
 * 
 * @author marioluan
 * @param <Key>
 *            the class type of the comparable key
 * @param <Value>
 *            the class type of the value
 */
public class IntervalSearchTree<Key extends Comparable<Key>, Value> {

    /**
     * Representation of a symbol table interval.
     * 
     * @author marioluan
     */
    private final class Interval {
        private Value value;
        private Key   lo;
        private Key   hi;

        private Interval(Key lo, Key hi, Value value) {
            this.lo = lo;
            this.hi = hi;
            this.value = value;
        }

        /**
         * Checks whether this interval is overlapped by the given interval.
         * 
         * @param thatLo
         * @param thatHi
         * @return whether this interval is overlapped by the given interval.
         */
        private boolean overlaps(Key thatLo, Key thatHi) {
            boolean loOverlap = this.lo.compareTo(thatLo) <= 0
                    && this.hi.compareTo(thatLo) >= 0;
            boolean hiOverlap = this.lo.compareTo(thatHi) <= 0
                    && this.hi.compareTo(thatHi) >= 0;
            return loOverlap || hiOverlap;
        }

        /**
         * Checks whether this interval is equal to the given interval.
         * 
         * @param thatLo
         * @param thatHi
         * @return whether this interval is equal to the given interval
         */
        private boolean equals(Key thatLo, Key thatHi) {
            return this.lo.compareTo(thatLo) == 0
                    && this.hi.compareTo(thatHi) == 0;
        }
    }

    /**
     * Representation of the nodes from the symbol table.
     * 
     * @author marioluan
     */
    private final class Node {
        private Key      key;
        private Interval interval;
        private Key      max;

        private Node left;
        private Node right;

        private Node(Key lo, Key hi, Key max, Value value) {
            this.interval = new Interval(lo, hi, value);
            this.key = lo;
            this.max = max;
        }
    }

    private Node root;

    /**
     * Inserts interval-value pair into the symbol table, using the {@link Key
     * lo} as the key.<br>
     * <strong>Time complexity:</strong> O(log N)
     * 
     * @param lo
     * @param hi
     * @param value
     */
    public void put(Key lo, Key hi, Value value) {
        if (lo == null || hi == null)
            throw new NullPointerException("lo and hi must not be null");

        root = put(root, lo, hi, value);
    }

    /**
     * Recursive wrapper for {@link #put} method which inserts a new
     * interval-pair into the symbol table.
     * 
     * @param cursor
     * @param lo
     * @param hi
     * @param value
     * @return the updated root node after the insert
     */
    private Node put(Node cursor, Key lo, Key hi, Value value) {
        if (cursor == null)
            return new Node(lo, hi, hi, value);

        // maintains the max endpoint for each node
        if (hi.compareTo(cursor.max) > 0)
            cursor.max = hi;

        // finds the place to put the node
        int cmp = cursor.key.compareTo(lo);
        if (cmp > 0)
            cursor.left = put(cursor.left, lo, hi, value);
        else if (cmp < 0)
            cursor.right = put(cursor.right, lo, hi, value);
        else
            throw new IllegalArgumentException("lo key already exists");

        return cursor;
    }

    /**
     * Finds and returns the value paired with the given interval.<br>
     * <strong>Time complexity:</strong> O(log N)
     * 
     * @param lo
     * @param hi
     * @return the value paired with the given interval
     */
    public Value get(Key lo, Key hi) {
        if (lo == null || hi == null)
            throw new NullPointerException("lo and hi must not be null");

        Node cursor = root;

        while (cursor != null) {
            if (cursor.interval.equals(lo, hi))
                return cursor.interval.value;
            else if (cursor.key.compareTo(lo) > 0)
                cursor = cursor.left;
            else if (cursor.key.compareTo(lo) < 0)
                cursor = cursor.right;
            else
                break;
        }

        return null;
    }

    /**
     * Finds and returns any one interval that intersects the given
     * interval.<br>
     * <strong>Time complexity:</strong> O(log N)
     * 
     * @param lo
     * @param hi
     * @return the value paired with the given interval
     */
    public Interval getAnyIntersection(Key lo, Key hi) {
        if (lo == null || hi == null)
            throw new NullPointerException("lo and hi must not be null");

        Node cursor = root;

        while (cursor != null) {
            if (cursor.interval.overlaps(lo, hi))
                return cursor.interval;
            else if (cursor.left == null)
                cursor = cursor.right;
            else if (cursor.left.max.compareTo(lo) > 0)
                cursor = cursor.right;
            else
                cursor = cursor.left;
        }

        return null;
    }

    /**
     * Deletes the given interval.<br>
     * <strong>Time complexity:</strong> O(log N)
     * 
     * @param lo
     * @param hi
     */
    public void delete(Key lo, Key hi) {
        if (lo == null || hi == null)
            throw new NullPointerException("lo and hi must not be null");

        if (isEmpty())
            throw new NoSuchElementException();

        root = delete(root, lo, hi);
    }

    /**
     * Recursive wrapper for {@link #delete} method.
     * 
     * @param cursor
     * @param lo
     * @param hi
     * @return the updated cursor node after the deletion
     */
    private Node delete(Node cursor, Key lo, Key hi) {
        // not found
        if (cursor == null)
            throw new NoSuchElementException();

        int cmp = cursor.key.compareTo(lo);

        if (cursor.interval.equals(lo, hi)) {
            if (cursor.right == null)
                return cursor.left;

            if (cursor.left == null)
                return cursor.right;

            Node toBeDeleted = cursor;

            // finds the minimum node from the right subtree and replaces it
            // with the node to be deleted
            cursor = min(toBeDeleted.right);

            // deletes the previous node found and returns its updated right
            // subtree
            cursor.right = deleteMin(toBeDeleted.right);

            // points its left subtree to the deleted node left subtree
            cursor.left = toBeDeleted.left;
        } else if (cmp > 0)
            cursor.left = delete(cursor.left, lo, hi);
        else if (cmp < 0)
            cursor.right = delete(cursor.right, lo, hi);

        return cursor;
    }

    /**
     * Deletes the smallest key from the {@link Node cursor} tree and returns
     * the updated tree. <br>
     * <strong>Time complexity:</strong> O(log N)
     * 
     * @param cursor
     * @return the updated tree
     */
    private Node deleteMin(Node cursor) {
        if (cursor.left == null)
            return cursor.right;

        cursor.left = deleteMin(cursor.left);

        return cursor;
    }

    /**
     * Wrapper which returns the node with the smallest key from the {@link Node
     * cursor} tree. <br>
     * <strong>Time complexity:</strong> O(log N)
     * 
     * @param cursor
     * @return the node with the smallest key from the cursor tree
     */
    private Node min(Node cursor) {
        while (cursor.left != null)
            cursor = cursor.left;

        return cursor;
    }

    /**
     * Finds and returns all intervals that overlaps the given interval.<br>
     * <strong>Time complexity:</strong> O(R log N)
     * 
     * @param lo
     * @param hi
     * @return all intervals that overlaps the given interval
     */
    public Iterable<Value> intersects(Key lo, Key hi) {
        if (lo == null || hi == null)
            throw new NullPointerException("lo and hi must not be null");

        Queue<Value> intersects = new Queue<>();
        Node cursor = root;

        while (cursor != null) {
            // intersection found
            if (cursor.interval.overlaps(lo, hi))
                intersects.enqueue(cursor.interval.value);

            // go down the tree
            if (cursor.left == null)
                cursor = cursor.right;
            else if (cursor.left.max.compareTo(lo) < 0)
                cursor = cursor.right;
            else
                cursor = cursor.left;
        }

        return intersects;
    }

    /**
     * Checks whether the symbol table is empty.
     * 
     * @return whether the symbol table is empty
     */
    public boolean isEmpty() {
        return root == null;
    }

}
