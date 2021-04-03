/**
 * Node class used for use in Singly Linked Lists, Doubly Linked Lists or
 * Circularly Linked Lists.
 */
public class Node<T> {

    private T data;
    private Node<T> next;
    private Node<T> previous;

    /**
     * Constructs a new Node with the given data and pointers to the next previous
     * node.
     *
     * @param data     the data stored in the new node
     * @param next     the next node in the list
     * @param previous the previous node in the list
     */
    Node(T data, Node<T> next, Node<T> previous) {
        this.data = data;
        this.next = next;
        this.previous = previous;
    }

    /**
     * Alternative constructor for use when the previous node if not of interest,
     * e.g. for a Singly Linked List.
     * 
     * @param data
     * @param next
     */
    Node(T data, Node<T> next) {
        this(data, next, null);
    }

    /**
     * Alternative constructor with only data and pointers set to null
     *
     * @param data the data stored in the new node
     */
    Node(T data) {
        this(data, null, null);
    }

    /**
     * Get the data.
     *
     * @return the data stored in the node
     */
    T getData() {
        return data;
    }

    /**
     * Get the next node.
     *
     * @return the next node
     */
    Node<T> getNext() {
        return next;
    }

    /**
     * Set the next node.
     *
     * @param next the new next node
     */
    void setNext(Node<T> next) {
        this.next = next;
    }

    /**
     * Get the previous node.
     * 
     * @return the previous node
     */
    Node<T> getPrevious() {
        return previous;
    }

    /**
     * Set the previous node.
     * 
     * @param previous the new previous node
     */
    void setPrevious(Node<T> previous) {
        this.previous = previous;
    }
}