package io.github.marioluan.datastructures.graph.search;

import com.greghaskins.spectrum.Spectrum;
import io.github.marioluan.datastructures.factory.UndirectedGraphFactory;
import io.github.marioluan.datastructures.graph.Graph;
import org.junit.Assert;
import org.junit.runner.RunWith;

import static com.greghaskins.spectrum.Spectrum.*;

@RunWith(Spectrum.class)
public class ConnectedComponentsTest {
    private Graph graph;
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
                it("marks visited vertices", () -> {
                    for (boolean marked : subject.getMarked())
                        Assert.assertTrue(marked);
                });

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
        });
    }
}
