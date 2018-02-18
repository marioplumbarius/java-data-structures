package io.github.marioluan.datastructures.graph;

import io.github.marioluan.datastructures.multiset.Bag;

import java.util.stream.IntStream;

/**
 * Adjacency-list {@link Graph graph} representation.<br>
 * Maintain vertex-indexed array of lists.<br>
 * <b>Space complexity: E + V</b>
 */
public class AdjacencyListGraph implements Graph {
    private final int V;
    private int E;
    private Bag<Integer>[] adj;

    /**
     * Creates an empty {@link AdjacencyListGraph}.<br>
     * time complexity: O(V)
     *
     * @param V number of vertices
     */
    public AdjacencyListGraph(int V) {
        this.V = V;
        adj = (Bag<Integer>[]) new Bag[V];
        IntStream.range(0, V).forEach(v -> adj[v] = new Bag<>());
        E = 0;
    }

    // time complexity: O(1)
    @Override
    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    // time complexity: degree(v)
    @Override
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    // time complexity: O(1)
    @Override
    public int V() {
        return V;
    }

    // time complexity: O(1)
    @Override
    public int E() {
        return E;
    }

    // TODO: implement me!
    @Override
    public String toString() {
        return null;
    }
}
