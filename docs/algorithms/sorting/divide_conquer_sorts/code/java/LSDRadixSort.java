import java.util.LinkedList;
import java.util.Arrays;

/**
 * Your implementation of various divide & conquer sorting algorithms.
 */

public class LSDRadixSort {

    /**
     * Implementation of LSD (least significant digit) radix sort.
     *
     * The algorithm is out-of-place, stable not and adaptive.
     *
     * Time complexity: Best case: O(kn) Worst case O(kn) where k represents the
     * number of digits in the numerically largest integer in the * array
     * 
     * It is assumed that the passed in array is valid and will not be null.
     *
     * @param arr The array to be sorted.
     */
    public static void sort(int[] arr) {

        if (arr.length < 2) {
            // Empty or single element arrays are already sorted, do nothing
            return;
        }
        // Create buckets as as array of linked lists
        // The buckets behave like queues enqueueing at back and dequeuing at front
        // 19 buckets represent the digits -9 -> 9
        LinkedList<Integer>[] buckets = new LinkedList[19];
        for (int l = 0; l < 19; l++) {
            buckets[l] = new LinkedList<>();
        }

        int div = 1;
        // k is the number of digits in the longest number (i.e. largest number)
        int k = getNumDigitsInLargestInt(arr);
        for (int dummy = 0; dummy < k; dummy++) {
            for (int i = 0; i < arr.length; i++) {
                // Calculate current digit for bucket placement
                // E.g. the number 1234 will be added to bucket 4, since 4 is as the
                // ones place
                int digit = (arr[i] / div) % 10;

                buckets[digit + 9].add(arr[i]);
            }
            // Set index for re-adding elements to the array
            // Here we are overwriting the original array
            int idx = 0;
            for (LinkedList<Integer> bucket : buckets) {
                // Move next queued integer from bucket into array
                while (!bucket.isEmpty()) {
                    arr[idx++] = bucket.remove();
                }
            }
            // Move to next significant digit
            div = div * 10;

            // Print status of array after this iteration
            System.out.println("Array after " + (dummy + 1) + "th iteration: " + Arrays.toString(arr));
        }
    }

    private static int getNumDigits(int number) {
        if (number == 0) {
            return 1;
        }
        if (number == Integer.MIN_VALUE) {
            // Abs(-2147483648) will overflow the int range
            return 10;
        }
        // Continuously divide by ten while counting digits
        number = Math.abs(number);
        int numDigits = 0;
        while (number > 0) {
            number = number / 10;
            numDigits++;
        }
        return numDigits;
    }

    private static int getNumDigitsInLargestInt(int[] arr) {
        if (arr.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        }
        int maxDigits = getNumDigits(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            int currentDigits = getNumDigits(arr[i]);
            if (currentDigits > maxDigits) {
                maxDigits = currentDigits;
            }
        }
        return maxDigits;
    }
}