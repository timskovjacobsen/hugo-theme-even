import java.util.Comparator;

public class MergeSort {

    /**
     * Implementation of merge sort.
     *
     * The algorithm is out-of-place, stable and not adaptive.
     *
     * Time complexity: Best/average/worst case: O(n log n)
     *
     * When splitting the array, if there is an odd number of elements, the extra
     * data is put on the right side.
     *
     * It is assumes that the passed in array and comparator are both valid and will
     * not be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array to be sorted.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void sort(T[] arr, Comparator<T> comparator) {
        // SPLITTING PART
        int len = arr.length;
        if (len < 2) {
            // Empty or single element arrays are already sorted, do nothing
            return;
        }
        int midIdx = len / 2;
        T[] left = sliceArray(arr, 0, midIdx);
        T[] right = sliceArray(arr, midIdx, len);

        // Enter the recursive call
        sort(left, comparator);
        sort(right, comparator);

        // SORTING AND MERGING PART
        int i = 0; // Index of left sub array
        int j = 0; // Index of right sub array
        while (i < left.length && j < right.length) {
            if (comparator.compare(left[i], right[j]) <= 0) {
                arr[i + j] = left[i];
                i++;
            } else {
                arr[i + j] = right[j];
                j++;
            }
        }

        // EMPTY THE SUB ARRAY THAT HAS DATA LEFT (not always necessary)
        while (i < left.length) {
            arr[i + j] = left[i];
            i++;
        }
        while (j < right.length) {
            arr[i + j] = right[j];
            j++;
        }
    }

    /**
     *
     * @param <T>
     * @param arr   The array to slice
     * @param start The start index for the slice to extract (inclusive)
     * @param end   The end index for the slice to extract (exclusive)
     * @return The sliced array containing indices arr[start:end-1]
     */
    private static <T> T[] sliceArray(T[] arr, int start, int end) {

        int newLength = end - start;
        T[] newArray = (T[]) new Object[newLength];
        for (int i = 0; i < newLength; i++) {
            newArray[i] = arr[start + i];
        }
        return newArray;
    }
}