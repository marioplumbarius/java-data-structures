package io.github.marioluan.datastructures.graph;

/**
 * Generic representation of a {@link Graph} client.
 */
public interface Paths {

    /**
     * Returns whether there is a path from s to v.
     *
     * @param v destination vertex v
     * @return whether there is a path from s to v
     */
    // Time complexity: O(1)
    boolean hasPathTo(int v);

    /**
     * Returns the path from s to v, or null if no such path.
     *
     * @param v destination vertex v
     * @return the path from s to v or null if no such path
     */
    Iterable<Integer> pathTo(int v);
};
