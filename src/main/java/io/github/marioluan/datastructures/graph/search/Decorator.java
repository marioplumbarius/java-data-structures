package io.github.marioluan.datastructures.graph.search;

/**
 * Decorator which provides methods shared by {@link BreadthFirstSearch} and {@link DepthFirstSearch}.
 */
public abstract class Decorator {
    protected Integer[] edgeTo;
    protected boolean[] marked;
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
