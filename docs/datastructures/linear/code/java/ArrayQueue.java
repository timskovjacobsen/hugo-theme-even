import java.util.NoSuchElementException;

public class ArrayQueue<T> {

    public static final int INITIAL_CAPACITY = 9;
    private T[] backingArray;
    private int front;
    private int size;

    public ArrayQueue() {
        // Cast Object array to generic type T array (not allowed directly in Java)
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
    }

    /**
     * Add data to the back of the queue. Time complexity: amortized O(1).
     *
     * @param data the data to add to the back of the queue
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void enqueue(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data to enqueue cannot be null.");
        }
        if (size == backingArray.length) {
            // No more space
            // Resize by creating new array of double capacity
            T[] backingArrayNew;
            backingArrayNew = (T[]) new Object[backingArray.length * 2];

            // Copy elements to the new array with data 0-aligned
            for (int i = 0; i < backingArray.length; i++) {
                // Index of current element to copy, modulo to account for wrap-around
                int idx = (front + i) % backingArray.length;

                backingArrayNew[i] = backingArray[idx];
            }
            // Update the backing array
            backingArray = backingArrayNew;

            // In the new array, the data is 0-aligned again
            // Set the front to index 0
            front = 0;
        }

        // Compute the index that is right behind the current back,
        // i.e. the spot to enqueue at
        int newBackIdx = (front + size) % backingArray.length;
        backingArray[newBackIdx] = data;
        size++;
    }

    /**
     * Remove and return the data from the front of the queue. Time complexity:
     * O(1).
     *
     * @return the data in the front of the queue
     * @throws java.util.NoSuchElementException if the queue is empty
     */
    public T dequeue() {
        if (size == 0) {
            // No elements in the queue
            throw new NoSuchElementException("The queue is empty.");
        }
        // Store the front element to be dequeued in a temp variable
        T dequeuedElement = backingArray[front];

        // Set the current front index to null since it will be dequeued
        backingArray[front] = null;

        // Compute the new front index (takes care of removing the last element as well)
        front = (front + 1) % backingArray.length;

        size--;
        return dequeuedElement;
    }

    /**
     * Return the backing array of the queue.
     *
     * @return the backing array of the queue
     */
    public T[] getBackingArray() {
        return backingArray;
    }

    /**
     * Return the size of the queue.
     *
     * @return the size of the queue
     */
    public int size() {
        return size;
    }

    public String toString() {
        String s = "[";
        for (int i = 0; i < backingArray.length; i++) {
            s = s + backingArray[i];

            if (i == backingArray.length - 1) {
                s = s + "";
            } else {
                s = s + ", ";
            }
        }
        s = s + "]";
        return s;
    }
}