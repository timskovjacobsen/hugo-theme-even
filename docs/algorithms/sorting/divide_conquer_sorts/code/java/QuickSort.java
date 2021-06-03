import java.util.Comparator;
import java.util.Arrays;

public class QuickSort {

    public static <T> void sort(T[] arr, Comparator<T> comparator) {
        sortBackend(arr, 0, arr.length - 1, comparator);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 
     * @param <T>        The data type to sort
     * @param arr        The array to be sorted
     * @param start      The start index of the array
     * @param end        The end index of the array (inclusive)
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void sortBackend(T[] arr, int start, int end, Comparator<T> comparator) {

        // ----- PART 1: Initial part -----
        // Base case
        if (end - start < 1) {
            return;
        }
        // Pick the middle index in the array for pivot point
        int pivotIdx = start + (end - start) / 2;
        T pivotVal = arr[pivotIdx];

        // Swap pivot point with first element
        arr[pivotIdx] = arr[start];
        arr[start] = pivotVal;

        // ----- PART 2: Iteration part -----
        int i = start + 1;
        int j = end;
        // Loop as long as index j and i have not crossed each other
        while (i <= j) {
            // Loop until value is larger than the pivot, or until i crosses j
            while (i <= j && comparator.compare(arr[i], pivotVal) < 0) {
                i++;
            }
            // Loop until value is smaller than the pivot, or until i crosses j
            while (i <= j && comparator.compare(arr[j], pivotVal) > 0) {
                j--;
            }

            if (i <= j) {
                // i has not strictly passed j, swap the values at i and j
                T temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }
        // ----- PART 3: Recursion part -----
        // i has strictly passed j, swap the values at start and j
        T temp = arr[start];
        arr[start] = arr[j];
        arr[j] = temp;

        // Recurse to the left and right sub arrays
        sortBackend(arr, start, j - 1, comparator);
        sortBackend(arr, j + 1, end, comparator);
    }
}