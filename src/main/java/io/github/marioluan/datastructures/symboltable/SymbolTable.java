package io.github.marioluan.datastructures.symboltable;

/**
 * Key-value pair abstraction of a symbol table data structure.
 * 
 * @author marioluan
 * @param <Key>
 *            the class type of the comparable keys
 * @param <Value>
 *            the class type of the values
 */
public interface SymbolTable<Key extends Comparable<Key>, Value> {

    /**
     * Puts key-value pair into the table. Overwrites old value with new
     * value, if present.<br>
     * <i>Removes key from table if value is null.</i>
     * 
     * @param key
     * @param value
     */
    void put(Key key, Value value);

    /**
     * Returns the value paired with key.
     * 
     * @param key
     * @return the value of null if key is absent.
     */
    Value get(Key key);

    /**
     * Removes key and its value from table.
     * 
     * @param key
     */
    void delete(Key key);

    /**
     * Checks if there is a value paired with key.
     * 
     * @param key
     * @return <code>true</code> if key exist;
     *         <code> false</code> otherwise.
     */
    boolean contains(Key key);

    /**
     * Checks if this table is empty.
     * 
     * @return returns <code>true</code> if the table is empty;
     *         <code>false</code> otherwise
     */
    boolean isEmpty();

    /**
     * Returns the number of key-value pairs in the table.
     * 
     * @return returns the number of key-value pairs in the table.
     */
    int size();

    /**
     * Returns all keys from the table.<br>
     * 
     * @return returns all keys from the table
     */
    Iterable<Key> keys();
}
