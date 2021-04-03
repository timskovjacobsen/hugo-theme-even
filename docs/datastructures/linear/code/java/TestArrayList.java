import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TestArrayList {

    private ArrayList<Integer> list = new ArrayList<>();

    @Test
    public void testAddToBack() {
        list.addToBack(1);
        String expected = "[1, null, null, null, null, null, null, null, null]";
        String actual = list.toString();
        assertEquals(expected, actual);
        assertEquals(1, list.size());
    }

    @Test
    public void testRemoveFromFrontEmpty() {
        list.addToFront(0);
        list.removeFromFront();
        String expected = "[null, null, null, null, null, null, null, null, null]";
        String actual = list.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testRemoveFromFrontFull() {
        list.addToFront(0);
        list.addToFront(0);
        list.addToFront(0);
        list.addToFront(0);
        list.addToFront(0);
        list.addToFront(0);
        list.addToFront(0);
        list.addToFront(0);
        list.addToFront(0);
        list.removeFromFront();
        String expected = "[0, 0, 0, 0, 0, 0, 0, 0, null]";
        String actual = list.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testRemoveFromFrontHalfFull() {
        list.addToFront(4);
        list.addToFront(4);
        list.addToFront(4);
        list.addToFront(4);
        list.removeFromFront();
        String expected = "[4, 4, 4, null, null, null, null, null, null]";
        String actual = list.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testAddAtIndexInMiddle() {
        list.addToBack(0);
        list.addToBack(1);
        list.addToBack(3);
        list.addToBack(4);
        list.addAtIndex(2, 2);
        String expected = "[0, 1, 2, 3, 4, null, null, null, null]";
        String actual = list.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testAddAtIndexInBeginning() {
        list.addToBack(1);
        list.addToBack(2);
        list.addToBack(3);
        list.addToBack(4);
        list.addAtIndex(0, 0);
        String expected = "[0, 1, 2, 3, 4, null, null, null, null]";
        String actual = list.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testAddAtIndexInBeginningOfEmptyList() {
        list.addAtIndex(0, 0);
        String expected = "[0, null, null, null, null, null, null, null, null]";
        String actual = list.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testAddAtIndexWithResize() {
        list.addToBack(0);
        list.addToBack(1);
        list.addToBack(2);
        list.addToBack(3);
        list.addToBack(4);
        list.addToBack(5);
        list.addToBack(6);
        list.addToBack(7);
        list.addToBack(8); // Array is full
        list.addAtIndex(2, 22); // Add to trigger the resize
        String expected = "[0, 1, 22, 2, 3, 4, 5, 6, 7, 8, null, null, null, null, null, null, null, null]";
        String actual = list.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testRemoveAtIndexInMiddle() {
        list.addToBack(0);
        list.addToBack(1);
        list.addToBack(2);
        list.addToBack(3);
        list.addToBack(4);
        list.removeAtIndex(2);
        String expected = "[0, 1, 3, 4, null, null, null, null, null]";
        String actual = list.toString();
        assertEquals(expected, actual);
    }
}