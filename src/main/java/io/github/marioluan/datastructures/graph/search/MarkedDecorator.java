package io.github.marioluan.datastructures.graph.search;

/**
 * Decorator which provides methods shared by {@link BreadthFirstSearch} and {@link DepthFirstSearch}.
 */
public abstract class MarkedDecorator {

    /**
     * List of marked (and unmarked) vertices.
     */
    protected boolean[] marked;

    /**
     * Returns the list of marked (and unmarked) vertices.
     *
     * @return the list of marked (and unmarked) vertices
     */
    public boolean[] getMarked() {
        return marked;
    }

}
