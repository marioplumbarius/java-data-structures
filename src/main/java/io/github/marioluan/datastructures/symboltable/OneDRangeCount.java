package io.github.marioluan.datastructures.symboltable;

/**
 * 1D search range implementation using a {@link BinarySearchTree}.<br>
 * <i>This data structure may be used to implement Database queries.</i>
 * 
 * @author marioluan
 * @param <Key>
 *            the class type of the comparable key
 * @param <Value>
 *            the class type of the value
 */
public class OneDRangeCount<Key extends Comparable<Key>, Value>
        extends BinarySearchTree<Key, Value> {

    /**
     * Computes and returns the number of keys between {@link Key lo} and
     * {@link Key hi}.<br>
     * <strong>Time complexity:</strong> O(log N)
     * 
     * @param lo
     * @param hi
     * @return the number of keys between lo and hi
     */
    public int count(Key lo, Key hi) {
        if (contains(hi))
            return rank(hi) - rank(lo) + 1;
        else
            return rank(hi) - rank(lo);
    }

    /**
     * Finds and returns all keys between lo and hi.<br>
     * <strong>Time complexity:</strong> O(R + log N)
     * 
     * @param lo
     * @param hi
     * @return all keys between lo and hi
     */
    public Iterable<Key> range(Key lo, Key hi) {
        return this.keys(lo, hi);
    }
}
