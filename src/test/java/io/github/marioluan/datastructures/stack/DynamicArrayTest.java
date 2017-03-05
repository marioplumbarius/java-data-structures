package io.github.marioluan.datastructures.stack;

import static com.greghaskins.spectrum.Spectrum.afterEach;
import static com.greghaskins.spectrum.Spectrum.beforeEach;
import static com.greghaskins.spectrum.Spectrum.describe;
import static com.greghaskins.spectrum.Spectrum.it;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
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

import java.util.NoSuchElementException;
import java.util.Random;

import org.junit.runner.RunWith;

import com.greghaskins.spectrum.Spectrum;

import io.github.marioluan.datastructures.wrappers.ArrayResizable;
import io.github.marioluan.datastructures.wrappers.Resizable;

@SuppressWarnings("unchecked")
@RunWith(Spectrum.class)
public class DynamicArrayTest {

    private Resizable<Integer[]> rs;
    private Stack<Integer>       subject;

    {
        describe("DynamicArray", () -> {
            beforeEach(() -> {
                this.rs = mock(ArrayResizable.class);
                this.subject = new DynamicArray<>(rs);

                given(this.rs.resize(anyObject(), anyInt(), anyInt()))
                        .willCallRealMethod();
            });

            afterEach(() -> {
                while (!this.subject.isEmpty())
                    this.subject.pop();

                reset(this.rs);
            });

            describe("constructor", () -> {
                it("initializes an empty deque", () -> {
                    assertTrue(this.subject.isEmpty());
                });
            });

            describe("#isEmpty", () -> {
                describe("when it is empty", () -> {
                    it("returns true", () -> {
                        assertTrue(this.subject.isEmpty());
                    });
                });

                describe("when it is not empty", () -> {
                    beforeEach(() -> {
                        this.subject.push(new Random().nextInt());
                    });

                    it("returns false", () -> {
                        assertFalse(this.subject.isEmpty());
                    });
                });
            });

            describe("#size", () -> {
                describe("when it is empty", () -> {
                    it("returns its @size", () -> {
                        assertEquals(0, this.subject.size());
                    });
                });

                describe("when it is not empty", () -> {
                    beforeEach(() -> {
                        this.subject.push(new Random().nextInt());
                    });

                    it("returns its @size", () -> {
                        assertEquals(1, this.subject.size());
                    });
                });
            });

            describe("#push", () -> {
                describe("when item is null", () -> {
                    it("throws illegal argument exception", () -> {
                        IllegalArgumentException throwed = null;

                        try {
                            this.subject.push(null);
                        } catch (IllegalArgumentException e) {
                            throwed = e;
                        }

                        assertNotNull(throwed);
                    });
                });

                describe("when item is not null", () -> {
                    it("does not throw illegal argument exception", () -> {
                        IllegalArgumentException throwed = null;

                        try {
                            this.subject.push(new Random().nextInt());
                        } catch (IllegalArgumentException e) {
                            throwed = e;
                        }

                        assertNull(throwed);
                    });

                    it("increments @size by 1", () -> {
                        int current = this.subject.size();
                        this.subject.push(new Random().nextInt());
                        int expected = current + 1;

                        assertEquals(this.subject.size(), expected);
                    });

                    it("adds it to the top of the stack", () -> {
                        Integer first = 1;
                        Integer second = 2;
                        Integer last = 3;

                        this.subject.push(first);
                        this.subject.push(second);
                        this.subject.push(last);

                        assertEquals(this.subject.peek(), last);
                    });

                    describe("when the stack is full", () -> {

                        it("does not throw exception", () -> {
                            this.subject.push(new Random().nextInt());
                            this.subject.push(new Random().nextInt());

                            int size = this.subject.size();
                            assertEquals(2, size);

                            ArrayIndexOutOfBoundsException throwed = null;

                            try {
                                this.subject.push(new Random().nextInt());
                            } catch (ArrayIndexOutOfBoundsException e) {
                                throwed = e;
                            }

                            assertNull(throwed);
                        });

                        it("doubles its size", () -> {
                            this.subject.push(new Random().nextInt());
                            this.subject.push(new Random().nextInt());
                            this.subject.push(new Random().nextInt());

                            verify(this.rs, times(1)).resize(anyObject(), eq(2),
                                    eq(4));

                            this.subject.push(new Random().nextInt());
                            this.subject.push(new Random().nextInt());

                            verify(this.rs, times(1)).resize(anyObject(), eq(4),
                                    eq(8));
                        });
                    });
                });
            });

            describe("#pop", () -> {
                describe("when it is empty", () -> {
                    it("throws no such element", () -> {
                        NoSuchElementException throwed = null;

                        try {
                            this.subject.pop();
                        } catch (NoSuchElementException e) {
                            throwed = e;
                        }

                        assertNotNull(throwed);
                    });
                });

                describe("when it is not empty", () -> {
                    beforeEach(() -> {
                        this.subject.push(new Random().nextInt());
                    });

                    it("does not throw null pointer", () -> {
                        NoSuchElementException throwed = null;

                        try {
                            this.subject.pop();
                        } catch (NoSuchElementException e) {
                            throwed = e;
                        }

                        assertNull(throwed);
                    });

                    it("decrements @size by 1", () -> {
                        int current = this.subject.size();
                        int expected = current - 1;
                        this.subject.pop();

                        assertEquals(expected, this.subject.size());
                    });

                    it("removes the item from the top of the stack", () -> {
                        Integer first = 1;
                        Integer second = 2;
                        Integer last = 3;

                        this.subject.push(first);
                        this.subject.push(second);
                        this.subject.push(last);

                        this.subject.pop();

                        assertNotEquals(this.subject.peek(), last);
                        assertEquals(this.subject.peek(), second);
                    });

                    it("returns the item from the top of the stack", () -> {
                        Integer first = 1;
                        Integer second = 2;
                        Integer last = 3;

                        this.subject.push(first);
                        this.subject.push(second);
                        this.subject.push(last);

                        assertEquals(this.subject.pop(), last);
                    });

                    describe("when there are 1/4 of queue size items", () -> {
                        it("shrinks its size", () -> {
                            this.subject.push(99999);
                            this.subject.push(99999);

                            this.subject.pop();
                            this.subject.pop();

                            verify(this.rs, times(1)).resize(anyObject(), eq(1),
                                    eq(2));
                        });
                    });

                });
            });

            describe("#peek", () -> {
                describe("when it is empty", () -> {
                    it("throws no such element", () -> {
                        NoSuchElementException throwed = null;

                        try {
                            this.subject.peek();
                        } catch (NoSuchElementException e) {
                            throwed = e;
                        }

                        assertNotNull(throwed);
                    });
                });

                describe("when it is not empty", () -> {
                    beforeEach(() -> {
                        this.subject.push(new Random().nextInt());
                    });

                    it("does not throw no such element", () -> {
                        NoSuchElementException throwed = null;

                        try {
                            this.subject.peek();
                        } catch (NoSuchElementException e) {
                            throwed = e;
                        }

                        assertNull(throwed);
                    });

                    it("does not decrement @size by 1", () -> {
                        int current = this.subject.size();
                        int expected = current;
                        this.subject.peek();

                        assertEquals(expected, this.subject.size());
                    });

                    it("does not remove the item from the top of the stack",
                            () -> {
                                Integer first = 1;
                                Integer second = 2;
                                Integer last = 3;

                                this.subject.push(first);
                                this.subject.push(second);
                                this.subject.push(last);

                                this.subject.peek();

                                assertEquals(this.subject.peek(), last);
                            });

                    it("returns the item from the top of the stack", () -> {
                        Integer first = 1;
                        Integer second = 2;
                        Integer last = 3;

                        this.subject.push(first);
                        this.subject.push(second);
                        this.subject.push(last);

                        assertEquals(this.subject.peek(), last);
                    });

                });
            });

            describe("#iterator", () -> {
                describe("when the stack is empty", () -> {
                    it("returns an empty iterator", () -> {
                        assertFalse(this.subject.iterator().hasNext());
                    });
                });

                describe("when the stack is not empty", () -> {
                    it("does not return an empty iterator", () -> {
                        this.subject.push(new Random().nextInt());

                        assertTrue(this.subject.iterator().hasNext());
                    });

                    it("returns the items in LIFO order", () -> {
                        int n = 5;

                        for (int i = 1; i <= n; i++)
                            this.subject.push(i);

                        for (Integer item : this.subject)
                            assertEquals(n--, (int) item);

                    });

                    it("makes a copy of the items from the stack", () -> {
                        int n = 5;

                        for (int i = 1; i <= n; i++)
                            this.subject.push(i);

                        for (int i = 0; i < 2; i++) {
                            for (Integer item : this.subject)
                                assertEquals(n--, (int) item);

                            n = 5;
                        }
                    });
                });
            });
        });
    }
}
