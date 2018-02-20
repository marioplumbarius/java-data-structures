package io.github.marioluan.datastructures.factory;

import io.github.marioluan.datastructures.graph.Digraph;
import io.github.marioluan.datastructures.graph.Graph;
import io.github.marioluan.datastructures.graph.Undirected;

public final class DigraphGraphFactory {
    /**
     * Builds a graph with the same vertices and edges from lecture slides. <br>
     * <strong>https://algs4.cs.princeton.edu/lectures/42DirectedGraphs.pdf</strong>
     */
    public static Graph build() {
        Graph graph = new Digraph(13);

        graph.addEdge(0, 1);
        graph.addEdge(0, 5);

        graph.addEdge(2, 0);
        graph.addEdge(2, 3);

        graph.addEdge(3, 2);
        graph.addEdge(3, 5);

        graph.addEdge(4, 2);
        graph.addEdge(4, 3);

        graph.addEdge(5, 4);

        graph.addEdge(6, 0);
        graph.addEdge(6, 9);
        graph.addEdge(6, 4);
        graph.addEdge(6, 8);

        graph.addEdge(7, 6);
        graph.addEdge(7, 9);

        graph.addEdge(8, 6);

        graph.addEdge(9, 10);
        graph.addEdge(9, 11);

        graph.addEdge(10, 12);

        graph.addEdge(11, 12);
        graph.addEdge(11, 4);

        graph.addEdge(12, 9);

        return graph;
    }
}
