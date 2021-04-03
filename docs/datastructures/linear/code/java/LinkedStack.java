public class LinkedStack<T> {

    SinglyLinkedList<T> linkedList = new SinglyLinkedList<>();

    /**
     * Add an element to the top of the Stack.
     * 
     * @param data the data to add to the top of the Stack
     */
    public void push(T data) {
        linkedList.addToFront(data);
    }

    /**
     * Remove and return the top element of the Stack.
     * 
     * @return the top element of the Stack
     */
    public T pop() {
        if (linkedList.size() == 0) {
            return null;
        }
        return linkedList.removeFromFront();
    }

    /**
     * Return (but don't remove) the top element of the Stack.
     * 
     * @return the top element of the Stack
     */
    public T peek() {
        return linkedList.getHead().getData();
    }

    /**
     * Return the size of the Stack
     * 
     * @return the size of the Stack
     */
    public int size() {
        return linkedList.size();
    }

    public String toString() {
        return linkedList.toString();
    }
}
