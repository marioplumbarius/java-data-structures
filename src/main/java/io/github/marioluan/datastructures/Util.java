package io.github.marioluan.datastructures;

/**
 * Abstract layer with helper methods for data structures' implementations.
 * 
 * @author marioluan
 */
public abstract class Util {

    /**
     * Check whether {@link Comparable a} is lesser than {@link Comparable b}.
     *
     * @param a
     * @param b
     * @return returns whether a is lesser than b
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    /**
     * Check whether {@link Comparable a} is lesser or equal to
     * {@link Comparable b}.
     *
     * @param a
     * @param b
     * @return returns whether a is lesser or equal to b
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected static boolean lessOrEqual(Comparable a, Comparable b) {
        return a.compareTo(b) < 1;
    }

    /**
     * Swap item in position i by item in position j from array {@link a}.
     *
     * @param a
     * @param i
     * @param j
     */
    @SuppressWarnings("rawtypes")
    protected static void swap(Comparable[] a, int i, int j) {
        Comparable copy = a[i];
        a[i] = a[j];
        a[j] = copy;
    }

    /**
     * Find and return the index from the minimum item from array a within
     * lowerBound and upperBound.
     * 
     * @param a
     * @param lowerBound
     * @param upperBound
     * @return returns the index from the element with the minimum value
     */
    @SuppressWarnings("rawtypes")
    protected static int findMin(Comparable[] a, int lowerBound,
            int upperBound) {
        int min = lowerBound;
        for (int i = lowerBound + 1; i < upperBound; i++)
            if (less(a[i], a[min]))
                min = i;

        return min;
    }

    /**
     * Make a copy of items from array a within lo and hi bounds into array aux.
     *
     * @param a
     * @param aux
     * @param lo
     * @param hi
     */
    @SuppressWarnings("rawtypes")
    protected static void copy(Comparable[] a, Comparable[] aux, int lo,
            int hi) {
        for (int i = lo; i <= hi; i++)
            aux[i] = a[i];
    }
}
