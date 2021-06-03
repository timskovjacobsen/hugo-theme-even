import java.util.Comparator;

public class BubbleSort {

    /**
     * Implementation of bubble sort with the last swap optimization.
     *
     * The algorithm is in-place, stable and adaptive
     *
     * Time complexity: Best cse O(n) and worst case O(n^2)
     *
     * It is assumed that the passed in array and comparator are both valid and will
     * never be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array that must be sorted after the method runs.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void sort(T[] arr, Comparator<T> comparator) {

        int stopIdx = arr.length - 1;
        while (stopIdx > 0) {
            int i = 0;
            int lastSwappedIdx = 0;
            while (i < stopIdx) {
                if (comparator.compare(arr[i], arr[i + 1]) > 0) {
                    // Swap the elements
                    T temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;

                    lastSwappedIdx = i;
                }
                i++;
            }
            stopIdx = lastSwappedIdx;
        }
    }
}
