public class DoublyLinkedList<T> extends SinglyLinkedList<T> {

    /**
     * Add the element to the front of the list. Time complexity: O(1).
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    @Override
    public void addToFront(T data) {
        // Check for argument being null (method from superclass)
        checkArgument(data);

        // Create the node, set its next and previous pointers to the current
        // head and null, respectively
        Node<T> newNode = new Node<>(data, head, null);

        if (isEmpty()) {
            // Creating a one-element list makes that single element the tail
            tail = newNode;
        } else {
            // Set the current head's previous pointer to the new node
            head.setPrevious(newNode);
        }

        // Update the head to be he new node
        head = newNode;
        size++;
    }

    public T removeFromFront() {
        checkIfEmpty();

        T removedElement = head.getData();

        // Move the head pointer to the second element and set its
        // previous pointer to null
        head = head.getNext();
        head.setPrevious(null);

        size--;
        return removedElement;
    }

    // TODO: Add more methods

}
