package io.github.marioluan.datastructures.symboltable;

import static com.greghaskins.spectrum.Spectrum.afterEach;
import static com.greghaskins.spectrum.Spectrum.beforeEach;
import static com.greghaskins.spectrum.Spectrum.describe;
import static com.greghaskins.spectrum.Spectrum.it;
import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.runner.RunWith;

import com.greghaskins.spectrum.Spectrum;

import edu.princeton.cs.algs4.Queue;

@RunWith(Spectrum.class)
public class OneDRangeCountTest {

    private OneDRangeCount<String, Integer> subject;
    private static final Random             RANDOM = new Random();
    private Integer                         value;

    {
        describe("OneDRangeCount", () -> {
            beforeEach(() -> {
                this.subject = new OneDRangeCount<>();
                this.value = RANDOM.nextInt();
            });

            afterEach(() -> {
                this.subject = null;
                this.value = null;
            });

            describe("#count(lo, hi)", () -> {
                it("returns the number of keys between lo and hi", () -> {
                    this.subject.put("S", value);
                    this.subject.put("X", value);
                    this.subject.put("E", value);
                    this.subject.put("R", value);
                    this.subject.put("A", value);
                    this.subject.put("C", value);
                    this.subject.put("H", value);
                    this.subject.put("M", value);
                    this.subject.put("L", value);
                    this.subject.put("P", value);

                    int count = 6;

                    assertEquals(count, this.subject.count("F", "T"));
                });
            });

            describe("#range(lo, hi)", () -> {
                it("returns the key between lo and hi", () -> {
                    this.subject.put("S", value);
                    this.subject.put("X", value);
                    this.subject.put("E", value);
                    this.subject.put("R", value);
                    this.subject.put("A", value);
                    this.subject.put("C", value);
                    this.subject.put("H", value);
                    this.subject.put("M", value);
                    this.subject.put("L", value);
                    this.subject.put("P", value);

                    Queue<String> keys = new Queue<>();
                    keys.enqueue("H");
                    keys.enqueue("L");
                    keys.enqueue("M");
                    keys.enqueue("P");
                    keys.enqueue("R");
                    keys.enqueue("S");

                    int queueSize = keys.size();
                    int rangeSize = 0;

                    for (String key : this.subject.range("F", "T")) {
                        rangeSize++;
                        assertEquals(keys.dequeue(), key);
                    }

                    assertEquals(queueSize, rangeSize);
                });
            });
        });
    }
}
