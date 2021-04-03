import java.util.NoSuchElementException;

public class SinglyLinkedList<T> {

    protected Node<T> head;
    protected Node<T> tail;
    protected int size;

    /**
     * Add the element to the front of the list. Time complexity: O(1).
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        // Check for argument being null
        checkArgument(data);

        // Create the node, set its pointer to the current head
        Node<T> newNode = new Node<>(data, head);
        head = newNode;

        // Creating a one-element list affects tail as well
        if (isEmpty()) {
            tail = newNode;
        }
        size++;
    }

    /**
     * Add the element to the back of the list. Time complexity: O(1).
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        checkArgument(data);

        // Create the node, set its pointer to null
        // (always the case for the tail for a singly linked list)
        Node<T> newNode = new Node<>(data, null);

        if (isEmpty()) {
            // List has a single node which must be head (and tail, see below)
            head = newNode;
        } else {
            // Point previous head to the new node
            tail.setNext(newNode);
        }
        // The new node is the new tail
        tail = newNode;
        size++;
    }

    /**
     * Remove and return the first data of the list. Time complexity: O(1).
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        // Raise exception if list is empty
        checkIfEmpty();

        // Store the data of the current head node (to be removed) in local
        // variable before reassigning head
        T removedHeadData = head.getData();

        if (size == 1) {
            tail = null;
            head = null;
        } else {
            // Move head to node number to, let the removed first node get garbage
            // collected by Java (unreachable)
            head = head.getNext();
        }
        size--;
        return removedHeadData;
    }

    /**
     * Remove and return the last data of the list. Time complexity: O(n).
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        checkIfEmpty();

        // Store the tail data in a temporary variable to avoid overwriting it
        T removedTailData = tail.getData();

        if (size == 1) {
            //
            tail = null;
            head = null;
        } else {
            // Get the second last element by traversing the list from head
            Node<T> current = head;
            while (current != null) {

                if (current.getNext().getNext() == null) {
                    // Found second last node

                    // Set the node as new tail and point to to null
                    tail = current;
                    tail.setNext(null);
                }
                current = current.getNext();
            }
        }
        size--;
        return removedTailData;
    }

    public void addAtIndex(int index, T data) {
        checkArgument(data);

        // Create the node to be inserted
        Node<T> newNode = new Node<>(data, null);

        // If insertion is at the front
        if (index == 0) {
            // Set the nodes next to the current head and update head
            newNode.setNext(head);
            head = newNode;
            if (isEmpty()) {
                // The new node is tail as well
                tail = newNode;
            }
        }
        // If insertion is at the back
        else if (index == size) {
            // Set the current tail to point to the new node and update tail
            tail.setNext(newNode);
            tail = newNode;
        }
        // If insertion is in the middle
        else {
            // Get the node that is currently at the index to insert the new data
            Node<T> existingNode = getNodeAtIndex(index - 1);

            // Set the new nodes next pointer to the existing nodes next
            newNode.setNext(existingNode.getNext());

            // Update the existing nodes next to point to the new node
            existingNode.setNext(newNode);
        }
        size++;
    }

    public T removeAtIndex(int index) {
        checkIfEmpty();

        // Get the node before the one that should be removed
        Node<T> nodeBefore = getNodeAtIndex(index - 1);

        Node<T> nodeToRemove;

        // If removing from a single-element list
        if (size == 1) {
            nodeToRemove = head;
            head = null;
            tail = null;
        } else {
            // If removing the last element
            if (index == size - 1) {
                // Get the tail as the node to remove and return
                nodeToRemove = tail;

                // Update the new tail and set its next pointer to null to garbage collect
                nodeBefore.setNext(null);
                tail = nodeBefore;
            }
            // If removing first element
            else if (index == 0) {
                // Define the head as the node to remove and return
                nodeToRemove = head;

                // Update the new head to the previous second node
                head = head.getNext();

                // Set the old head's next pointer to null to aid garbage collection
                nodeToRemove.setNext(null);
            }
            // If removing in the middle
            else {
                // Get the node to remove and return
                nodeToRemove = nodeBefore.getNext();

                // Update the next pointer of the node before the one that is removed
                nodeBefore.setNext(nodeToRemove.getNext());
            }
        }
        size--;
        return nodeToRemove.getData();
    }

    private Node<T> getNodeAtIndex(int index) {
        Node<T> current = head;

        int i = 0;
        // Advance down the list until the node at index is reached
        while (i < index) {
            current = current.getNext();
            i++;
        }
        return current;
    }

    protected void checkArgument(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Can't add null to the Singly Linked List.");
        }
    }

    protected void checkIfEmpty() {
        if (isEmpty()) {
            throw new NoSuchElementException("Can't remove from an empty list.");
        }
    }

    /**
     * Return the head node of the list.
     *
     * @return the node at the head of the list
     */
    public Node<T> getHead() {
        return head;
    }

    /**
     * Return the tail node of the list.
     *
     * @return the node at the tail of the list
     */
    public Node<T> getTail() {
        return tail;
    }

    /**
     * Return the size of the list.
     *
     * @return the size of the list
     */
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public String toString() {
        String s = "[";

        Node<T> current = getHead();
        while (current != null) {
            if (current == getHead()) {
                // Insert head signature before the element
                s = s + "<";
            }
            s = s + current.getData();

            if (current == getTail()) {
                // Insert tail signature after the element
                s = s + ">";
            }

            current = current.getNext();

            // Check for last item
            if (current != null) {
                s = s + ", ";
            } else {
                s = s + "";
            }
        }
        s = s + "]";

        return s;
    }
}