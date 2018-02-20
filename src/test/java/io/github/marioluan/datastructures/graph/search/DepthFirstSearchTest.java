package io.github.marioluan.datastructures.graph.search;

import com.greghaskins.spectrum.Spectrum;
import io.github.marioluan.datastructures.graph.Undirected;
import io.github.marioluan.datastructures.graph.Graph;
import org.junit.Assert;
import org.junit.runner.RunWith;

import static com.greghaskins.spectrum.Spectrum.*;

@RunWith(Spectrum.class)
public class DepthFirstSearchTest {
    private Graph graph;
    private DepthFirstSearch subject;

    {
        describe("DepthFirstSearch", () -> {
            beforeEach(() -> {
                graph = new Undirected(13);
                graph.addEdge(0, 1);
                graph.addEdge(0, 2);
                graph.addEdge(0, 5);
                graph.addEdge(0, 6);

                graph.addEdge(6, 4);

                graph.addEdge(4, 3);
                graph.addEdge(4, 5);

                graph.addEdge(3, 5);

                graph.addEdge(7, 8);

                graph.addEdge(9, 10);
                graph.addEdge(9, 11);
                graph.addEdge(9, 12);

                graph.addEdge(11, 12);

                subject = new DepthFirstSearch(graph, 0);
            });

            afterEach(() -> {
                graph = null;
                subject = null;
            });

            describe("#constructor", () -> {
                it("marks visited vertices", () -> {
                    boolean[] expected = new boolean[]{
                        true, true, true, true, true, true, true, false, false, false, false, false, false
                    };

                    for (int i = 0; i < expected.length; i++)
                        Assert.assertEquals(expected[i], subject.getMarked()[i]);
                });

                it("tracks edge to all vertices", () -> {
                    Integer[] expected = new Integer[]{null, 0, 0, 5, 6, 4, 0};

                    for (int i = 0; i < expected.length; i++)
                        Assert.assertEquals(expected[i], subject.getEdgeTo()[i]);
                });
            });
        });
    }
}
