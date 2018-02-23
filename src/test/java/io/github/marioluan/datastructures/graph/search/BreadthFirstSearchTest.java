package io.github.marioluan.datastructures.graph.search;

import com.greghaskins.spectrum.Spectrum;
import io.github.marioluan.datastructures.factory.DigraphGraphFactory;
import io.github.marioluan.datastructures.factory.UndirectedGraphFactory;
import io.github.marioluan.datastructures.graph.Graph;
import io.github.marioluan.datastructures.graph.Undirected;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.runner.RunWith;

import static com.greghaskins.spectrum.Spectrum.*;

@RunWith(Spectrum.class)
public class BreadthFirstSearchTest {
    private Graph graph;
    private BreadthFirstSearch subject;

    {
        describe("BreadthFirstSearch", () -> {
            beforeEach(() -> {
                graph = new Undirected(6);
                graph.addEdge(0, 1);
                graph.addEdge(0, 5);
                graph.addEdge(0, 2);

                graph.addEdge(2, 3);
                graph.addEdge(2, 4);

                graph.addEdge(4, 3);
                graph.addEdge(3, 5);

                subject = new BreadthFirstSearch(graph, 0);
            });

            afterEach(() -> {
                graph = null;
                subject = null;
            });

            describe("#constructor", () -> {
                it("marks visited vertices", () -> {
                    for (boolean v : subject.getMarked())
                        Assert.assertTrue(v);
                });

                it("tracks edge to all vertices", () -> {
                    Integer[] expected = new Integer[]{null, 0, 0, 2, 2, 0};

                    for (int i = 0; i < expected.length; i++)
                        Assert.assertEquals(expected[i], subject.getEdgeTo()[i]);
                });

                it("tracks distance to all vertices", () -> {
                    Integer[] expected = new Integer[]{0, 1, 1, 2, 2, 1};

                    for (int i = 0; i < expected.length; i++)
                        Assert.assertEquals(expected[i], subject.getDistTo()[i]);
                });
            });

            describe("#hasPathTo", () -> {
                describe("with graph", () -> {
                    describe("when it is undirected", () -> {
                        beforeEach(() -> {
                            graph = UndirectedGraphFactory.build();
                        });
                        describe("when there is a path from s to v", () -> {
                            it("returns true", () -> {
                                // clusters of [connected] sources vertices
                                int[][] clusters = new int[][]{
                                    new int[]{0, 1, 2, 3, 4, 5, 6},
                                    new int[]{7, 8},
                                    new int[]{9, 10, 11, 12}
                                };

                                // for each cluster
                                for (int c = 0; c < clusters.length; c++) {
                                    // pick a cluster of connected vertices
                                    int[] cluster = clusters[c];

                                    // for each source vertex within the cluster
                                    for (int s = 0; s < cluster.length; s++) {
                                        subject = new BreadthFirstSearch(graph, cluster[s]);

                                        // for each destination vertex
                                        for (int v = 0; v < cluster.length; v++) {
                                            if (cluster[v] == cluster[s])
                                                continue;

                                            Assert.assertTrue(subject.hasPathTo(cluster[v]));
                                            break;
                                        }
                                    }
                                }
                            });
                        });

                        describe("when there isn't a path from s to v", () -> {
                            it("returns false", () -> {
                                // it's made of one vertex from each cluster
                                int[] cluster = new int[]{0, 7, 9};

                                // for each source vertex within the cluster
                                for (int s = 0; s < cluster.length; s++) {
                                    subject = new BreadthFirstSearch(graph, cluster[s]);

                                    // for each destination vertex
                                    for (int v = 0; v < cluster.length; v++) {
                                        if (cluster[v] == cluster[s])
                                            continue;

                                        Assert.assertFalse(subject.hasPathTo(cluster[v]));
                                        break;
                                    }
                                }
                            });
                        });
                    });

                    describe("when it is directed", () -> {
                        beforeEach(() -> {
                            graph = DigraphGraphFactory.build();
                        });
                        describe("when there is a path from s to v", () -> {
                            it("returns true", () -> {
                                // clusters of [connected] sources vertices
                                int[][] clusters = new int[][]{
                                    new int[]{1, 2, 3, 4, 5},
                                    new int[]{},
                                    new int[]{0, 1, 3, 4, 5},
                                    new int[]{0, 1, 2, 4, 5},
                                    new int[]{0, 1, 2, 3, 5},
                                    new int[]{0, 1, 2, 3, 4, 5},
                                    new int[]{0, 1, 2, 3, 4, 5, 6, 8, 9, 10, 11, 12},
                                    new int[]{0, 1, 2, 3, 4, 5, 6, 8, 9, 10, 11, 12},
                                    new int[]{0, 1, 2, 3, 4, 5, 6, 9, 10, 11, 12},
                                    new int[]{0, 1, 2, 3, 4, 5, 10, 11, 12},
                                    new int[]{0, 1, 2, 3, 4, 5, 9, 11, 12},
                                    new int[]{0, 1, 2, 3, 4, 5, 9, 10, 12},
                                    new int[]{0, 1, 2, 3, 4, 5, 9, 10, 11},
                                };

                                // for each cluster
                                for (int c = 0; c < clusters.length; c++) {
                                    // pick a cluster of connected vertices
                                    int[] cluster = clusters[c];

                                    // for each source vertex within the cluster
                                    for (int s = 0; s < cluster.length; s++) {
                                        subject = new BreadthFirstSearch(graph, c);

                                        // for each destination vertex
                                        for (int v = 0; v < cluster.length; v++) {
                                            Assert.assertTrue(subject.hasPathTo(cluster[v]));
                                            break;
                                        }
                                    }
                                }
                            });
                        });

                        describe("when there isn't a path from s to v", () -> {
                            it("returns false", () -> {
                                // clusters of [connected] sources vertices
                                int[][] clusters = new int[][]{
                                    new int[]{6, 7, 8, 9, 10, 11, 12},
                                    new int[]{0, 1, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12},
                                    new int[]{6, 7, 8, 9, 10, 11, 12},
                                    new int[]{6, 7, 8, 9, 10, 11, 12},
                                    new int[]{6, 7, 8, 9, 10, 11, 12},
                                    new int[]{6, 7, 8, 9, 10, 11, 12},
                                    new int[]{7},
                                    new int[]{},
                                    new int[]{7},
                                    new int[]{6, 8},
                                    new int[]{6, 8},
                                    new int[]{6, 8},
                                    new int[]{6, 8},
                                };

                                // for each cluster
                                for (int c = 0; c < clusters.length; c++) {
                                    // pick a cluster of connected vertices
                                    int[] cluster = clusters[c];

                                    // for each source vertex within the cluster
                                    for (int s = 0; s < cluster.length; s++) {
                                        subject = new BreadthFirstSearch(graph, c);

                                        // for each destination vertex
                                        for (int v = 0; v < cluster.length; v++) {
                                            Assert.assertFalse(subject.hasPathTo(cluster[v]));
                                            break;
                                        }
                                    }
                                }
                            });
                        });
                    });
                });
            });
        });
    }
}
