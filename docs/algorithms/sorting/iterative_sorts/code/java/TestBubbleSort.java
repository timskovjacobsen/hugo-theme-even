import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;

import java.util.Comparator;

public class TestBubbleSort {

    public class IntComparator implements Comparator<Integer> {

        public int compare(Integer i1, Integer i2) {
            return i1 - i2;
        }
    }

    public class StringComparator implements Comparator<String> {
        public int compare(String s1, String s2) {
            return s1.compareTo(s2);
        }
    }

    @Test
    public void testArrayFromAssignmentExample() {
        Integer[] arr = { 1, 2, 6, 5, 3, 4, 7, 8, 9 };
        BubbleSort.sort(arr, new IntComparator());
        Integer[] expected = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        assertArrayEquals(expected, arr);
    }

    @Test
    public void testTwoElementArray() {
        Integer[] arr = { 1, 0 };
        BubbleSort.sort(arr, new IntComparator());
        Integer[] expected = { 0, 1 };
        assertArrayEquals(expected, arr);
    }

    @Test
    public void testIntArray() {
        Integer[] arr = { 8, 6, 7, 9, 5, 4, 3, 10, 1, 0, 2 };
        BubbleSort.sort(arr, new IntComparator());
        Integer[] expected = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        assertArrayEquals(expected, arr);
    }

    @Test
    public void testMixedStringArray() {
        String[] arr = { "4b", "1b", "2a", "2c", "3a", "3b", "3c", "4a", "4c", "5a", "1a", "5b", "1c", "5c", "2b" };
        BubbleSort.sort(arr, new StringComparator());
        String[] expected = { "1a", "1b", "1c", "2a", "2b", "2c", "3a", "3b", "3c", "4a", "4b", "4c", "5a", "5b",
                "5c" };
        assertArrayEquals(expected, arr);
    }
}
