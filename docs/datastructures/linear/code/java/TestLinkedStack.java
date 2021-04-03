import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TestLinkedStack {

    private LinkedStack<Integer> stack = new LinkedStack<>();

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
        String expected = "[<6, 9, 7>]";
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
