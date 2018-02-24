package io.github.marioluan.datastructures.graph.search;

import com.greghaskins.spectrum.Spectrum;
import io.github.marioluan.datastructures.factory.UndirectedGraphFactory;
import io.github.marioluan.datastructures.graph.Graph;
import io.github.marioluan.datastructures.graph.Undirected;
import org.junit.Assert;
import org.junit.runner.RunWith;

import static com.greghaskins.spectrum.Spectrum.*;

@RunWith(Spectrum.class)
public class ConnectedComponentsTest {
    private Undirected graph;
    private ConnectedComponents subject;

    {
        describe("ConnectedComponents", () -> {
            beforeEach(() -> {
                graph = UndirectedGraphFactory.build();
                subject = new ConnectedComponents(graph);
            });

            afterEach(() -> {
                graph = null;
                subject = null;
            });

            describe("#constructor", () -> {
                it("group vertices into components", () -> {
                    Assert.assertEquals(3, subject.getCount());
                });

                it("group vertices from same components together", () -> {
                    int[] expected = new int[]{0, 0, 0, 0, 0, 0, 0, 1, 1, 2, 2, 2, 2};

                    for (int i = 0; i < graph.V(); i++)
                        Assert.assertTrue(expected[i] == subject.id(i));
                });
            });

            describe("#getCount", () -> {
                it("returns the number of components", () -> {
                    Assert.assertEquals(3, subject.getCount());
                });
            });

            describe("#id", () -> {
                it("returns the component id of a given vertex", () -> {
                    int[] expected = new int[]{0, 0, 0, 0, 0, 0, 0, 1, 1, 2, 2, 2, 2};

                    for (int i = 0; i < graph.V(); i++)
                        Assert.assertTrue(expected[i] == subject.id(i));
                });
            });

            describe("#connected", () -> {
                describe("when vertex s is connected to vertex v", () -> {
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

                            // for each source vertex
                            for (int v = 0; v < cluster.length; v++)
                                // for each destination vertex
                                for (int w = 0; w < cluster.length; w++)
                                    if (w != v)
                                        Assert.assertTrue(subject.connected(cluster[w], cluster[v]));

                        }
                    });
                });

                describe("when vertex s is not connected to vertex v", () -> {
                    it("returns false", () -> {
                        // it's made of one vertex from each cluster
                        int[] cluster = new int[]{0, 7, 9};

                        // for each destination vertex
                        for (int v = 0; v < cluster.length; v++)
                            for (int w = 0; w < cluster.length; w++)
                                if (w != v)
                                    Assert.assertFalse(subject.connected(cluster[v], cluster[w]));
                    });
                });
            });
        });
    }
}
