package io.github.marioluan.datastructures.graph.search;

/**
 * Decorator which provides methods shared by {@link BreadthFirstSearch} and {@link DepthFirstSearch}.
 */
public abstract class Decorator {

    /**
     * List of all edges found connecting source vertex to all possible destination vertices.
     */
    protected Integer[] edgeTo;

    /**
     * List of marked (and unmarked) vertices.
     */
    protected boolean[] marked;

    /**
     * List of distance between source and destination vertices.
     */
    protected Integer[] disTo;

    /**
     * Returns all edges found connecting source vertex to all possible destination vertices.
     *
     * @return edges found connecting source vertex to all possible destination vertices
     */
    public Integer[] getEdgeTo() {
        return edgeTo;
    }

    /**
     * Returns the list of marked (and unmarked) vertices.
     *
     * @return the list of marked (and unmarked) vertices
     */
    public boolean[] getMarked() {
        return marked;
    }

    /**
     * Returns the list of distance between source and destination vertices.
     *
     * @return the list of distance between source and destination vertices
     */
    public Integer[] getDistTo() {
        return disTo;
    }

}
