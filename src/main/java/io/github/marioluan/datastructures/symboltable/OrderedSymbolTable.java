package io.github.marioluan.datastructures.symboltable;

/**
 * Specification for an ordered {@link SymbolTable} implementations.
 * 
 * @author marioluan
 * @param <Key>
 *            the class type of the comparable key
 * @param <Value>
 *            the class type of the value
 */
public interface OrderedSymbolTable<Key extends Comparable<Key>, Value>
        extends SymbolTable<Key, Value> {

    /**
     * Returns the smallest key.
     * 
     * @return the smallest key
     */
    Key min();

    /**
     * Returns the largest key.
     * 
     * @return the largest key
     */
    Key max();

    /**
     * Returns the largest key less than or equal to key.
     * 
     * @param key
     * @return the largest key less than or equal to key.
     */
    Key floor(Key key);

    /**
     * Returns the smallest key greater than or equal to key.
     * 
     * @param key
     * @return the smallest key greater than or equal to key.
     */
    Key ceiling(Key key);

    /**
     * Returns the number of keys less than key.
     * 
     * @param key
     * @return the number of keys less than key.
     */
    int rank(Key key);

    /**
     * Returns the key of rank k.
     * 
     * @param k
     * @return the key of rank k.
     */
    Key select(int k);

    /**
     * Deletes the smallest key.
     */
    void deleteMin();

    /**
     * Deletes the largest key.
     */
    void deleteMax();

    /**
     * Returns all keys in [lo..hi], in sorted order.
     * 
     * @param lo
     * @param hi
     * @return all keys in [lo..hi], in sorted order.
     */
    Iterable<Key> keys(Key lo, Key hi);
}
