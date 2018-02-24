package io.github.marioluan.datastructures.graph.search;

import io.github.marioluan.datastructures.graph.Graph;

/**
 * Depth-first search implementation. <br>
 * Put unvisited vertices on a stack. <br>
 * Goal: Find all vertices connected to s (and a corresponding path).
 */
public class DepthFirstSearch extends PathDecorator {
    private final Graph graph;
    private boolean[] marked;
    private Integer[] edgeTo;

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

    // Time complexity: O(V + E)
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

    @Override
    protected Integer[] getEdgeTo() {
        return edgeTo;
    }

    @Override
    protected boolean[] getMarked() {
        return marked;
    }
}
