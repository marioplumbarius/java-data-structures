package io.github.marioluan.datastructures.graph;


/**
 * Common {@link Graph} operations.
 */
public final class GraphProcessor {

    private GraphProcessor() {
    }

    /**
     * Computes the degree of vertex v of a given {@link Graph}.<br>
     * That is: the number of adjacent vertices.
     *
     * @param G graph
     * @param v vertex
     * @return the degree of vertex v
     * @throws ArrayIndexOutOfBoundsException when v is greater or less than graph bounds
     */
    public static int degree(Graph G, int v) throws ArrayIndexOutOfBoundsException {
        int degree = 0;

        for (int w : G.adj(v)) degree++;
        return degree;
    }

    /**
     * Computes maximum degree of a given {@link Graph}.
     *
     * @param G graph
     * @return maximum degree of {@link Graph G}
     */
    public static int maxDegree(Graph G) {
        int max = 0;

        for (int v = 0; v < G.V(); v++) {
            int currentDegree = degree(G, v);
            if (degree(G, v) > max)
                max = currentDegree;
        }
        return max;
    }

    /**
     * Computes average degree of a given {@link Graph}.
     *
     * @param G graph
     * @return average degree of {@link Graph G}
     */
    public static double averageDegree(Graph G) {
        // so we dont try to divide 0 by G.V
        if (G.E() == 0)
            return 0;

        return 2.0 * G.E() / G.V();
    }

    /**
     * Counts self-loops of a given {@link Graph]}.
     *
     * @param G graph
     * @return number of self-loops of {@link Graph G}
     */
    public static int numberOfSelfLoops(Graph G) {
        int count = 0;
        for (int v = 0; v < G.V(); v++)
            for (int w : G.adj(v))
                if (v == w) count++;

        // each edge counted twice
        return count / 2;
    }
}
