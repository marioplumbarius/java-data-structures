package io.github.marioluan.datastructures.graph;

import io.github.marioluan.datastructures.graph.search.DepthFirstSearch;
import io.github.marioluan.datastructures.stack.LinkedList;

/**
 * Depth-first search client.
 */
public class Paths {
    private final DepthFirstSearch DFS;
    private final int s;

    /**
     * Finds paths in G from source s.
     *
     * @param G a graph
     * @param s a given source vertex
     */
    public Paths(Graph G, int s) {
        DFS = new DepthFirstSearch(G, s);
        this.s = s;
    }

    /**
     * Returns whether there is a path from s to v.
     *
     * @param v destination vertex v
     * @return whether there is a path from s to v
     */
    // Time complexity: O(1)
    public boolean hasPathTo(int v) {
        return DFS.getMarked()[v];
    }

    /**
     * Returns the path from s to v, or null if no such path.
     *
     * @param v destination vertex v
     * @return the path from s to v or null if no such path
     */
    public Iterable<Integer> pathTo(int v) {
        // path not found
        if (!hasPathTo(v)) return null;

        // makes code more readable
        Integer[] edgeTo = DFS.getEdgeTo();
        LinkedList<Integer> path = new LinkedList<>();

        // add destination vertex to stack
        path.push(v);

        // start looking for edges connecting both vertices
        while (edgeTo[v] != null) {
            // add vertex found to stack
            path.push(edgeTo[v]);

            // path not found
            // keep looking
            v = edgeTo[v];
        }

        return path;
    }
};
