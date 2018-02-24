package io.github.marioluan.datastructures.graph;

import com.greghaskins.spectrum.Spectrum;
import io.github.marioluan.datastructures.factory.DigraphGraphFactory;
import io.github.marioluan.datastructures.factory.UndirectedGraphFactory;
import org.junit.Assert;
import org.junit.runner.RunWith;

import java.util.Random;
import java.util.stream.IntStream;

import static com.greghaskins.spectrum.Spectrum.*;

@RunWith(Spectrum.class)
public class UndirectedTest {

    private Undirected subject;

    private int V;

    private int v;
    private int w;

    {
        describe("Undirected", () -> {
            beforeEach(() -> {
                V = 2;
                subject = new Undirected(V);
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
                    subject = new Undirected(V);

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

                it("adds an edge from w to v", () -> {
                    Assert.assertSame(v, subject.adj(w).iterator().next());
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
                    Assert.assertSame(v, subject.adj(w).iterator().next());
                    Assert.assertSame(w, subject.adj(v).iterator().next());
                });
            });

            describe("#V", () -> {
                beforeEach(() -> {
                    V = 2;
                    subject = new Undirected(V);
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
                it("returns itself", () -> {
                    subject = UndirectedGraphFactory.build();
                    Undirected reversed = (Undirected) subject.reverse();
                    Assert.assertSame(reversed, subject);
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


