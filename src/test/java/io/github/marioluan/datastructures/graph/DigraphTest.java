package io.github.marioluan.datastructures.graph;

import com.greghaskins.spectrum.Spectrum;
import io.github.marioluan.datastructures.factory.DigraphGraphFactory;
import org.junit.Assert;
import org.junit.runner.RunWith;

import java.util.Random;
import java.util.stream.IntStream;

import static com.greghaskins.spectrum.Spectrum.*;

@RunWith(Spectrum.class)
public class DigraphTest {

    private Digraph subject;

    private int V;

    private int v;
    private int w;

    {
        describe("Digraph", () -> {
            beforeEach(() -> {
                V = 2;
                subject = new Digraph(V);
                w = new Random().nextInt(V);
                v = new Random().nextInt(V);
            });

            afterEach(() -> {
                V = 0;
                subject = null;
                w = 0;
                v = 0;
            });

            describe("#constructor", () -> {
                beforeEach(() -> {
                    V = 2;
                });

                it("creates an empty graph", () -> {
                    subject = new Digraph(V);

                    Assert.assertEquals(V, subject.V());
                    Assert.assertEquals(0, subject.E());
                });
            });

            describe("#addEdge", () -> {
                beforeEach(() -> {
                    w = 0;
                    v = 1;

                    subject.addEdge(v, w);
                });

                it("adds an edge from v to w", () -> {
                    Assert.assertSame(w, subject.adj(v).iterator().next());
                });

                it("does not add an edge from w to v", () -> {
                    Assert.assertFalse(subject.adj(w).iterator().hasNext());
                });

                it("increments the number of edges", () -> {
                    Assert.assertEquals(1, subject.E());
                });
            });

            describe("#adj", () -> {
                beforeEach(() -> {
                    w = new Random().nextInt(V);
                    v = new Random().nextInt(V);

                    subject.addEdge(v, w);
                });

                it("returns the vertices adjacent to v", () -> {
                    Assert.assertSame(w, subject.adj(v).iterator().next());
                });
            });

            describe("#V", () -> {
                beforeEach(() -> {
                    V = 2;
                    subject = new Digraph(V);
                });

                it("returns the number of vertices", () -> {
                    Assert.assertEquals(V, subject.V());
                });
            });

            describe("#E", () -> {
                beforeEach(() -> {
                    w = new Random().nextInt(V);
                    v = new Random().nextInt(V);

                    subject.addEdge(v, w);
                });

                it("returns the number of edges", () -> {
                    Assert.assertEquals(1, subject.E());
                });
            });

            describe("#reverse", () -> {
                describe("when it's empty", () -> {
                    it("does not set any edges", () -> {
                        Digraph reversed = (Digraph) subject.reverse();
                        Assert.assertEquals(0, reversed.E());
                    });
                });

                describe("when it isn't empty", () -> {
                    beforeEach(() -> {
                        subject = DigraphGraphFactory.build();
                    });

                    it("set edges", () -> {
                        Digraph reversed = (Digraph) subject.reverse();
                        Assert.assertEquals(subject.E(), reversed.E());
                    });

                    it("reverses it", () -> {
                        // reversed order of the directed graph
                        int[][] expected = new int[][]{
                            new int[]{2, 6},
                            new int[]{0},
                            new int[]{3, 4},
                            new int[]{2, 4},
                            new int[]{5, 6, 11},
                            new int[]{0, 3},
                            new int[]{7, 8},
                            new int[]{},
                            new int[]{6},
                            new int[]{6, 7, 12},
                            new int[]{9},
                            new int[]{9},
                            new int[]{10, 11},
                        };

                        Digraph reversed = (Digraph) subject.reverse();

                        // for each source vertex
                        for (int i = 0; i < expected.length; i++)
                            // for each adjacent vertex
                            for (int s : reversed.adj(i))
                                // does not rely in adj ordering, which can be either a, b or b, a
                                Assert.assertTrue(IntStream.of(expected[i]).anyMatch(x -> x == s));
                    });
                });
            });

            xdescribe("#toString", () -> {
                beforeEach(() -> {
                    w = new Random().nextInt(V);
                    v = new Random().nextInt(V);

                    subject.addEdge(v, w);
                });

                it("returns its string representation", () -> {
                    Assert.assertEquals("", subject.toString());
                });
            });
        });
    }
}


