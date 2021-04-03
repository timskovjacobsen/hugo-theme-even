import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class TestMinHeap {

    public MinHeap<Integer> initialMinHeapAdd() {
        MinHeap<Integer> heap = new MinHeap<>();
        heap.add(1);
        heap.add(2);
        heap.add(3);
        heap.add(4);
        heap.add(5);
        heap.add(6);
        heap.add(7);
        heap.add(8);
        heap.add(9);
        return heap;
    }

    @Test
    public void testAdd() {

        MinHeap<Integer> heap = initialMinHeapAdd();
        heap.add(0);

        String actual = Arrays.toString(heap.getBackingArray());
        String expected = "[null, 0, 1, 3, 4, 2, 6, 7, 8, 9, 5, null, null]";
        assertEquals(expected, actual);
    }

    @Test
    public void testAddWithFullArray() {
        MinHeap<Integer> heap = initialMinHeapAdd();
        heap.add(0);
        heap.add(10);
        heap.add(11);
        heap.add(12);
        // Array is full now

        // Add another item to trigger a resize
        heap.add(13);

        String actual = Arrays.toString(heap.getBackingArray());
        String expected = "[null, 0, 1, 3, 4, 2, 6, 7, 8, 9, 5, 10, 11, 12, 13, null, null, null, null, null, null, null, null, null, null, null]";
        assertEquals(expected, actual);

    }

    public MinHeap<Integer> initialMinHeapRemove() {
        MinHeap<Integer> heap = new MinHeap<>();
        heap.add(0);
        heap.add(1);
        heap.add(2);
        heap.add(4);
        heap.add(3);
        heap.add(5);
        heap.add(7);
        heap.add(6);
        heap.add(8);
        heap.add(9);
        heap.add(10);
        return heap;
    }

    @Test
    public void testRemove() {
        MinHeap<Integer> heap = initialMinHeapRemove();
        // Print initial heap to check if it matches problem statement
        // Could be it's own test, but hey, chill everyone
        System.out.println(Arrays.toString(heap.getBackingArray()));

        int returnedData = heap.remove();
        assertEquals(0, returnedData);

        String expected = "[null, 1, 3, 2, 4, 9, 5, 7, 6, 8, 10, null, null]";

        String actual = Arrays.toString(heap.getBackingArray());
        assertEquals(expected, actual);

        assertEquals(10, heap.size());
    }

}
