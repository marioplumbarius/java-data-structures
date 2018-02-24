package io.github.marioluan.datastructures.factory;

import io.github.marioluan.datastructures.graph.Digraph;
import io.github.marioluan.datastructures.graph.Graph;

public final class DirectedAcyclicGraphFactory {
    /**
     * Builds a graph with the same vertices and edges from lecture slides. <br>
     * <strong>https://algs4.cs.princeton.edu/lectures/42DirectedGraphs.pdf</strong>
     */
    public static Graph build() {
        Graph graph = new Digraph(7);

        graph.addEdge(0, 2);
        graph.addEdge(0, 5);
        graph.addEdge(0, 1);

        graph.addEdge(1, 4);

        graph.addEdge(5, 2);

        graph.addEdge(3, 6);
        graph.addEdge(3, 2);
        graph.addEdge(3, 5);
        graph.addEdge(3, 4);

        graph.addEdge(6, 0);
        graph.addEdge(6, 4);


        return graph;
    }
}
