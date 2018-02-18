package io.github.marioluan.datastructures.graph.search;

import io.github.marioluan.datastructures.graph.Graph;

/**
 * Depth-first search implementation. <br>
 * Goal: Find all vertices connected to s (and a corresponding path).
 */
public class DepthFirstSearch extends Decorator {
    private final Graph graph;

    /**
     * Performs a DFS.
     *
     * @param graph the given graph
     * @param s     the source vertex
     */
    public DepthFirstSearch(Graph graph, int s) {
        this.graph = graph;
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
}
