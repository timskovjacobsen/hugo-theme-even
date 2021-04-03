import static org.junit.Assert.assertEquals;

import java.util.NoSuchElementException;

import org.junit.Test;

public class TestDoublyLinkedList {

    DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

    @Test
    public void addToFrontEmptyList() {
        list.addToFront(0);

        assertEquals(1, list.size());
        assertEquals("[<0>]", list.toString());
        Node head = list.getHead();
        Node tail = list.getTail();
        assertEquals(0, head.getData());
        assertEquals(0, tail.getData());
        // All four next and previous pointers should be null
        assertEquals(null, head.getPrevious());
        assertEquals(null, head.getNext());
        assertEquals(null, tail.getNext());
        assertEquals(null, tail.getPrevious());
    }

    @Test
    public void addToFrontExistingElements() {
        list.addToFront(3);
        list.addToFront(2);
        list.addToFront(1);
        list.addToFront(0);

        assertEquals(4, list.size());
        assertEquals("[<0, 1, 2, 3>]", list.toString());
        Node head = list.getHead();
        Node tail = list.getTail();
        assertEquals(0, head.getData());
        assertEquals(3, tail.getData());
        assertEquals(null, head.getPrevious());
        assertEquals(1, head.getNext().getData());
        assertEquals(null, tail.getNext());
        assertEquals(2, tail.getPrevious().getData());
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveFromFrontEmptyList() {
        list.removeFromFront();
    }

    @Test
    public void testRemoveFromFrontExistingElements() {
        list.addToFront(3);
        list.addToFront(2);
        list.addToFront(1);
        list.addToFront(0);
        list.removeFromFront();

        assertEquals(3, list.size());
        assertEquals("[<1, 2, 3>]", list.toString());
        Node head = list.getHead();
        Node tail = list.getTail();
        assertEquals(null, head.getPrevious());
        assertEquals(2, head.getNext().getData());
        assertEquals(2, tail.getPrevious().getData());
        assertEquals(null, tail.getNext());
    }

}
