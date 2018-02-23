package io.github.marioluan.datastructures.graph.search;

import io.github.marioluan.datastructures.stack.LinkedList;

/**
 * Decorator for {@link io.github.marioluan.datastructures.graph.Graph} search algorithms.
 */
public abstract class PathDecorator extends MarkedDecorator implements Paths {

    /**
     * Returns all edges found connecting source vertex to all possible destination vertices.
     *
     * @return edges found connecting source vertex to all possible destination vertices
     */
    protected abstract Integer[] getEdgeTo();

    @Override
    public boolean hasPathTo(int v) {
        return getMarked()[v];
    }

    @Override
    public Iterable<Integer> pathTo(int v) {
        // path not found
        if (!hasPathTo(v)) return null;

        // makes code more readable
        LinkedList<Integer> path = new LinkedList<>();

        // add destination vertex to stack
        path.push(v);

        // start looking for edges connecting both vertices
        while (getEdgeTo()[v] != null) {
            // add vertex found to stack
            path.push(getEdgeTo()[v]);

            // path not found
            // keep looking
            v = getEdgeTo()[v];
        }

        return path;
    }

}
