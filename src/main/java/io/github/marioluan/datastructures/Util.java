package io.github.marioluan.datastructures;

/**
 * Utility with static methods to be used by data structures implementations.
 * 
 * @author marioluan
 */
public final class Util {

    private Util() {
    }

    /**
     * Check whether {@link Comparable a} is lesser than {@link Comparable b}.
     *
     * @param a
     * @param b
     * @return returns whether a is lesser than b
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    /**
     * Check whether {@link a} is lesser than {@link b}.
     * 
     * @param <T>
     * @param a
     * @param b
     * @return returns whether a is lesser than b
     */
    @SuppressWarnings("unchecked")
    public static <T> boolean less(T a, T b) {
        return ((Comparable<T>) a).compareTo(b) < 0;
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
    public static boolean lessOrEqual(Comparable a, Comparable b) {
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
    public static void swap(Comparable[] a, int i, int j) {
        Comparable copy = a[i];
        a[i] = a[j];
        a[j] = copy;
    }

    /**
     * Swap item in position i by item in position j from array {@link a}.
     * 
     * @param <T>
     * @param a
     * @param i
     * @param j
     */
    public static <T> void swap(T[] a, int i, int j) {
        T copy = a[i];
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
    public static int findMin(Comparable[] a, int lowerBound, int upperBound) {
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
    public static void copy(Comparable[] a, Comparable[] aux, int lo, int hi) {
        for (int i = lo; i <= hi; i++)
            aux[i] = a[i];
    }

    /**
     * Make a copy of items from array a within lo and hi bounds into array aux.
     * 
     * @param <T>
     * @param a
     * @param aux
     * @param lo
     * @param hi
     */
    public static <T> void copy(T[] a, T[] aux, int lo, int hi) {
        for (int i = lo; i <= hi; i++)
            aux[i] = a[i];
    }
}
