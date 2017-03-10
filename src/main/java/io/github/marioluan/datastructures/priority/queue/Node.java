package io.github.marioluan.datastructures.priority.queue;

/**
 * Implementation of a node data structure with a link to its next and previous
 * nodes.
 * 
 * @author marioluan
 * @param <T>
 *            the class type of the data being handled
 */
public class Node<T extends Comparable<T>> implements Comparable<Node<T>> {

    /**
     * The value of the node.
     */
    public T data;

    /**
     * A link to the next node.
     */
    public Node<T> next;

    /**
     * A link to the previous node.
     */
    public Node<T> prev;

    /**
     * Construct a new node with the given data.
     * 
     * @param data
     */
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
