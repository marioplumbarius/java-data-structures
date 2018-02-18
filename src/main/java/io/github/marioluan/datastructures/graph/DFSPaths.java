package io.github.marioluan.datastructures.graph;

import io.github.marioluan.datastructures.graph.search.DepthFirstSearch;
import io.github.marioluan.datastructures.stack.LinkedList;

/**
 * Implementation of hypothetical DFS client.
 */
public class DFSPaths implements Paths {
    private final DepthFirstSearch DFS;
    private final int s;

    /**
     * Finds paths in {@link Graph G} from source vertex s.
     *
     * @param G a graph
     * @param s a given source vertex
     */
    public DFSPaths(Graph G, int s) {
        DFS = new DepthFirstSearch(G, s);
        this.s = s;
    }

    @Override
    public boolean hasPathTo(int v) {
        return DFS.getMarked()[v];
    }

    @Override
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
