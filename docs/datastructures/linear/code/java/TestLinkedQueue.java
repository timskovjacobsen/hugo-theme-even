import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.NoSuchElementException;

public class TestLinkedQueue {

    LinkedQueue<Integer> queue = new LinkedQueue<>();

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
        assertEquals("[<0, 1, 2>]", queue.toString());
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
        assertEquals("[<0, 1, 2, 3, 4, 5, 6, 7, 8>]", queue.toString());
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        assertEquals("[<3, 4, 5, 6, 7, 8>]", queue.toString());
        queue.enqueue(11);
        queue.enqueue(12);
        assertEquals("[<3, 4, 5, 6, 7, 8, 11, 12>]", queue.toString());
    }

    // ----- DEQUEUE TESTS -----
    @Test
    public void testDequeueNormal() {
        queue.enqueue(0);
        queue.enqueue(1);
        queue.enqueue(2);
        int dequeuedElement = queue.dequeue();
        assertEquals(2, queue.size());
        assertEquals("[<1, 2>]", queue.toString());
        assertEquals(0, dequeuedElement);
    }

    @Test(expected = NoSuchElementException.class)
    public void testDequeueEmpty() {
        queue.dequeue();
    }
}
