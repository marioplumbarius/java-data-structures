package io.github.marioluan.datastructures;

/**
 * Implementation of a node data structure with a link to its next and previous
 * nodes.
 * 
 * @author marioluan
 * @param <T>
 *            the class type of the data being handled
 */
public class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
    public T       data;
    public Node<T> next;
    public Node<T> prev;

    public Node(T data) {
        this.data = data;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(Node<T> that) {
        return this.data.compareTo(that.data);
    }

    /**
     * Checks whether there is a link to next node.
     * 
     * @return whether the next field is not null
     */
    public boolean hasNext() {
        return this.next != null;
    }

    /**
     * Checks whether there is a link to previous node.
     * 
     * @return whether the prev field is not null
     */
    public boolean hasPrev() {
        return this.prev != null;
    }
}