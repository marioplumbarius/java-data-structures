package io.github.marioluan.datastructures.symboltable;

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

import java.util.Random;

import org.junit.runner.RunWith;

import com.greghaskins.spectrum.Spectrum;

import edu.princeton.cs.algs4.BST;

@RunWith(Spectrum.class)
public class BinarySearchTreeTest {

    private BinarySearchTree<String, Integer> subject;
    private BST<String, Integer>              bst;
    private static final Random               RANDOM = new Random();
    private String                            key;
    private Integer                           value;

    {
        describe("BinarySearchTree", () -> {
            beforeEach(() -> {
                this.bst = new BST<>();
                this.subject = new BinarySearchTree<>();
                this.key = String.valueOf(RANDOM.nextInt(26 + 'a'));
                this.value = RANDOM.nextInt();
            });

            afterEach(() -> {
                this.bst = null;
                this.subject = null;
                this.key = null;
                this.value = null;
            });

            describe("constructor", () -> {
                it("initializes an empty symbol table", () -> {
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
                        this.subject.put(key, value);
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
                    describe("when there is a single node", () -> {
                        beforeEach(() -> {
                            this.subject.put(key, value);
                        });

                        it("returns its @size", () -> {
                            assertEquals(1, this.subject.size());
                        });
                    });

                    describe("when there are multiple nodes", () -> {
                        it("returns its @size", () -> {
                            this.subject.put("S", value);
                            assertEquals(1, this.subject.size());

                            this.subject.put("X", value);
                            assertEquals(2, this.subject.size());

                            this.subject.put("E", value);
                            assertEquals(3, this.subject.size());

                            this.subject.put("D", value);
                            assertEquals(4, this.subject.size());
                        });
                    });
                });
            });

            describe("#put", () -> {
                describe("when key is null", () -> {
                    it("throws null pointer", () -> {
                        NullPointerException throwed = null;

                        try {
                            this.subject.put(null, value);
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
                            this.subject.put(key, value);
                        } catch (NullPointerException e) {
                            throwed = e;
                        }

                        assertNull(throwed);
                    });

                    describe("when key already exists", () -> {
                        describe("when value is null", () -> {
                            describe("when it does not have subtrees", () -> {
                                beforeEach(() -> {
                                    this.subject.put("S", value);
                                    this.subject.put("X", value);
                                    this.subject.put("E", value);
                                    this.subject.put("R", value);

                                    this.subject.put("X", null);
                                });

                                it("removes the key from table", () -> {
                                    assertFalse(this.subject.contains("X"));
                                });

                                it("decrements size by 1", () -> {
                                    assertEquals(3, this.subject.size());
                                });
                            });

                            describe("when it has a left subtree", () -> {
                                beforeEach(() -> {
                                    this.subject.put("S", value);
                                    this.subject.put("X", value);
                                    this.subject.put("E", value);
                                    this.subject.put("R", value);
                                    this.subject.put("C", value);
                                    this.subject.put("A", value);

                                    this.subject.put("C", null);
                                });

                                it("removes the key from table", () -> {
                                    assertFalse(this.subject.contains("C"));
                                });

                                it("decrements size by 1", () -> {
                                    assertEquals(5, this.subject.size());
                                });
                            });

                            describe("when it has a right subtree", () -> {
                                beforeEach(() -> {
                                    this.subject.put("S", value);
                                    this.subject.put("X", value);
                                    this.subject.put("E", value);
                                    this.subject.put("R", value);
                                    this.subject.put("C", value);
                                    this.subject.put("D", value);

                                    this.subject.put("C", null);
                                });

                                it("removes the key from table", () -> {
                                    assertFalse(this.subject.contains("C"));
                                });

                                it("decrements size by 1", () -> {
                                    assertEquals(5, this.subject.size());
                                });
                            });

                            describe("when it has both right and left subtrees",
                                    () -> {
                                        beforeEach(() -> {
                                            this.subject.put("S", value);
                                            this.subject.put("X", value);
                                            this.subject.put("E", value);
                                            this.subject.put("R", value);
                                            this.subject.put("C", value);
                                            this.subject.put("D", value);
                                            this.subject.put("A", value);

                                            this.subject.put("C", null);
                                        });

                                        it("removes the key from table", () -> {
                                            assertFalse(
                                                    this.subject.contains("C"));
                                        });

                                        it("decrements size by 1", () -> {
                                            assertEquals(6,
                                                    this.subject.size());
                                        });
                                    });

                            describe(
                                    "when it has both right and left subtrees with nested subtrees",
                                    () -> {
                                        beforeEach(() -> {
                                            this.subject.put("S", value);
                                            this.subject.put("X", value);
                                            this.subject.put("E", value);
                                            this.subject.put("R", value);
                                            this.subject.put("C", value);
                                            this.subject.put("D", value);
                                            this.subject.put("A", value);

                                            this.subject.put("E", null);
                                        });

                                        it("removes the key from table", () -> {
                                            assertTrue(
                                                    this.subject.contains("S"));
                                            assertTrue(
                                                    this.subject.contains("X"));
                                            assertTrue(
                                                    this.subject.contains("R"));
                                            assertTrue(
                                                    this.subject.contains("C"));
                                            assertTrue(
                                                    this.subject.contains("A"));
                                            assertTrue(
                                                    this.subject.contains("D"));
                                            assertFalse(
                                                    this.subject.contains("E"));
                                        });

                                        it("decrements size by 1", () -> {
                                            assertEquals(6,
                                                    this.subject.size());
                                        });
                                    });
                        });

                        describe("when value is not null", () -> {
                            beforeEach(() -> {
                                this.subject.put(key + 1, value);
                                this.subject.put(key, RANDOM.nextInt());
                                this.subject.put(key, value);
                            });

                            it("updates the key from table", () -> {
                                assertEquals(value, this.subject.get(key));
                            });

                            it("does not updates its size", () -> {
                                assertEquals(2, this.subject.size());
                            });
                        });
                    });

                    describe("when key does not exist yet", () -> {
                        describe("when value is null", () -> {
                            it("does not add the key to table", () -> {
                                this.subject.put(key, null);

                                assertFalse(this.subject.contains(key));
                                assertEquals(0, this.subject.size());
                            });
                        });

                        describe("when value is not null", () -> {
                            beforeEach(() -> {
                                this.subject.put(key + 1, value);
                                this.subject.put(key, value);
                            });

                            it("adds the key to table", () -> {
                                assertTrue(this.subject.contains(key));
                                assertEquals(this.subject.get(key), value);
                            });

                            it("increments size by 1", () -> {
                                assertEquals(2, this.subject.size());
                            });
                        });
                    });
                });
            });

            describe("#get", () -> {
                describe("when key is null", () -> {
                    it("throws null pointer", () -> {
                        NullPointerException throwed = null;

                        try {
                            this.subject.get(null);
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
                            this.subject.get(key);
                        } catch (NullPointerException e) {
                            throwed = e;
                        }

                        assertNull(throwed);
                    });

                    describe("when table is empty", () -> {
                        it("returns null", () -> {
                            assertNull(this.subject.get(key));
                        });
                    });

                    describe("when table is not empty", () -> {
                        describe("when table has only a single node", () -> {
                            beforeEach(() -> {
                                this.subject.put(key, value);
                            });

                            describe("when key exists", () -> {
                                it("returns the value paired with it", () -> {
                                    assertEquals(value, this.subject.get(key));
                                });
                            });

                            describe("when key does not exist", () -> {
                                it("returns null", () -> {
                                    assertNull(this.subject.get(
                                            String.valueOf(RANDOM.nextInt())));
                                });
                            });
                        });

                        describe("when table has multiple nodes", () -> {
                            beforeEach(() -> {
                                this.subject.put(key, value);
                                this.subject.put(key + 1, value + 1);
                                this.subject.put(key + 2, value + 2);
                            });

                            describe("when key exists", () -> {
                                it("returns the value paired with it", () -> {
                                    assertEquals(value, this.subject.get(key));
                                });
                            });

                            describe("when key does not exist", () -> {
                                it("returns null", () -> {
                                    assertNull(this.subject.get(
                                            String.valueOf(RANDOM.nextInt())));
                                });
                            });
                        });
                    });
                });
            });

            describe("#delete", () -> {
                describe("when key is null", () -> {
                    it("throws null pointer", () -> {
                        NullPointerException throwed = null;

                        try {
                            this.subject.delete(null);
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
                            this.subject.delete(key);
                        } catch (NullPointerException e) {
                            throwed = e;
                        }

                        assertNull(throwed);
                    });

                    describe("when key already exists", () -> {
                        describe("when it does not have subtrees", () -> {
                            beforeEach(() -> {
                                this.subject.put("S", value);
                                this.subject.put("X", value);
                                this.subject.put("E", value);
                                this.subject.put("R", value);

                                this.subject.delete("X");
                            });

                            it("removes the key from table", () -> {
                                assertTrue(this.subject.contains("S"));
                                assertFalse(this.subject.contains("X"));
                                assertTrue(this.subject.contains("E"));
                                assertTrue(this.subject.contains("R"));
                            });

                            it("decrements size by 1", () -> {
                                assertEquals(3, this.subject.size());
                            });
                        });

                        describe("when it has a left subtree", () -> {
                            beforeEach(() -> {
                                this.subject.put("S", value);
                                this.subject.put("X", value);
                                this.subject.put("E", value);
                                this.subject.put("R", value);
                                this.subject.put("C", value);
                                this.subject.put("A", value);

                                this.subject.delete("C");
                            });

                            it("removes the key from table", () -> {
                                assertTrue(this.subject.contains("S"));
                                assertTrue(this.subject.contains("X"));
                                assertTrue(this.subject.contains("E"));
                                assertTrue(this.subject.contains("R"));
                                assertFalse(this.subject.contains("C"));
                                assertTrue(this.subject.contains("A"));
                            });

                            it("decrements size by 1", () -> {
                                assertEquals(5, this.subject.size());
                            });
                        });

                        describe("when it has a right subtree", () -> {
                            beforeEach(() -> {
                                this.subject.put("S", value);
                                this.subject.put("X", value);
                                this.subject.put("E", value);
                                this.subject.put("R", value);
                                this.subject.put("C", value);
                                this.subject.put("D", value);

                                this.subject.delete("C");
                            });

                            it("removes the key from table", () -> {
                                assertTrue(this.subject.contains("S"));
                                assertTrue(this.subject.contains("X"));
                                assertTrue(this.subject.contains("E"));
                                assertTrue(this.subject.contains("R"));
                                assertFalse(this.subject.contains("C"));
                                assertTrue(this.subject.contains("D"));
                            });

                            it("decrements size by 1", () -> {
                                assertEquals(5, this.subject.size());
                            });
                        });

                        describe(
                                "when it has both right and left subtrees without nested subtrees",
                                () -> {
                                    describe("when it is not at the root",
                                            () -> {
                                                beforeEach(() -> {
                                                    this.subject.put("S",
                                                            value);
                                                    this.subject.put("X",
                                                            value);
                                                    this.subject.put("E",
                                                            value);
                                                    this.subject.put("R",
                                                            value);
                                                    this.subject.put("C",
                                                            value);
                                                    this.subject.put("D",
                                                            value);
                                                    this.subject.put("A",
                                                            value);

                                                    this.subject.delete("C");
                                                });

                                                it("removes the key from table",
                                                        () -> {
                                                            assertTrue(
                                                                    this.subject
                                                                            .contains(
                                                                                    "S"));
                                                            assertTrue(
                                                                    this.subject
                                                                            .contains(
                                                                                    "X"));
                                                            assertTrue(
                                                                    this.subject
                                                                            .contains(
                                                                                    "R"));
                                                            assertFalse(
                                                                    this.subject
                                                                            .contains(
                                                                                    "C"));
                                                            assertTrue(
                                                                    this.subject
                                                                            .contains(
                                                                                    "D"));
                                                            assertTrue(
                                                                    this.subject
                                                                            .contains(
                                                                                    "A"));
                                                        });

                                                it("decrements size by 1",
                                                        () -> {
                                                            assertEquals(6,
                                                                    this.subject
                                                                            .size());
                                                        });
                                            });

                                    describe("when it is at the root", () -> {
                                        beforeEach(() -> {
                                            this.subject.put("S", value);
                                            this.subject.put("X", value);
                                            this.subject.put("E", value);
                                            this.subject.put("R", value);
                                            this.subject.put("C", value);
                                            this.subject.put("D", value);
                                            this.subject.put("A", value);

                                            this.subject.delete("S");
                                        });

                                        it("removes the key from table", () -> {
                                            assertFalse(
                                                    this.subject.contains("S"));
                                            assertTrue(
                                                    this.subject.contains("X"));
                                            assertTrue(
                                                    this.subject.contains("E"));
                                            assertTrue(
                                                    this.subject.contains("R"));
                                            assertTrue(
                                                    this.subject.contains("C"));
                                            assertTrue(
                                                    this.subject.contains("D"));
                                            assertTrue(
                                                    this.subject.contains("A"));
                                        });

                                        it("decrements size by 1", () -> {
                                            assertEquals(6,
                                                    this.subject.size());
                                        });
                                    });
                                });

                        describe(
                                "when it has both right and left subtrees with nested subtrees",
                                () -> {
                                    beforeEach(() -> {
                                        this.subject.put("S", value);
                                        this.subject.put("X", value);
                                        this.subject.put("E", value);
                                        this.subject.put("R", value);
                                        this.subject.put("C", value);
                                        this.subject.put("D", value);
                                        this.subject.put("A", value);

                                        this.subject.delete("E");
                                    });

                                    it("removes the key from table", () -> {
                                        assertTrue(this.subject.contains("S"));
                                        assertTrue(this.subject.contains("X"));
                                        assertTrue(this.subject.contains("R"));
                                        assertTrue(this.subject.contains("C"));
                                        assertTrue(this.subject.contains("A"));
                                        assertTrue(this.subject.contains("D"));
                                        assertFalse(this.subject.contains("E"));
                                    });

                                    it("decrements size by 1", () -> {
                                        assertEquals(6, this.subject.size());
                                    });
                                });
                    });

                    describe("when key does not exist yet", () -> {
                        it("does not decrements the size of the table", () -> {
                            this.subject.delete(key);

                            assertEquals(0, this.subject.size());
                        });

                        it("does not throw null pointer", () -> {
                            NullPointerException throwed = null;

                            try {
                                this.subject.delete(key);
                            } catch (NullPointerException e) {
                                throwed = e;
                            }

                            assertNull(throwed);
                        });
                    });
                });
            });

            describe("#keys", () -> {
                describe("when table is empty", () -> {
                    it("returns an empty iterator", () -> {
                        assertNotNull(subject.keys());
                        assertFalse(subject.keys().iterator().hasNext());
                    });
                });

                describe("when table is not empty", () -> {
                    it("returns the keys from the table", () -> {
                        String[] keys = { key, key + 1, key + 2 };
                        this.subject.put(keys[0], value);
                        this.subject.put(keys[1], value + 1);
                        this.subject.put(keys[2], value + 2);

                        int size = 3;
                        int i = 0;

                        for (String k : this.subject.keys())
                            assertEquals(keys[i++], k);

                        assertEquals(size, i);
                    });
                });
            });

            describe("#keys(lo, hi)", () -> {
                describe("when table is empty", () -> {
                    it("returns an empty iterator", () -> {
                        assertNotNull(subject.keys(key, key));
                        assertFalse(
                                subject.keys(key, key).iterator().hasNext());
                    });
                });

                describe("when table is not empty", () -> {
                    it("returns the keys from the table", () -> {
                        String[] keys = { key, key + 1, key + 2, key + 3,
                                key + 4 };
                        this.subject.put(keys[0], value);
                        this.subject.put(keys[1], value + 1);
                        this.subject.put(keys[2], value + 2);
                        this.subject.put(keys[3], value + 3);
                        this.subject.put(keys[4], value + 4);

                        this.bst.put(keys[0], value);
                        this.bst.put(keys[1], value + 1);
                        this.bst.put(keys[2], value + 2);
                        this.bst.put(keys[3], value + 3);
                        this.bst.put(keys[4], value + 4);

                        int i = 0;

                        for (String k : this.subject.keys(keys[1], keys[3]))
                            assertEquals(keys[++i], k);

                        i = 0;

                        for (String k : this.bst.keys(keys[1], keys[3]))
                            assertEquals(keys[++i], k);
                    });
                });
            });

            describe("#min", () -> {
                it("returns the minimum key", () -> {
                    subject.put(key + 1, value);
                    subject.put(key, value);
                    subject.put(key + 2, value);

                    this.bst.put(key + 1, value);
                    this.bst.put(key, value);
                    this.bst.put(key + 2, value);

                    assertEquals(this.bst.min(), subject.min());
                });
            });

            describe("#max", () -> {
                it("returns the maximum key", () -> {
                    subject.put(key + 1, value);
                    subject.put(key, value);
                    subject.put(key + 2, value);

                    this.bst.put(key + 1, value);
                    this.bst.put(key, value);
                    this.bst.put(key + 2, value);

                    assertEquals(this.bst.max(), subject.max());
                });
            });

            describe("#rank", () -> {
                describe("when key is on the table", () -> {
                    it("returns number of keys less than key", () -> {
                        subject.put("X", value);
                        subject.put("F", value);
                        subject.put("M", value);
                        subject.put("R", value);
                        subject.put("C", value);
                        subject.put("H", value);
                        subject.put("A", value);
                        subject.put("E", value);
                        subject.put("S", value);

                        this.bst.put("X", value);
                        this.bst.put("F", value);
                        this.bst.put("M", value);
                        this.bst.put("R", value);
                        this.bst.put("C", value);
                        this.bst.put("H", value);
                        this.bst.put("A", value);
                        this.bst.put("E", value);
                        this.bst.put("S", value);

                        assertEquals(this.bst.rank("X"), subject.rank("X"));
                        assertEquals(this.bst.rank("F"), subject.rank("F"));
                        assertEquals(this.bst.rank("M"), subject.rank("M"));
                        assertEquals(this.bst.rank("R"), subject.rank("R"));
                        assertEquals(this.bst.rank("C"), subject.rank("C"));
                        assertEquals(this.bst.rank("H"), subject.rank("H"));
                        assertEquals(this.bst.rank("A"), subject.rank("A"));
                        assertEquals(this.bst.rank("E"), subject.rank("E"));
                        assertEquals(this.bst.rank("S"), subject.rank("S"));
                    });
                });

                describe("when key is not on the table", () -> {
                    it("returns number of keys less than key", () -> {
                        subject.put("X", value);
                        subject.put("F", value);
                        subject.put("M", value);
                        subject.put("R", value);
                        subject.put("C", value);
                        subject.put("H", value);
                        subject.put("A", value);
                        subject.put("E", value);
                        subject.put("S", value);

                        this.bst.put("X", value);
                        this.bst.put("F", value);
                        this.bst.put("M", value);
                        this.bst.put("R", value);
                        this.bst.put("C", value);
                        this.bst.put("H", value);
                        this.bst.put("A", value);
                        this.bst.put("E", value);
                        this.bst.put("S", value);

                        assertEquals(this.bst.rank("B"), subject.rank("B"));
                        assertEquals(this.bst.rank("D"), subject.rank("D"));
                        assertEquals(this.bst.rank("G"), subject.rank("G"));
                        assertEquals(this.bst.rank("I"), subject.rank("I"));
                        assertEquals(this.bst.rank("J"), subject.rank("J"));
                        assertEquals(this.bst.rank("K"), subject.rank("K"));
                        assertEquals(this.bst.rank("L"), subject.rank("L"));
                        assertEquals(this.bst.rank("N"), subject.rank("N"));
                        assertEquals(this.bst.rank("O"), subject.rank("O"));
                        assertEquals(this.bst.rank("P"), subject.rank("P"));
                        assertEquals(this.bst.rank("Q"), subject.rank("Q"));
                        assertEquals(this.bst.rank("T"), subject.rank("T"));
                        assertEquals(this.bst.rank("U"), subject.rank("U"));
                        assertEquals(this.bst.rank("V"), subject.rank("V"));
                        assertEquals(this.bst.rank("Y"), subject.rank("Y"));
                        assertEquals(this.bst.rank("Z"), subject.rank("Z"));
                        assertEquals(this.bst.rank("W"), subject.rank("W"));
                    });
                });

                it("returns number of keys less than key", () -> {
                    subject.put("X", value);
                    subject.put("F", value);
                    subject.put("M", value);
                    subject.put("R", value);
                    subject.put("C", value);
                    subject.put("H", value);
                    subject.put("A", value);
                    subject.put("E", value);
                    subject.put("S", value);

                    this.bst.put("X", value);
                    this.bst.put("F", value);
                    this.bst.put("M", value);
                    this.bst.put("R", value);
                    this.bst.put("C", value);
                    this.bst.put("H", value);
                    this.bst.put("A", value);
                    this.bst.put("E", value);
                    this.bst.put("S", value);

                    assertEquals(this.bst.rank("X"), subject.rank("X"));
                    assertEquals(this.bst.rank("F"), subject.rank("F"));
                    assertEquals(this.bst.rank("M"), subject.rank("M"));
                    assertEquals(this.bst.rank("R"), subject.rank("R"));
                    assertEquals(this.bst.rank("C"), subject.rank("C"));
                    assertEquals(this.bst.rank("H"), subject.rank("H"));
                    assertEquals(this.bst.rank("A"), subject.rank("A"));
                    assertEquals(this.bst.rank("E"), subject.rank("E"));
                    assertEquals(this.bst.rank("S"), subject.rank("S"));
                });
            });

            describe("#floor", () -> {
                describe("when key is on the table", () -> {
                    it("returns the largest key less than or equal to key",
                            () -> {
                                subject.put("X", value);
                                subject.put("F", value);
                                subject.put("M", value);
                                subject.put("R", value);
                                subject.put("C", value);
                                subject.put("H", value);
                                subject.put("A", value);
                                subject.put("E", value);
                                subject.put("S", value);

                                this.bst.put("X", value);
                                this.bst.put("F", value);
                                this.bst.put("M", value);
                                this.bst.put("R", value);
                                this.bst.put("C", value);
                                this.bst.put("H", value);
                                this.bst.put("A", value);
                                this.bst.put("E", value);
                                this.bst.put("S", value);

                                assertEquals(this.bst.floor("A"),
                                        subject.floor("A"));
                                assertEquals(this.bst.floor("C"),
                                        subject.floor("C"));
                                assertEquals(this.bst.floor("E"),
                                        subject.floor("E"));
                                assertEquals(this.bst.floor("F"),
                                        subject.floor("F"));
                                assertEquals(this.bst.floor("H"),
                                        subject.floor("H"));
                                assertEquals(this.bst.floor("M"),
                                        subject.floor("M"));
                                assertEquals(this.bst.floor("R"),
                                        subject.floor("R"));
                                assertEquals(this.bst.floor("S"),
                                        subject.floor("S"));
                                assertEquals(this.bst.floor("X"),
                                        subject.floor("X"));
                            });
                });

                describe("when key is not on the table", () -> {
                    it("returns the largest key less than or equal to key",
                            () -> {
                                subject.put("X", value);
                                subject.put("F", value);
                                subject.put("M", value);
                                subject.put("R", value);
                                subject.put("C", value);
                                subject.put("H", value);
                                subject.put("A", value);
                                subject.put("E", value);
                                subject.put("S", value);

                                this.bst.put("X", value);
                                this.bst.put("F", value);
                                this.bst.put("M", value);
                                this.bst.put("R", value);
                                this.bst.put("C", value);
                                this.bst.put("H", value);
                                this.bst.put("A", value);
                                this.bst.put("E", value);
                                this.bst.put("S", value);

                                assertEquals(this.bst.floor("B"),
                                        subject.floor("B"));
                                assertEquals(this.bst.floor("D"),
                                        subject.floor("D"));
                                assertEquals(this.bst.floor("G"),
                                        subject.floor("G"));
                                assertEquals(this.bst.floor("I"),
                                        subject.floor("I"));
                                assertEquals(this.bst.floor("J"),
                                        subject.floor("J"));
                                assertEquals(this.bst.floor("K"),
                                        subject.floor("K"));
                                assertEquals(this.bst.floor("L"),
                                        subject.floor("L"));
                                assertEquals(this.bst.floor("N"),
                                        subject.floor("N"));
                                assertEquals(this.bst.floor("O"),
                                        subject.floor("O"));
                                assertEquals(this.bst.floor("T"),
                                        subject.floor("T"));
                                assertEquals(this.bst.floor("U"),
                                        subject.floor("U"));
                                assertEquals(this.bst.floor("V"),
                                        subject.floor("V"));
                                assertEquals(this.bst.floor("W"),
                                        subject.floor("W"));
                                assertEquals(this.bst.floor("Y"),
                                        subject.floor("Y"));
                                assertEquals(this.bst.floor("Z"),
                                        subject.floor("Z"));
                            });
                });

            });

            describe("#ceiling", () -> {
                describe("when key is on the table", () -> {
                    it("returns the smallest key greater than or equal to key",
                            () -> {
                                subject.put("X", value);
                                subject.put("F", value);
                                subject.put("M", value);
                                subject.put("A", value);
                                subject.put("R", value);
                                subject.put("C", value);
                                subject.put("H", value);
                                subject.put("E", value);
                                subject.put("S", value);

                                this.bst.put("X", value);
                                this.bst.put("F", value);
                                this.bst.put("M", value);
                                this.bst.put("R", value);
                                this.bst.put("C", value);
                                this.bst.put("H", value);
                                this.bst.put("A", value);
                                this.bst.put("E", value);
                                this.bst.put("S", value);

                                assertEquals(this.bst.ceiling("A"),
                                        subject.ceiling("A"));
                                assertEquals(this.bst.ceiling("C"),
                                        subject.ceiling("C"));
                                assertEquals(this.bst.ceiling("E"),
                                        subject.ceiling("E"));
                                assertEquals(this.bst.ceiling("F"),
                                        subject.ceiling("F"));
                                assertEquals(this.bst.ceiling("H"),
                                        subject.ceiling("H"));
                                assertEquals(this.bst.ceiling("M"),
                                        subject.ceiling("M"));
                                assertEquals(this.bst.ceiling("R"),
                                        subject.ceiling("R"));
                                assertEquals(this.bst.ceiling("S"),
                                        subject.ceiling("S"));
                                assertEquals(this.bst.ceiling("X"),
                                        subject.ceiling("X"));
                            });
                });

                describe("when key is not on the table", () -> {
                    it("returns the smallest key greater than or equal to key",
                            () -> {
                                subject.put("X", value);
                                subject.put("F", value);
                                subject.put("M", value);
                                subject.put("A", value);
                                subject.put("R", value);
                                subject.put("C", value);
                                subject.put("H", value);
                                subject.put("E", value);
                                subject.put("S", value);

                                this.bst.put("X", value);
                                this.bst.put("F", value);
                                this.bst.put("M", value);
                                this.bst.put("R", value);
                                this.bst.put("C", value);
                                this.bst.put("H", value);
                                this.bst.put("A", value);
                                this.bst.put("E", value);
                                this.bst.put("S", value);

                                assertEquals(this.bst.ceiling("B"),
                                        subject.ceiling("B"));
                                assertEquals(this.bst.ceiling("D"),
                                        subject.ceiling("D"));
                                assertEquals(this.bst.ceiling("G"),
                                        subject.ceiling("G"));
                                assertEquals(this.bst.ceiling("I"),
                                        subject.ceiling("I"));
                                assertEquals(this.bst.ceiling("J"),
                                        subject.ceiling("J"));
                                assertEquals(this.bst.ceiling("K"),
                                        subject.ceiling("K"));
                                assertEquals(this.bst.ceiling("L"),
                                        subject.ceiling("L"));
                                assertEquals(this.bst.ceiling("N"),
                                        subject.ceiling("N"));
                                assertEquals(this.bst.ceiling("O"),
                                        subject.ceiling("O"));
                                assertEquals(this.bst.ceiling("P"),
                                        subject.ceiling("P"));
                                assertEquals(this.bst.ceiling("Q"),
                                        subject.ceiling("Q"));
                                assertEquals(this.bst.ceiling("T"),
                                        subject.ceiling("T"));
                                assertEquals(this.bst.ceiling("U"),
                                        subject.ceiling("U"));
                                assertEquals(this.bst.ceiling("V"),
                                        subject.ceiling("V"));
                                assertEquals(this.bst.ceiling("W"),
                                        subject.ceiling("W"));
                                assertEquals(this.bst.ceiling("Y"),
                                        subject.ceiling("Y"));
                                assertEquals(this.bst.ceiling("Z"),
                                        subject.ceiling("Z"));
                            });
                });

            });

            describe("#deleteMin", () -> {
                describe("when smallest key is at the root", () -> {
                    it("deletes it", () -> {
                        this.subject.put("A", value);
                        this.subject.put("C", value);
                        this.subject.put("B", value);
                        this.subject.put("D", value);
                        this.subject.put("T", value);

                        assertTrue(this.subject.contains("A"));

                        this.subject.deleteMin();

                        assertFalse(this.subject.contains("A"));
                    });
                });

                describe("when smallest key is not at the root", () -> {
                    describe("when it does not have right subtree", () -> {
                        it("deletes it", () -> {
                            this.subject.put(key + 2, value);
                            this.subject.put(key + 4, value);
                            this.subject.put(key + 1, value);
                            this.subject.put(key + 3, value);

                            assertTrue(this.subject.contains(key + 1));

                            this.subject.deleteMin();

                            assertFalse(this.subject.contains(key + 1));
                        });
                    });

                    describe("when it has right subtree", () -> {
                        it("deletes it", () -> {
                            this.subject.put(key + 4, value);
                            this.subject.put(key + 1, value);
                            this.subject.put(key + 2, value);
                            this.subject.put(key + 3, value);

                            assertTrue(this.subject.contains(key + 1));

                            this.subject.deleteMin();

                            assertFalse(this.subject.contains(key + 1));
                        });
                    });
                });
            });

            describe("#deleteMax", () -> {
                describe("when largest key is at the root", () -> {
                    it("deletes it", () -> {
                        this.subject.put("T", value);
                        this.subject.put("A", value);
                        this.subject.put("D", value);
                        this.subject.put("C", value);
                        this.subject.put("B", value);

                        assertTrue(this.subject.contains("T"));

                        this.subject.deleteMax();

                        assertFalse(this.subject.contains("T"));
                    });
                });

                describe("when smallest key is not at the root", () -> {
                    describe("when it does not have left subtree", () -> {
                        it("deletes it", () -> {
                            this.subject.put(key + 4, value);
                            this.subject.put(key + 1, value);
                            this.subject.put(key + 5, value);
                            this.subject.put(key + 6, value);
                            this.subject.put(key + 2, value);

                            assertTrue(this.subject.contains(key + 6));

                            this.subject.deleteMax();

                            assertFalse(this.subject.contains(key + 6));
                        });
                    });

                    describe("when it has left subtree", () -> {
                        it("deletes it", () -> {
                            this.subject.put(key + 1, value);
                            this.subject.put(key + 7, value);
                            this.subject.put(key + 6, value);
                            this.subject.put(key + 5, value);
                            this.subject.put(key, value);

                            assertTrue(this.subject.contains(key + 7));

                            this.subject.deleteMax();

                            assertFalse(this.subject.contains(key + 7));
                        });
                    });
                });
            });

            xdescribe("#select", () -> {
                describe("when index k is lesser than 0", () -> {
                    it("throws argument error", () -> {
                        IllegalArgumentException throwed = null;

                        try {
                            this.subject.select(-1);
                        } catch (IllegalArgumentException e) {
                            throwed = e;
                        }

                        assertNotNull(throwed);
                    });
                });

                xdescribe("when index k is greater or equal to table size",
                        () -> {
                            it("throws argument error", () -> {
                                this.subject.put(key, value);

                                IllegalArgumentException throwed = null;

                                try {
                                    this.subject.select(1);
                                } catch (IllegalArgumentException e) {
                                    throwed = e;
                                }

                                assertNotNull(throwed);
                            });
                        });

                describe("when index k is between 0 and table size - 1", () -> {
                    it("does not throw argument error", () -> {
                        this.subject.put(key, value);
                        this.subject.put(key + 1, value);

                        IllegalArgumentException throwed = null;

                        try {
                            this.subject.select(1);
                        } catch (IllegalArgumentException e) {
                            throwed = e;
                        }

                        assertNull(throwed);
                    });

                    it("returns the associated key", () -> {
                        this.subject.put("B", value);
                        this.subject.put("A", value);
                        this.subject.put("C", value);
                        this.subject.put("T", value);
                        this.subject.put("D", value);

                        assertEquals(key, this.subject.select(0));
                    });
                });
            });
        });
    }
}
