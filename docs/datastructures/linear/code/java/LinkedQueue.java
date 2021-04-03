public class LinkedQueue<T> {

    SinglyLinkedList<T> linkedList = new SinglyLinkedList<>();
    private int size;

    public void enqueue(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data to enqueue cannot be null.");
        }
        linkedList.addToBack(data);
        size++;
    }

    public T dequeue() {
        size--;
        return linkedList.removeFromFront();
    }

    public int size() {
        return linkedList.size();
    }

    public String toString() {
        return linkedList.toString();
    }
}
