package io.github.marioluan.datastructures.graph.search;

import io.github.marioluan.datastructures.graph.Graph;
import io.github.marioluan.datastructures.queue.Deque;

/**
 * Bread-first search implementation. <br>
 * Put unvisited vertices on a queue. <br>
 * Goal: Find path from s to v that uses fewest number of edges.
 * Category: single-source reachability.
 */
public class BreadthFirstSearch extends PathDecorator {
    private final Graph graph;
    private final int s;
    private boolean[] marked;
    private Integer[] edgeTo;
    private Integer[] disTo;

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

        search();
    }

    // Time complexity: O(V + E)
    private void search() {
        Deque<Integer> queue = new Deque<>();
        queue.addFirst(s);
        int dist = 0;
        disTo[s] = dist;
        marked[s] = true;

        while (!queue.isEmpty()) {
            int w = queue.removeLast();
            // child vertices has distance from current
            // vertex plus one
            dist = disTo[w] + 1;

            // for each connected vertex
            for (int v : graph.adj(w)) {
                // already marked
                if (marked[v])
                    continue;

                // track
                marked[v] = true;
                disTo[v] = dist;
                edgeTo[v] = w;

                // enqueue it
                queue.addFirst(v);
            }
        }
    }

    /**
     * Returns the distance from all source to all destination vertices.
     *
     * @return the distance from all source to all destination vertices
     */
    protected Integer[] getDistTo() {
        return disTo;
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
