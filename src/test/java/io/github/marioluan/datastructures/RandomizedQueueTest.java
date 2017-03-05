package io.github.marioluan.datastructures;

import static com.greghaskins.spectrum.Spectrum.afterEach;
import static com.greghaskins.spectrum.Spectrum.beforeEach;
import static com.greghaskins.spectrum.Spectrum.describe;
import static com.greghaskins.spectrum.Spectrum.it;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

import org.junit.Assert;
import org.junit.runner.RunWith;

import com.greghaskins.spectrum.Spectrum;

import io.github.marioluan.datastructures.wrappers.ArrayResizable;
import io.github.marioluan.datastructures.wrappers.Resizable;

@SuppressWarnings({ "unchecked", "rawtypes" })
@RunWith(Spectrum.class)
public class RandomizedQueueTest {

    private Resizable<Integer[]> rs;

    private RandomizedQueue<Integer> subject;

    {
        describe("RandomizedQueue", () -> {
            beforeEach(() -> {
                this.rs = mock(ArrayResizable.class);
                this.subject = new RandomizedQueue<>(rs);

                given(this.rs.resize(anyObject(), anyInt(), anyInt()))
                        .willCallRealMethod();
            });

            afterEach(() -> {
                while (!this.subject.isEmpty())
                    this.subject.dequeue();

                reset(this.rs);
            });

            describe("constructor", () -> {
                it("initializes an empty randomized queue", () -> {
                    assertTrue(this.subject.isEmpty());
                });
            });

            describe("#isEmpty", () -> {
                describe("when it is empty", () -> {
                    beforeEach(() -> {
                        this.subject.enqueue(new Random().nextInt());
                        this.subject.dequeue();
                    });

                    it("returns true", () -> {
                        assertTrue(this.subject.isEmpty());
                    });
                });

                describe("when it is not empty", () -> {
                    beforeEach(() -> {
                        this.subject.enqueue(new Random().nextInt());
                    });

                    it("returns false", () -> {
                        assertFalse(this.subject.isEmpty());
                    });
                });
            });

            describe("#size", () -> {
                describe("when it is empty", () -> {
                    beforeEach(() -> {
                        this.subject.enqueue(new Random().nextInt());
                        this.subject.dequeue();
                    });

                    it("returns its @size", () -> {
                        assertEquals(0, this.subject.size());
                    });
                });

                describe("when it is not empty", () -> {
                    beforeEach(() -> {
                        this.subject.enqueue(new Random().nextInt());
                    });

                    it("returns its @size", () -> {
                        assertEquals(1, this.subject.size());
                    });
                });
            });

            describe("#enqueue", () -> {
                describe("when item is null", () -> {
                    it("throws null pointer", () -> {
                        NullPointerException throwed = null;

                        try {
                            this.subject.enqueue(null);
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
                            this.subject.enqueue(new Random().nextInt());
                        } catch (NullPointerException e) {
                            throwed = e;
                        }

                        assertNull(throwed);
                    });

                    it("increments size by 1", () -> {
                        int current = this.subject.size();
                        this.subject.enqueue(new Random().nextInt());
                        int expected = current + 1;

                        assertEquals(expected, this.subject.size());
                    });

                    describe("when queue is full", () -> {

                        it("does not throw exception", () -> {
                            this.subject.enqueue(new Random().nextInt());
                            this.subject.enqueue(new Random().nextInt());

                            int size = this.subject.size();
                            assertEquals(2, size);

                            ArrayIndexOutOfBoundsException throwed = null;

                            try {
                                this.subject.enqueue(new Random().nextInt());
                            } catch (ArrayIndexOutOfBoundsException e) {
                                throwed = e;
                            }

                            assertNull(throwed);
                        });

                        it("doubles its size", () -> {
                            this.subject.enqueue(new Random().nextInt());
                            this.subject.enqueue(new Random().nextInt());
                            this.subject.enqueue(new Random().nextInt());

                            verify(this.rs, times(1)).resize(anyObject(), eq(2),
                                    eq(4));

                            this.subject.enqueue(new Random().nextInt());
                            this.subject.enqueue(new Random().nextInt());

                            verify(this.rs, times(1)).resize(anyObject(), eq(4),
                                    eq(8));
                        });
                    });
                });
            });

            describe("#dequeue", () -> {
                describe("when queue is empty", () -> {
                    it("throws NoSuchElementException", () -> {
                        NoSuchElementException throwed = null;

                        try {
                            this.subject.dequeue();
                        } catch (NoSuchElementException e) {
                            throwed = e;
                        }

                        assertNotNull(throwed);
                    });
                });

                describe("when queue is not null", () -> {
                    beforeEach(() -> {
                        this.subject.enqueue(new Random().nextInt());
                    });

                    it("does not throw NoSuchElementException", () -> {
                        NoSuchElementException throwed = null;

                        try {
                            this.subject.dequeue();
                        } catch (NoSuchElementException e) {
                            throwed = e;
                        }

                        assertNull(throwed);
                    });

                    it("decrements size by 1", () -> {
                        int current = this.subject.size();
                        this.subject.dequeue();
                        int expected = current - 1;

                        assertEquals(expected, this.subject.size());
                    });

                    describe("when there are 1/4 of queue size items", () -> {
                        it("shrinks its size", () -> {
                            this.subject.enqueue(99999);
                            this.subject.enqueue(99999);

                            this.subject.dequeue();
                            this.subject.dequeue();

                            verify(this.rs, times(1)).resize(anyObject(), eq(1),
                                    eq(2));
                        });
                    });
                });
            });

            describe("#iterator", () -> {
                describe("when it is nested", () -> {
                    it("does not throw exception", () -> {
                        Integer[] ints = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

                        for (int i = 0; i < ints.length; i++)
                            this.subject.enqueue(ints[i]);

                        for (Iterator it = this.subject.iterator(); it
                                .hasNext();) {
                            assertNotNull(it.next());
                            for (Iterator itt = this.subject.iterator(); itt
                                    .hasNext();) {
                                assertNotNull(itt.next());
                            }
                        }
                    });
                });

                describe("when it is not nested", () -> {
                    it("does not return null items", () -> {
                        Integer[] ints = { 1 };

                        for (int i = 0; i < ints.length; i++)
                            this.subject.enqueue(ints[i]);

                        for (Iterator<Integer> it = this.subject.iterator(); it
                                .hasNext();) {
                            Integer item = it.next();
                            Assert.assertNotNull(item);
                        }
                    });
                });
            });
        });
    }
}
