package io.github.marioluan.datastructures.stack;

import java.util.NoSuchElementException;
import java.util.Random;

import static com.greghaskins.spectrum.Spectrum.describe;
import static com.greghaskins.spectrum.Spectrum.beforeEach;
import static com.greghaskins.spectrum.Spectrum.afterEach;
import static com.greghaskins.spectrum.Spectrum.it;
import static com.greghaskins.spectrum.Spectrum.xdescribe;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.runner.RunWith;

import com.greghaskins.spectrum.Spectrum;

@RunWith(Spectrum.class)
public class LinkedListTest {

    private Stack<Integer> subject;

    {
        describe("LinkedListTest", () -> {
            beforeEach(() -> {
                this.subject = new LinkedList<>();
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

            xdescribe("#iterator", () -> {

            });
        });
    }
}
