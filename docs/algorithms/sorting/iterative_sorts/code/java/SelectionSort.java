import java.util.Comparator;

public class SelectionSort {

    /**
     * Implementation of selection sort.
     *
     * The algorithm is in-place, unstable and not adaptive
     * 
     * Time complexity: Best and worst case: O(n^2)
     *
     * It is assumes that the passed in array and comparator are both valid and will
     * never be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array that must be sorted after the method runs.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void sort(T[] arr, Comparator<T> comparator) {
        for (int n = arr.length - 1; n > 0; n--) {
            int maxIdx = n;
            for (int i = 0; i < n; i++) {
                if (comparator.compare(arr[i], arr[maxIdx]) > 0) {
                    maxIdx = i;
                }
            }
            T temp = arr[maxIdx];
            arr[maxIdx] = arr[n];
            arr[n] = temp;
        }
    }
}
