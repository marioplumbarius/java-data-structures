package io.github.marioluan.datastructures;

import static com.greghaskins.spectrum.Spectrum.afterEach;
import static com.greghaskins.spectrum.Spectrum.beforeEach;
import static com.greghaskins.spectrum.Spectrum.describe;
import static com.greghaskins.spectrum.Spectrum.it;
import static com.greghaskins.spectrum.Spectrum.xdescribe;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.NoSuchElementException;
import java.util.Random;

import org.junit.runner.RunWith;

import com.greghaskins.spectrum.Spectrum;

import io.github.marioluan.datastructures.wrappers.Resizable;

@RunWith(Spectrum.class)
public class MaxBinaryHeapTest {

    private Resizable<Integer[]>   rs;
    private MaxBinaryHeap<Integer> subject;

    {
        describe("MinOrderedLinkedListPriorityQueue", () -> {
            beforeEach(() -> {
                this.subject = new MaxBinaryHeap<>(10);
            });

            afterEach(() -> {
                this.subject = null;
            });

            describe("#isEmpty", () -> {
                describe("when it is empty", () -> {
                    beforeEach(() -> {
                        this.subject.insert(new Random().nextInt());
                        this.subject.delMax();
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
                        this.subject.delMax();
                    });

                    it("returns its @size", () -> {
                        assertEquals(this.subject.size(), 0);
                    });
                });

                describe("when it is not empty", () -> {
                    beforeEach(() -> {
                        this.subject.insert(new Random().nextInt());
                    });

                    it("returns its @size", () -> {
                        assertEquals(this.subject.size(), 1);
                    });
                });
            });

            describe("#insert", () -> {
                describe("when key is null", () -> {
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

                describe("when key is not null", () -> {
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

                        assertEquals(this.subject.size(), expected);
                    });

                    it("adds key to the priority queue keeping the natural order or elements",
                            () -> {
                                Integer min = 1;
                                Integer mid = 2;
                                Integer max = 3;

                                this.subject.insert(mid);
                                this.subject.insert(max);
                                this.subject.insert(min);

                                assertEquals(max, this.subject.delMax());
                                assertEquals(mid, this.subject.delMax());
                                assertEquals(min, this.subject.delMax());
                            });

                    // TODO: implement funcionality
                    xdescribe("when queue is full", () -> {

                        it("does not throw exception", () -> {
                            this.subject.insert(new Random().nextInt());
                            this.subject.insert(new Random().nextInt());

                            int size = this.subject.size();
                            assertEquals(2, size);

                            ArrayIndexOutOfBoundsException throwed = null;

                            try {
                                this.subject.insert(new Random().nextInt());
                            } catch (ArrayIndexOutOfBoundsException e) {
                                throwed = e;
                            }

                            assertNull(throwed);
                        });

                        it("doubles its size", () -> {
                            this.subject.insert(new Random().nextInt());
                            this.subject.insert(new Random().nextInt());
                            this.subject.insert(new Random().nextInt());

                            verify(this.rs, times(1)).resize(anyObject(), eq(2),
                                    eq(4));

                            this.subject.insert(new Random().nextInt());
                            this.subject.insert(new Random().nextInt());

                            verify(this.rs, times(1)).resize(anyObject(), eq(4),
                                    eq(8));
                        });
                    });
                });
            });

            describe("#delMax", () -> {
                describe("when it is empty", () -> {
                    it("throws no such element", () -> {
                        NoSuchElementException throwed = null;

                        try {
                            this.subject.delMax();
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
                        this.subject.delMax();

                        assertEquals(this.subject.size(), expected);
                    });

                    it("removes the largest key", () -> {
                        Integer min = 2;
                        Integer max = 3;

                        this.subject.insert(min);
                        this.subject.insert(max);

                        assertEquals(max, this.subject.delMax());
                        assertEquals(min, this.subject.delMax());
                    });

                    it("sets the cursor to the next greater key", () -> {
                        Integer min = 1;
                        Integer mid = 2;
                        Integer max = 3;

                        this.subject.insert(min);
                        this.subject.insert(mid);
                        this.subject.insert(max);

                        this.subject.delMax();

                        assertEquals(this.subject.delMax(), mid);
                    });
                });
            });
        });
    }
}
