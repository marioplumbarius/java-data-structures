package io.github.marioluan.datastructures.graph.sort;

import com.greghaskins.spectrum.Spectrum;

import static com.greghaskins.spectrum.Spectrum.*;

import io.github.marioluan.datastructures.factory.DirectedAcyclicGraphFactory;
import io.github.marioluan.datastructures.graph.Graph;
import org.junit.Assert;
import org.junit.runner.RunWith;

@RunWith(Spectrum.class)
public class TopologicalSortTest {
    private Graph graph;
    private TopologicalSort subject;

    {
        describe("TopologicalSort", () -> {
            beforeEach(() -> {
                graph = DirectedAcyclicGraphFactory.build();
                subject = new TopologicalSort(graph);
            });

            afterEach(() -> {
                graph = null;
                subject = null;
            });

            describe("#reversePost", () -> {
                it("returns vertices in reverse post order", () -> {
                    int[] vertices = new int[]{4, 1, 2, 5, 0, 6, 3};
                    int i = vertices.length - 1;

                    for (int v : subject.reversePost())
                        Assert.assertEquals(vertices[i--], v);
                });
            });
        });
    }
}
