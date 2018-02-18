package io.github.marioluan.datastructures.graph.search;

import com.greghaskins.spectrum.Spectrum;
import io.github.marioluan.datastructures.graph.AdjacencyListGraph;
import io.github.marioluan.datastructures.graph.Graph;
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
                graph = new AdjacencyListGraph(6);
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
        });
    }
}
