package io.github.marioluan.datastructures.graph;

/**
 * Representation of a Graph.
 */
public interface Graph {

    /**
     * Adds an edge v-w.
     *
     * @param v vertex v
     * @param w vertex w
     */
    void addEdge(int v, int w);

    /**
     * Returns the vertices adjacent to v.
     *
     * @param v vertex v
     * @return vertices adjacent to v
     */
    Iterable<Integer> adj(int v);

    /**
     * Returns the number of vertices.
     *
     * @return number of vertices
     */
    int V();

    /**
     * Returns the number of edges.
     *
     * @return number of edges
     */
    int E();

    /**
     * Returns its string representation.
     *
     * @return string representation
     */
    @Override
    String toString();
}
