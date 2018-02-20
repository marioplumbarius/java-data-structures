package io.github.marioluan.datastructures.factory;

import io.github.marioluan.datastructures.graph.Undirected;
import io.github.marioluan.datastructures.graph.Graph;

public final class GraphFactory {
    /**
     * Builds a graph with the same vertices and edges from lecture slides. <br>
     * <strong>https://www.coursera.org/learn/algorithms-part2/lecture/mW9aG/depth-first-search</strong>
     */
    public static Graph buildForDFSLecture() {
        Graph graph = new Undirected(13);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 5);
        graph.addEdge(0, 6);
        graph.addEdge(6, 4);
        graph.addEdge(4, 3);
        graph.addEdge(4, 5);
        graph.addEdge(3, 5);

        // 7 -> 8
        graph.addEdge(7, 8);

        graph.addEdge(9, 10);
        graph.addEdge(9, 11);
        graph.addEdge(9, 12);
        graph.addEdge(11, 12);

        return graph;
    }
}
