package io.github.marioluan.datastructures.graph.search;

import io.github.marioluan.datastructures.graph.Graph;

/**
 * Depth-first search implementation. <br>
 * Goal: Find all vertices connected to s (and a corresponding path).
 */
public class DepthFirstSearch {
    private final Graph graph;
    private boolean[] marked;
    private Integer[] edgeTo;
    private final int s;

    /**
     * Performs a DFS.
     *
     * @param graph the given graph
     * @param s     the source vertex
     */
    public DepthFirstSearch(Graph graph, int s) {
        this.graph = graph;
        this.s = s;
        marked = new boolean[graph.V()];
        edgeTo = new Integer[graph.V()];

        search(s);
    }

    // Time complexity: proportional to DFS.getEdgeTo's length
    private void search(int v) {
        marked[v] = true;

        for (int w : graph.adj(v)) {

            // already marked
            // also stop condition to recursive search method
            if (marked[w])
                continue;

            search(w);
            edgeTo[w] = v;
        }
    }

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
}
