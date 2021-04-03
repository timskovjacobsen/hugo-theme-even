import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.NoSuchElementException;

public class TestArrayQueue {

    ArrayQueue<Integer> queue = new ArrayQueue<>();

    // ----- ENQUEUE TESTS -----
    @Test(expected = IllegalArgumentException.class)
    public void testNullArgRaisesException() {
        queue.enqueue(null);
    }

    @Test
    public void testEnqueueEmpty() {
        queue.enqueue(0);
        queue.enqueue(1);
        queue.enqueue(2);
        assertEquals(3, queue.size());
        assertEquals("[0, 1, 2, null, null, null, null, null, null]", queue.toString());
    }

    @Test
    public void testEnqueueWrapAround() {
        queue.enqueue(0);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
        queue.enqueue(7);
        queue.enqueue(8);
        assertEquals("[0, 1, 2, 3, 4, 5, 6, 7, 8]", queue.toString());
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        assertEquals("[null, null, null, 3, 4, 5, 6, 7, 8]", queue.toString());
        queue.enqueue(11);
        queue.enqueue(12);
        assertEquals("[11, 12, null, 3, 4, 5, 6, 7, 8]", queue.toString());
    }

    @Test
    public void testEnqueueResizeBackingArray() {
        queue.enqueue(0);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
        queue.enqueue(7);
        queue.enqueue(8);
        queue.enqueue(9);
        Object[] backingArray = queue.getBackingArray();
        assertEquals(18, backingArray.length);
        assertEquals("[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, null, null, null, null, null, null, null, null]",
                queue.toString());
    }

    @Test
    public void testEnqueueStringsResizeBackingArray() {
        ArrayQueue<String> queue = new ArrayQueue<>();
        // Front index should be 3 to match auto-grader test, create that
        queue.enqueue("toBeDequeued1");
        queue.enqueue("toBeDequeued2");
        queue.enqueue("toBeDequeued3");
        queue.enqueue("0a");
        queue.enqueue("1a");
        queue.enqueue("2a");
        queue.enqueue("3a");
        queue.enqueue("4a");
        queue.enqueue("5a");
        // Dequeue 3 elements to make front index 3
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        assertEquals("[null, null, null, 0a, 1a, 2a, 3a, 4a, 5a]", queue.toString());
        // Enqueue elements so queue is full and front is at index 3
        queue.enqueue("6a");
        queue.enqueue("7a");
        queue.enqueue("8a");
        assertEquals("[6a, 7a, 8a, 0a, 1a, 2a, 3a, 4a, 5a]", queue.toString());
        // Enqueue an element that will trigger a resize
        queue.enqueue("9a");

        System.out.println(queue.toString());
        Object[] backingArray = queue.getBackingArray();
        assertEquals(18, backingArray.length);
        assertEquals("[0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a, 9a, null, null, null, null, null, null, null, null]",
                queue.toString());
    }

    // ----- DEQUEUE TESTS -----
    @Test
    public void testDequeueNormal() {
        queue.enqueue(0);
        queue.enqueue(1);
        queue.enqueue(2);
        int dequeuedElement = queue.dequeue();
        Object[] backingArray = queue.getBackingArray();
        assertEquals(9, backingArray.length);
        assertEquals("[null, 1, 2, null, null, null, null, null, null]", queue.toString());
        assertEquals(0, dequeuedElement);
    }

    @Test(expected = NoSuchElementException.class)
    public void testDequeueEmpty() {
        queue.dequeue();
    }
}
