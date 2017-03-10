package io.github.marioluan.datastructures.queue;

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
public class DequeTest {

    private Deque<Integer> subject;

    {
        describe("Deque", () -> {
            beforeEach(() -> {
                this.subject = new Deque<>();
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
                        this.subject.addFirst(new Random().nextInt());
                        this.subject.removeLast();
                    });

                    it("returns true", () -> {
                        assertTrue(this.subject.isEmpty());
                    });
                });

                describe("when it is not empty", () -> {
                    beforeEach(() -> {
                        this.subject.addFirst(new Random().nextInt());
                    });

                    it("returns false", () -> {
                        assertFalse(this.subject.isEmpty());
                    });
                });
            });

            describe("#size", () -> {
                describe("when it is empty", () -> {
                    beforeEach(() -> {
                        this.subject.addFirst(new Random().nextInt());
                        this.subject.removeLast();
                    });

                    it("returns its @size", () -> {
                        assertEquals(this.subject.size(), 0);
                    });
                });

                describe("when it is not empty", () -> {
                    beforeEach(() -> {
                        this.subject.addFirst(new Random().nextInt());
                    });

                    it("returns its @size", () -> {
                        assertEquals(this.subject.size(), 1);
                    });
                });
            });

            describe("#addFirst", () -> {
                describe("when item is null", () -> {
                    it("throws null pointer", () -> {
                        NullPointerException throwed = null;

                        try {
                            this.subject.addFirst(null);
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
                            this.subject.addFirst(new Random().nextInt());
                        } catch (NullPointerException e) {
                            throwed = e;
                        }

                        assertNull(throwed);
                    });

                    it("increments size by 1", () -> {
                        int current = this.subject.size();
                        this.subject.addFirst(new Random().nextInt());
                        int expected = current + 1;

                        assertEquals(this.subject.size(), expected);
                    });

                    it("adds item to the front", () -> {
                        Integer tail = 1;
                        Integer mid = 2;
                        Integer head = 3;

                        this.subject.addFirst(tail);
                        this.subject.addFirst(mid);
                        this.subject.addFirst(head);

                        assertEquals(this.subject.removeFirst(), head);
                    });

                    describe("when it is empty", () -> {
                        it("adds item to both front end back", () -> {
                            Integer headAndTail = new Random().nextInt();

                            this.subject.addFirst(headAndTail);

                            assertEquals(this.subject.removeFirst(),
                                    headAndTail);

                            this.subject.addFirst(headAndTail);

                            assertEquals(this.subject.removeLast(),
                                    headAndTail);
                        });
                    });
                });
            });

            describe("#addLast", () -> {
                describe("when item is null", () -> {
                    it("throws null pointer", () -> {
                        NullPointerException throwed = null;

                        try {
                            this.subject.addLast(null);
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
                            this.subject.addLast(new Random().nextInt());
                        } catch (NullPointerException e) {
                            throwed = e;
                        }

                        assertNull(throwed);
                    });

                    it("increments size by 1", () -> {
                        int current = this.subject.size();
                        this.subject.addLast(new Random().nextInt());
                        int expected = current + 1;

                        assertEquals(this.subject.size(), expected);
                    });

                    it("adds item to the back", () -> {
                        Integer tail = 1;
                        Integer mid = 2;
                        Integer head = 3;

                        this.subject.addFirst(tail);
                        this.subject.addFirst(mid);
                        this.subject.addFirst(head);

                        assertEquals(this.subject.removeLast(), tail);
                    });

                    describe("when it is empty", () -> {
                        it("adds item to both front end back", () -> {
                            Integer headAndTail = new Random().nextInt();

                            this.subject.addLast(headAndTail);

                            assertEquals(this.subject.removeFirst(),
                                    headAndTail);

                            this.subject.addLast(headAndTail);

                            assertEquals(this.subject.removeLast(),
                                    headAndTail);
                        });
                    });
                });
            });

            describe("#removeFirst", () -> {
                describe("when it is empty", () -> {
                    it("throws no such element", () -> {
                        NoSuchElementException throwed = null;

                        try {
                            this.subject.removeFirst();
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
                            this.subject.addLast(new Random().nextInt());
                        } catch (NullPointerException e) {
                            throwed = e;
                        }

                        assertNull(throwed);
                    });

                    it("decrements size by 1", () -> {
                        this.subject.addLast(new Random().nextInt());
                        int current = this.subject.size();
                        int expected = current - 1;
                        this.subject.removeFirst();

                        assertEquals(this.subject.size(), expected);
                    });

                    it("removes item from the front", () -> {
                        Integer tail = 1;
                        Integer mid = 2;
                        Integer head = 3;

                        this.subject.addFirst(tail);
                        this.subject.addFirst(mid);
                        this.subject.addFirst(head);

                        assertEquals(this.subject.removeFirst(), head);
                    });

                    it("sets the head cursor to next node", () -> {
                        Integer tail = 1;
                        Integer mid = 2;
                        Integer head = 3;

                        this.subject.addFirst(tail);
                        this.subject.addFirst(mid);
                        this.subject.addFirst(head);

                        this.subject.removeFirst();

                        assertEquals(this.subject.removeFirst(), mid);
                    });
                });
            });

            describe("#removeLast", () -> {
                describe("when it is empty", () -> {
                    it("throws no such element", () -> {
                        NoSuchElementException throwed = null;

                        try {
                            this.subject.removeLast();
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
                            this.subject.addLast(new Random().nextInt());
                        } catch (NullPointerException e) {
                            throwed = e;
                        }

                        assertNull(throwed);
                    });

                    it("decrements size by 1", () -> {
                        this.subject.addLast(new Random().nextInt());
                        int current = this.subject.size();
                        int expected = current - 1;
                        this.subject.removeLast();

                        assertEquals(this.subject.size(), expected);
                    });

                    it("removes item from the back", () -> {
                        Integer tail = 1;
                        Integer mid = 2;
                        Integer head = 3;

                        this.subject.addFirst(tail);
                        this.subject.addFirst(mid);
                        this.subject.addFirst(head);

                        assertEquals(this.subject.removeLast(), tail);
                    });

                    it("sets the tail cursor to prev node", () -> {
                        Integer tail = 1;
                        Integer mid = 2;
                        Integer head = 3;

                        this.subject.addFirst(tail);
                        this.subject.addFirst(mid);
                        this.subject.addFirst(head);

                        this.subject.removeLast();

                        assertEquals(this.subject.removeLast(), mid);
                    });
                });
            });
        });
    }
}
