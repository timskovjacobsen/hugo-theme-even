// package main.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import java.util.NoSuchElementException;

public class TestSinglyLinkedList {

    SinglyLinkedList<Integer> list = new SinglyLinkedList<>();

    @Test
    public void testCreateEmptyList() {
        // Head and tail are initially null
        Object headInit = list.getHead();
        Object tailInit = list.getTail();
        String listString = list.toString();

        assertEquals(0, list.size());
        assertEquals(null, headInit);
        assertEquals(null, tailInit);
        assertEquals("[]", listString);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddToFrontIllegalArgumentExceptionWithNullArg() {
        list.addToFront(null);
    }

    @Test
    public void testAddToFrontOneElement() {
        list.addToFront(1);
        String listString = list.toString();
        assertEquals("[<1>]", listString);
        assertEquals(1, list.size());

    }

    @Test
    public void testAddToFrontManyElements() {
        list.addToFront(4);
        list.addToFront(3);
        list.addToFront(2);
        list.addToFront(1);
        String listString = list.toString();
        System.out.println(listString);
        System.out.println("head: " + list.getHead().getData());
        System.out.println("tail: " + list.getTail().getData());

        Object actualHeadData = list.getHead().getData();
        Object actualTailData = list.getTail().getData();
        assertEquals(1, actualHeadData);
        assertEquals(4, actualTailData);
        assertEquals(4, list.size());

        // Tests string representation
        assertEquals("[<1, 2, 3, 4>]", listString);
    }

    @Test
    public void testAddToBackEmptyList() {
        list.addToBack(1);
        String listString = list.toString();

        // Head and tail should be equal
        assertEquals(list.getHead(), list.getTail());
        assertEquals("[<1>]", listString);
        assertEquals(1, list.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddToBackIllegalArgumentExceptionWithNullArg() {
        list.addToBack(null);
    }

    @Test
    public void testAddToBackManyElements() {
        list.addToBack(1);
        list.addToBack(2);
        list.addToBack(3);
        list.addToBack(4);
        String listString = list.toString();

        assertEquals("[<1, 2, 3, 4>]", listString);
        assertEquals(4, list.size());
    }

    @Test
    public void testRemoveFromFrontWithSingleElement() {
        list.addToBack(1);
        list.removeFromFront();
        String listString = list.toString();

        assertEquals("[]", listString);

        assertEquals(null, list.getHead());
        assertEquals(null, list.getTail());
    }

    @Test
    public void testRemoveFromFrontMultipleElements() {
        list.addToBack(1);
        list.addToBack(2);
        list.addToBack(3);
        list.addToBack(4);
        Object removedNodeData = list.removeFromFront();

        assertEquals("[<2, 3, 4>]", list.toString());
        assertEquals(1, removedNodeData);
        Object actualHead = list.getHead().getData();
        Object actualTail = list.getTail().getData();
        assertEquals(2, actualHead);
        assertEquals(4, actualTail);
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveFromFrontWithEmptyList() {
        list.removeFromFront();
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveFromBackWithEmptyList() {
        list.removeFromBack();
    }

    @Test
    public void testRemoveFromBackWithSingleElement() {
        list.addToBack(1);
        Object removedNode = list.removeFromBack();

        assertEquals("[]", list.toString());
        assertEquals(1, removedNode);
        assertEquals(0, list.size());

        // NOTE: Don't .getData() from head and tail, since they are null
        Object actualHead = list.getHead();
        Object actualTail = list.getTail();
        assertEquals(null, actualHead);
        assertEquals(null, actualTail);
    }

    @Test
    public void testRemoveFromBackMultipleElements() {
        list.addToBack(1);
        list.addToBack(2);
        list.addToBack(3);
        list.addToBack(4);
        Object removedNode = list.removeFromBack();

        assertEquals("[<1, 2, 3>]", list.toString());
        assertEquals(4, removedNode);
        assertEquals(3, list.size());

        Object actualHead = list.getHead().getData();
        Object actualTail = list.getTail().getData();
        assertEquals(1, actualHead);
        assertEquals(3, actualTail);
    }

    @Test
    public void testAddAtIndexEmptyList() {
        list.addAtIndex(0, 0);

        assertEquals("[<0>]", list.toString());
        assertEquals(1, list.size());

        Object actualHead = list.getHead().getData();
        Object actualTail = list.getTail().getData();
        assertEquals(0, actualHead);
        assertEquals(0, actualTail);
    }

    @Test
    public void testAddAtIndexAtFrontExistingElements() {
        list.addToBack(1);
        list.addToBack(2);
        list.addToBack(3);
        list.addAtIndex(0, 0);

        assertEquals("[<0, 1, 2, 3>]", list.toString());
        assertEquals(4, list.size());

        Object actualHead = list.getHead().getData();
        Object actualTail = list.getTail().getData();
        assertEquals(0, actualHead);
        assertEquals(3, actualTail);
    }

    @Test
    public void testAddAtIndexAtBackExistingElements() {
        list.addToBack(0);
        list.addToBack(1);
        list.addToBack(2);
        list.addAtIndex(3, 3);

        assertEquals("[<0, 1, 2, 3>]", list.toString());
        assertEquals(4, list.size());

        Object actualHead = list.getHead().getData();
        Object actualTail = list.getTail().getData();
        assertEquals(0, actualHead);
        assertEquals(3, actualTail);
    }

    @Test
    public void testAddAtIndexInMiddle() {
        list.addToBack(0);
        list.addToBack(1);
        list.addToBack(3);
        list.addToBack(4);
        list.addAtIndex(2, 2);

        assertEquals("[<0, 1, 2, 3, 4>]", list.toString());
        assertEquals(5, list.size());
        Object actualHead = list.getHead().getData();
        Object actualTail = list.getTail().getData();
        assertEquals(0, actualHead);
        assertEquals(4, actualTail);
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveAtIndexEmptyList() {
        list.removeAtIndex(0);
    }

    @Test
    public void testRemoveAtIndexInMiddle() {
        list.addToBack(0);
        list.addToBack(1);
        list.addToBack(2);
        list.addToBack(3);
        int removedElement = list.removeAtIndex(2);

        assertEquals("[<0, 1, 3>]", list.toString());
        assertEquals(3, list.size());
        assertEquals(2, removedElement);
        Object actualHead = list.getHead().getData();
        Object actualTail = list.getTail().getData();
        assertEquals(0, actualHead);
        assertEquals(3, actualTail);
    }

    @Test
    public void testRemoveAtIndexLastElement() {
        list.addToBack(0);
        list.addToBack(1);
        list.addToBack(2);
        list.addToBack(3);
        int removedElement = list.removeAtIndex(3);

        assertEquals("[<0, 1, 2>]", list.toString());
        assertEquals(3, list.size());
        assertEquals(3, removedElement);
        Object actualHead = list.getHead().getData();
        Object actualTail = list.getTail().getData();
        assertEquals(0, actualHead);
        assertEquals(2, actualTail);
    }

    @Test
    public void testRemoveAtIndexFirstElement() {
        list.addToBack(0);
        list.addToBack(1);
        list.addToBack(2);
        list.addToBack(3);
        int removedElement = list.removeAtIndex(0);

        assertEquals("[<1, 2, 3>]", list.toString());
        assertEquals(3, list.size());
        assertEquals(0, removedElement);
        Object actualHead = list.getHead().getData();
        Object actualTail = list.getTail().getData();
        assertEquals(1, actualHead);
        assertEquals(3, actualTail);
    }

    @Test
    public void testRemoveAtIndexWithSingleElement() {
        list.addToBack(0);
        int removedElement = list.removeAtIndex(0);

        assertEquals("[]", list.toString());
        assertEquals(0, list.size());
        assertEquals(0, removedElement);
        assertEquals(null, list.getHead());
        assertEquals(null, list.getTail());
    }
}