import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;

import java.util.Comparator;

import java.util.Arrays;

public class TestLSDRadixSort {

    @Test
    public void testArrayFromAssignmentExample() {
        int[] arr = { 1, 2, 6, 5, 3, 4, 7, 8, 9 };
        int[] expected = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        LSDRadixSort.sort(arr);
        System.out.println(Arrays.toString(arr));
        assertArrayEquals(expected, arr);
    }

    @Test
    public void testEmptyArray() {
        int[] arr = {};
        LSDRadixSort.sort(arr);
        int[] expected = {};
        assertArrayEquals(expected, arr);
    }

    @Test
    public void testArrayWithLargerNumbers() {
        int[] arr = { 511, 23, 234, 7, 1044, 363, 834, 77, 238, 495 };
        int[] expected = { 7, 23, 77, 234, 238, 363, 495, 511, 834, 1044 };
        LSDRadixSort.sort(arr);
        System.out.println(Arrays.toString(arr));
        assertArrayEquals(expected, arr);
    }

    @Test
    public void testArrayWithVeryLargeNumbers() {
        int[] arr = { 513123, 413123 };
        LSDRadixSort.sort(arr);
        int[] expected = { 413123, 513123 };
        System.out.println(Arrays.toString(arr));
        assertArrayEquals(expected, arr);
    }

    @Test
    public void testArrayWithZeroAsANumber() {
        int[] arr = { 1, 0 };
        LSDRadixSort.sort(arr);
        int[] expected = { 0, 1 };
        System.out.println(Arrays.toString(arr));
        assertArrayEquals(expected, arr);
    }

    @Test
    public void testArrayWithVeryNegativeNumber() {
        int[] arr1 = { -100, 42, -214, 134, 673 };
        int[] arr2 = { 1, -111, 3, -2 };
        int[] arr3 = { 1, -2 };
        LSDRadixSort.sort(arr1);
        LSDRadixSort.sort(arr2);
        LSDRadixSort.sort(arr3);
        int[] expected1 = { -214, -100, 42, 134, 673 };
        int[] expected2 = { -111, -2, 1, 3 };
        int[] expected3 = { -2, 1 };
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));
        System.out.println(Arrays.toString(arr3));
        assertArrayEquals(expected1, arr1);
        assertArrayEquals(expected2, arr2);
        assertArrayEquals(expected3, arr3);
    }

    @Test
    public void testArrayWithVeryNegativeNumber2() {
        int[] arr = { 1000, 2014, 231, 53, -1, -92, -9403, 634, -207 };
        LSDRadixSort.sort(arr);
        int[] expected = { -9403, -207, -92, -1, 53, 231, 634, 1000, 2014 };
        System.out.println(Arrays.toString(arr));
        assertArrayEquals(expected, arr);
    }

    @Test
    public void testArrayWithNegativeOverflowInt() {
        int[] arr = { 17, -2147483648, 2147483647 };
        LSDRadixSort.sort(arr);
        int[] expected = { -2147483648, 17, 2147483647 };
        System.out.println(Arrays.toString(arr));
        assertArrayEquals(expected, arr);
    }
}
