package io.github.marioluan.datastructures.graph.search;

import io.github.marioluan.datastructures.graph.Graph;
import io.github.marioluan.datastructures.queue.Deque;

/**
 * Bread-first search implementation. <br>
 * Goal: Find all vertices connected to s (and a corresponding path).
 */
public class BreadthFirstSearch {
    private final Graph graph;
    private boolean[] marked;
    private Integer[] edgeTo;
    private Integer[] disTo;
    private final int s;

    /**
     * Performs a BFS.
     *
     * @param graph the given graph
     * @param s     the source vertex
     */
    public BreadthFirstSearch(Graph graph, int s) {
        this.graph = graph;
        this.s = s;
        marked = new boolean[graph.V()];
        edgeTo = new Integer[graph.V()];
        disTo = new Integer[graph.V()];

        search(s);
    }

    // TODO: time complexity
    private void search(int s) {
        Deque<Integer> queue = new Deque<>();
        queue.addFirst(s);
        int dist = 0;
        disTo[s] = dist;
        marked[s] = true;

        while (!queue.isEmpty()) {
            int w = queue.removeLast();
            dist = disTo[w] + 1;

            for (int v : graph.adj(w)) {
                if (marked[v])
                    continue;

                marked[v] = true;
                disTo[v] = dist;
                edgeTo[v] = w;
                queue.addFirst(v);
            }
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

    // TODO: add javadoc
    public Integer[] getDistTo() {
        return disTo;
    }
}
