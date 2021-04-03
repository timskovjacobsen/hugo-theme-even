import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class TestMaxHeap {

    public MaxHeap<Integer> initialMaxHeapAdd() {
        MaxHeap<Integer> heap = new MaxHeap<>();
        heap.add(10);
        heap.add(5);
        heap.add(3);
        heap.add(2);
        heap.add(4);
        return heap;
    }

    @Test
    public void testAdd() {

        MaxHeap<Integer> heap = initialMaxHeapAdd();
        String actualInitial = Arrays.toString(heap.getBackingArray());
        String expectedInitial = "[null, 10, 5, 3, 2, 4, null, null, null, null, null, null, null]";
        assertEquals(expectedInitial, actualInitial);

        heap.add(15);
        String actual = Arrays.toString(heap.getBackingArray());
        String expected = "[null, 15, 5, 10, 2, 4, 3, null, null, null, null, null, null]";
        assertEquals(expected, actual);

    }

    // @Test
    // public void testAddWithFullArray() {
    // }

    // public MaxHeap<Integer> initialMaxHeapRemove() {
    // MaxHeap<Integer> heap = new MaxHeap<>();
    // heap.add(0);
    // heap.add(1);
    // heap.add(2);
    // heap.add(4);
    // heap.add(3);
    // heap.add(5);
    // heap.add(7);
    // heap.add(6);
    // heap.add(8);
    // heap.add(9);
    // heap.add(10);
    // return heap;
    // }

    // @Test
    // public void testRemove() {
    // MaxHeap<Integer> heap = initialMaxHeapRemove();
    // // Print initial heap to check if it matches problem statement
    // // Could be it's own test, but hey, chill everyone
    // System.out.println(Arrays.toString(heap.getBackingArray()));

    // int returnedData = heap.remove();
    // assertEquals(0, returnedData);

    // String expected = "[null, 1, 3, 2, 4, 9, 5, 7, 6, 8, 10, null, null]";

    // String actual = Arrays.toString(heap.getBackingArray());
    // assertEquals(expected, actual);

    // assertEquals(10, heap.size());
    // }

}
