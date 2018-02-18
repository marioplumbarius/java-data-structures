package io.github.marioluan.datastructures.graph;

import com.greghaskins.spectrum.Spectrum;
import io.github.marioluan.datastructures.factory.GraphFactory;
import org.junit.Assert;
import org.junit.runner.RunWith;

import static com.greghaskins.spectrum.Spectrum.*;

@RunWith(Spectrum.class)
public class PathsTest {
    private Paths subject;
    private Graph graph;

    {
        describe("Paths", () -> {
            beforeEach(() -> {
                graph = GraphFactory.buildForDFSLecture();
            });

            afterEach(() -> {
                graph = null;
                subject = null;
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
                            int sourceVertex = cluster[s];
                            subject = new Paths(graph, sourceVertex);

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
                        int sourceVertex = cluster[s];
                        subject = new Paths(graph, sourceVertex);

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
    }
}
