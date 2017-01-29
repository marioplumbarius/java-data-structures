package io.github.marioluan.datastructures;

import java.util.NoSuchElementException;
import java.util.Random;

import static com.greghaskins.spectrum.Spectrum.describe;
import static com.greghaskins.spectrum.Spectrum.beforeEach;
import static com.greghaskins.spectrum.Spectrum.afterEach;
import static com.greghaskins.spectrum.Spectrum.it;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.runner.RunWith;

import com.greghaskins.spectrum.Spectrum;

@RunWith(Spectrum.class)
public class MinUnorderedLinkedListPriorityQueueTest {

    private MinUnorderedLinkedListPriorityQueue<Integer> subject;

    {
        describe("MinUnorderedLinkedListPriorityQueue", () -> {
            beforeEach(() -> {
                this.subject = new MinUnorderedLinkedListPriorityQueue<>();
            });

            afterEach(() -> {
                this.subject = null;
            });

            describe("constructor", () -> {
                it("initializes an empty deque", () -> {
                    assertTrue(this.subject.isEmpty());
                });
            });

            describe("#isEmpty", () -> {
                describe("when it is empty", () -> {
                    beforeEach(() -> {
                        this.subject.insert(new Random().nextInt());
                        this.subject.removeMin();
                    });

                    it("returns true", () -> {
                        assertTrue(this.subject.isEmpty());
                    });
                });

                describe("when it is not empty", () -> {
                    beforeEach(() -> {
                        this.subject.insert(new Random().nextInt());
                    });

                    it("returns false", () -> {
                        assertFalse(this.subject.isEmpty());
                    });
                });
            });

            describe("#size", () -> {
                describe("when it is empty", () -> {
                    beforeEach(() -> {
                        this.subject.insert(new Random().nextInt());
                        this.subject.removeMin();
                    });

                    it("returns its @size", () -> {
                        assertEquals(0, this.subject.size());
                    });
                });

                describe("when it is not empty", () -> {
                    beforeEach(() -> {
                        this.subject.insert(new Random().nextInt());
                    });

                    it("returns its @size", () -> {
                        assertEquals(1, this.subject.size());
                    });
                });
            });

            describe("#insert", () -> {
                describe("when item is null", () -> {
                    it("throws null pointer", () -> {
                        NullPointerException throwed = null;

                        try {
                            this.subject.insert(null);
                        } catch (NullPointerException e) {
                            throwed = e;
                        }

                        assertNotNull(throwed);
                    });
                });

                describe("when item is not null", () -> {
                    it("does not throw null pointer", () -> {
                        NullPointerException throwed = null;

                        try {
                            this.subject.insert(new Random().nextInt());
                        } catch (NullPointerException e) {
                            throwed = e;
                        }

                        assertNull(throwed);
                    });

                    it("increments size by 1", () -> {
                        int current = this.subject.size();
                        this.subject.insert(new Random().nextInt());
                        int expected = current + 1;

                        assertEquals(expected, this.subject.size());
                    });

                    it("adds item to the top of the priority queue", () -> {
                        Integer tail = 1;
                        Integer mid = 2;
                        Integer head = 3;

                        this.subject.insert(tail);
                        this.subject.insert(mid);
                        this.subject.insert(head);
                        
                        this.subject.removeMin();
                        this.subject.removeMin();

                        assertEquals(head, this.subject.removeMin());
                    });
                });
            });

            describe("#removeMin", () -> {
                describe("when it is empty", () -> {
                    it("throws no such element", () -> {
                        NoSuchElementException throwed = null;

                        try {
                            this.subject.removeMin();
                        } catch (NoSuchElementException e) {
                            throwed = e;
                        }

                        assertNotNull(throwed);
                    });
                });

                describe("when it is not empty", () -> {
                    it("does not throw null pointer", () -> {
                        NullPointerException throwed = null;

                        try {
                            this.subject.insert(new Random().nextInt());
                        } catch (NullPointerException e) {
                            throwed = e;
                        }

                        assertNull(throwed);
                    });

                    it("decrements size by 1", () -> {
                        this.subject.insert(new Random().nextInt());
                        int current = this.subject.size();
                        int expected = current - 1;
                        this.subject.removeMin();

                        assertEquals(this.subject.size(), expected);
                    });

                    it("removes the smallest item", () -> {
                        Integer min = 1;
                        Integer mid = 2;
                        Integer max = 3;

                        this.subject.insert(min);
                        this.subject.insert(mid);
                        this.subject.insert(max);

                        assertEquals(min, this.subject.removeMin());
                    });

                    it("sets the min cursor to next node", () -> {
                        Integer min = 1;
                        Integer mid = 2;
                        Integer max = 3;

                        this.subject.insert(min);
                        this.subject.insert(mid);
                        this.subject.insert(max);

                        this.subject.removeMin();

                        assertEquals(mid, this.subject.removeMin());
                    });
                });
            });
        });
    }
}
