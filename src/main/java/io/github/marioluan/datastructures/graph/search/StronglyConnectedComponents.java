package io.github.marioluan.datastructures.graph.search;

import io.github.marioluan.datastructures.graph.Digraph;
import io.github.marioluan.datastructures.graph.sort.TopologicalSort;

/**
 * Implementation of a Strongly-Connected Components data structure using Kosaraju-Sharir algorithm and {@link DepthFirstSearch} (twice). <br>
 * Goal: given a {@link Digraph directed graph}, finds whether a vertex s is strongly-connected to vertex v.
 * Applications:
 * - ecological food webs
 * -- vertex: species
 * -- edge: from producer to consumer
 * -- strong component: subset of species with common energy flow
 * - software modules
 * -- vertex: software module
 * -- edge: from module to dependency
 * -- strong component: subset of mutually interacting modules
 * -- approaches:
 * --- 1. package strong components together
 * --- 2. use to improve design
 */
public class StronglyConnectedComponents {
    private final Digraph graph;
    private int[] id;
    private int count;
    private boolean[] marked;
    private Iterable<Integer> reversePost;

    /**
     * Builds a cluster of strongly-connected components for every source vertex in the {@link Digraph graph}. <br>
     * Goal: given a {@link Digraph directed graph}, finds whether vertex s is strongly-connected to vertex v in O(1).
     * Algorithm:
     * - 1. reverse G®
     * - 2. run DFS on G® to compute reverse postorder.
     * - 3. run DFS on G, considering vertices in order given by first DFS.
     *
     * @param graph a given graph
     */
    public StronglyConnectedComponents(Digraph graph) {
        this.graph = graph;
        marked = new boolean[graph.V()];
        id = new int[graph.V()];

        // 1
        Digraph reversed = (Digraph) graph.reverse();

        // 2
        reversePost = new TopologicalSort(reversed).reversePost();

        // 3
        search();
    }

    /**
     * Performs a DFS for each [unmarked] vertex in the graph.
     */
    private void search() {
        for (int s : reversePost)
            if (!marked[s]) {
                search(s);
                count++;
            }
    }

    /**
     * Performs a DFS from source vertex s.
     *
     * @param s the source vertex
     */
    // Time complexity: O(V+E)
    private void search(int s) {
        marked[s] = true;
        id[s] = count;

        for (int w : graph.adj(s))
            if (!marked[w])
                search(w);
    }

    /**
     * Returns the number of components.
     *
     * @return the number of components
     */
    public int getCount() {
        return count;
    }

    /**
     * Returns the component's id of the vertex v.
     *
     * @param v a given vertex
     * @return the component's id of the vertex v.
     */
    public int id(int v) {
        return id[v];
    }

    /**
     * Returns whether vertices s and v are strongly-connected.
     *
     * @param s source vertex
     * @param v destination vertex
     * @return whether vertices s and v are strongly-connected
     */
    // Time complexity: O(1)
    public boolean stronglyConnected(int s, int v) {
        return id[s] == id[v];
    }
}
