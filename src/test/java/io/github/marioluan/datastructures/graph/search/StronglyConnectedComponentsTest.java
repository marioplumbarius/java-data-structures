package io.github.marioluan.datastructures.graph.search;

import com.greghaskins.spectrum.Spectrum;
import io.github.marioluan.datastructures.factory.DigraphGraphFactory;
import io.github.marioluan.datastructures.factory.UndirectedGraphFactory;
import io.github.marioluan.datastructures.graph.Digraph;
import io.github.marioluan.datastructures.graph.Graph;
import io.github.marioluan.datastructures.graph.Undirected;
import org.junit.Assert;
import org.junit.runner.RunWith;

import static com.greghaskins.spectrum.Spectrum.*;

@RunWith(Spectrum.class)
public class StronglyConnectedComponentsTest {
    private Digraph graph;
    private StronglyConnectedComponents subject;

    {
        describe("StronglyConnectedComponents", () -> {
            beforeEach(() -> {
                graph = DigraphGraphFactory.build();
                subject = new StronglyConnectedComponents(graph);
            });

            afterEach(() -> {
                graph = null;
                subject = null;
            });

            describe("#getCount", () -> {
                it("returns the number of components", () -> {
                    Assert.assertEquals(5, subject.getCount());
                });
            });

            describe("#id", () -> {
                it("returns the component id of a given vertex", () -> {
                    int[] expected = new int[]{1, 0, 1, 1, 1, 1, 3, 4, 3, 2, 2, 2, 2};

                    for (int i = 0; i < graph.V(); i++)
                        Assert.assertEquals(expected[i], subject.id(i));
                });
            });

            describe("#connected", () -> {
                describe("when vertex s is strongly-connected to vertex v", () -> {
                    it("returns true", () -> {
                        // clusters of [connected] sources vertices
                        int[][] clusters = new int[][]{
                            new int[]{2, 3, 4, 5},
                            new int[]{},
                            new int[]{0, 3, 4, 5},
                            new int[]{0, 2, 4, 5},
                            new int[]{0, 2, 3, 5},
                            new int[]{0, 2, 3, 4},
                            new int[]{8},
                            new int[]{},
                            new int[]{6},
                            new int[]{10, 11, 12},
                            new int[]{9, 11, 12},
                            new int[]{9, 10, 12},
                            new int[]{9, 10, 11},
                        };

                        // for each cluster
                        for (int c = 0; c < clusters.length; c++) {
                            // pick a cluster of connected vertices
                            int[] cluster = clusters[c];

                            // for each source vertex
                            for (int v = 0; v < cluster.length; v++)
                                // for each destination vertex
                                for (int w = 0; w < cluster.length; w++)
                                    if (w != v)
                                        Assert.assertTrue(subject.stronglyConnected(cluster[w], cluster[v]));

                        }
                    });
                });

                describe("when vertex s is not connected to vertex v", () -> {
                    it("returns false", () -> {
                        // it's made of one vertex from each cluster
                        int[] cluster = new int[]{1, 0, 6, 7, 9};

                        // for each destination vertex
                        for (int v = 0; v < cluster.length; v++)
                            for (int w = 0; w < cluster.length; w++)
                                if (w != v)
                                    Assert.assertFalse(subject.stronglyConnected(cluster[v], cluster[w]));
                    });
                });
            });
        });
    }
}
