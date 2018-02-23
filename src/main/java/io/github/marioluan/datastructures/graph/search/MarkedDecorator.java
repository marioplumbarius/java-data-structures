package io.github.marioluan.datastructures.graph.search;

/**
 * Decorator of {@link Graph graphs} which need marked functionality.
 */
public abstract class MarkedDecorator {

    /**
     * Returns the list of marked (and unmarked) vertices.
     *
     * @return the list of marked (and unmarked) vertices
     */
    protected abstract boolean[] getMarked();
}
