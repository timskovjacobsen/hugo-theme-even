import java.util.Comparator;

public class InsertionSort {

    /**
     * Implementation of insertion sort.
     *
     * The algorithm is in-place, stable and adaptive.
     * 
     * Time complexity: Best case: O(n) and worst case: O(n^2)
     *
     * It is assumed that the passed in array and comparator are both valid and will
     * never be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array that must be sorted after the method runs.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void sort(T[] arr, Comparator<T> comparator) {

        //
        for (int n = 1; n <= arr.length - 1; n++) {
            int i = n;
            while (i > 0 && comparator.compare(arr[i], arr[i - 1]) < 0) {
                // Swap values at i and i-1 and decrement i
                T temp = arr[i];
                arr[i] = arr[i - 1];
                arr[i - 1] = temp;
                i--;
            }
        }
    }
}
