import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TestArrayStack {

    private ArrayStack<Integer> stack = new ArrayStack<>();

    @Test
    public void testPushPopPeek() {
        stack.push(5);
        stack.push(3);
        stack.pop();
        stack.pop();
        stack.push(7);
        stack.push(9);
        stack.push(4);
        stack.pop();
        stack.push(6);
        stack.push(8);
        int poppedElement = stack.pop();

        int peekedElement = stack.peek();
        String expected = "[7, 9, 6, null, null, null, null, null, null]";
        String actual = stack.toString();
        assertEquals(expected, actual);
        assertEquals(8, poppedElement);
        assertEquals(6, peekedElement);
        assertEquals(3, stack.size());
    }

    @Test
    public void testPopWithEmptyStack() {
        assertEquals(null, stack.pop());
    }
}
