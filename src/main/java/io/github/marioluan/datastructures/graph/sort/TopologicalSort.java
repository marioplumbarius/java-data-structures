package io.github.marioluan.datastructures.graph.sort;

import io.github.marioluan.datastructures.graph.Graph;
import io.github.marioluan.datastructures.stack.LinkedList;

/**
 * Implementation of Topological Sort algorithm using {@link io.github.marioluan.datastructures.graph.search.DepthFirstSearch}. <br>
 * Constraint: graph MUST be a DAG (Directed Acyclic Graph).
 */
public class TopologicalSort {
    private Graph graph;
    private LinkedList<Integer> reversePost;
    private boolean[] marked;

    /**
     * Performs a DFS and sort graph's vertices in topological order.
     *
     * @param graph the graph to be sorted
     */
    public TopologicalSort(Graph graph) {
        // TODO: isGraphAcyclic (it cannot have cycles)
        this.graph = graph;
        reversePost = new LinkedList<>();
        marked = new boolean[graph.V()];

        search();
    }

    /**
     * Performs a DFS from each vertex in the graph.
     */
    private void search() {
        for (int s = 0; s < graph.V(); s++)
            // do not traverse the same vertex twice
            if (!marked[s])
                search(s);
    }

    /**
     * Performs a DFS from vertex s to its adjacent vertices.
     *
     * @param s source vertex
     */
    private void search(int s) {
        // tell the world, we already visited it
        marked[s] = true;

        // go deep into its adjacent vertices
        for (int v : graph.adj(s))
            // repeat the same process for its adjacent vertex
            // only if we have not done it before
            if (!marked[v])
                search(v);

        // when we reached the last adjacent vertex of s
        reversePost.push(s);
    }

    /**
     * Returns the vertices in reverse postorder.
     *
     * @return the vertices in reverse postorder
     */
    public Iterable<Integer> reversePost() {
        return reversePost;
    }
}
