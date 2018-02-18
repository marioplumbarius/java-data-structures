package io.github.marioluan.datastructures.graph;

import io.github.marioluan.datastructures.graph.search.BreadthFirstSearch;
import io.github.marioluan.datastructures.stack.LinkedList;

/**
 * Implementation of hypothetical BFS client.
 */
public class BFSPaths implements Paths {
    private final BreadthFirstSearch BFS;
    private final int s;

    /**
     * Finds paths in {@link Graph G} from source vertex s.
     *
     * @param G a graph
     * @param s a given source vertex
     */
    public BFSPaths(Graph G, int s) {
        BFS = new BreadthFirstSearch(G, s);
        this.s = s;
    }

    @Override
    public boolean hasPathTo(int v) {
        return BFS.getMarked()[v];
    }

    @Override
    public Iterable<Integer> pathTo(int v) {
        // path not found
        if (!hasPathTo(v)) return null;

        // makes code more readable
        Integer[] edgeTo = BFS.getEdgeTo();
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
