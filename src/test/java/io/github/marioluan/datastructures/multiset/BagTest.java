package io.github.marioluan.datastructures.multiset;

import static com.greghaskins.spectrum.Spectrum.afterEach;
import static com.greghaskins.spectrum.Spectrum.beforeEach;
import static com.greghaskins.spectrum.Spectrum.describe;
import static com.greghaskins.spectrum.Spectrum.it;
import static org.junit.Assert.*;

import java.util.Random;

import io.github.marioluan.datastructures.priority.queue.LinkedListIterator;
import org.hamcrest.CoreMatchers;
import org.junit.runner.RunWith;

import com.greghaskins.spectrum.Spectrum;

@RunWith(Spectrum.class)
public class BagTest {

    private Bag<Integer> subject;

    private Integer item;

    {
        describe("Bag", () -> {
            beforeEach(() -> {
                subject = new Bag<>();
                item = new Random().nextInt();
            });

            afterEach(() -> {
                subject = null;
                item = null;
            });

            describe("add", () -> {
                describe("when item is null", () -> {
                    beforeEach(() -> {
                        item = null;
                    });

                    it("throws IllegalArgumentException", () -> {
                        Exception thrown = null;

                        try {
                            subject.add(item);
                        } catch (IllegalArgumentException e) {
                            thrown = e;
                        }

                        assertNotNull(thrown);
                        assertEquals("item cannot be null", thrown.getMessage());
                    });

                    it("does not increment @size", () -> {
                        try {
                            subject.add(item);
                        } catch (Exception e) {
                        }

                        assertEquals(0, subject.size());
                    });

                    it("does not add item to bag", () -> {
                        try {
                            subject.add(item);
                        } catch (Exception e) {
                        }

                        assertFalse(subject.iterator().hasNext());
                    });
                });
                describe("when item is not null", () -> {
                    beforeEach(() -> {
                        item = new Random().nextInt();
                    });

                    it("does not throw IllegalArgumentException", () -> {
                        Exception thrown = null;

                        try {
                            subject.add(item);
                        } catch (IllegalArgumentException e) {
                            thrown = e;
                        }

                        assertNull(thrown);
                    });

                    it("increments @size by one", () -> {
                        int expectedSize = 0;

                        assertEquals(expectedSize, subject.size());

                        subject.add(item);

                        assertEquals(++expectedSize, subject.size());
                    });

                    it("adds item to bag", () -> {
                        subject.add(item);

                        assertTrue(subject.iterator().hasNext());
                        assertSame(subject.iterator().next(), item);
                    });
                });
            });

            describe("#isEmpty", () -> {
                describe("when @size is zero", () -> {
                    it("returns true", () -> {
                        assertTrue(subject.isEmpty());
                    });
                });

                describe("when @size is not zero", () -> {
                    beforeEach(() -> {
                        item = new Random().nextInt();
                        subject.add(item);
                    });

                    it("returns false", () -> {
                        assertFalse(subject.isEmpty());
                    });
                });
            });

            describe("#size", () -> {
                describe("when it is empty", () -> {
                    it("returns zero", () -> {
                        assertEquals(0, subject.size());
                    });
                });

                describe("when it is not empty", () -> {
                    beforeEach(() -> {
                        item = new Random().nextInt();
                        subject.add(item);
                    });

                    it("returns greater than zero", () -> {
                        assertTrue(subject.size() > 0);
                    });
                });
            });

            describe("#iterator", () -> {
                it("returns an instance of LinkedListIterator", () -> {
                    assertThat(subject.iterator(), CoreMatchers.instanceOf(LinkedListIterator.class));
                });
            });
        });
    }
}

