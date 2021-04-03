public class ArrayStack<T> {

    public static final int INITIAL_CAPACITY = 9;
    private T[] backingArray;
    private int size;

    public ArrayStack() {
        // Cast Object array to generic type T array (not allowed directly in Java)
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
    }

    /**
     * Add and element to the top of the stack
     * 
     * @param data the data to add
     */
    public void push(T data) {

        if (size == backingArray.length) {
            // Backing array must be resized to hold the new element
            T[] backingArrayNew = (T[]) new Object[backingArray.length * 2];

            // Copy existing elements to the new array
            for (int i = 0; i < size; i++) {
                backingArrayNew[i] = backingArray[i];
            }
            backingArray = backingArrayNew;
        }
        // Add new element to the back (top) of the Stack
        backingArray[size] = data;
        size++;
    }

    /**
     * Remove and return the top element of the Stack.
     * 
     * @return the top element of the Stack
     */
    public T pop() {
        // Return null if popping from an empty stack
        if (size == 0) {
            return null;
        }
        // Save element to pop in a temp variable, reset to null and adjust size
        T poppedElement = backingArray[size - 1];
        backingArray[size - 1] = null;
        size--;
        return poppedElement;
    }

    /**
     * Return (but don't remove) the element at the top of the stack
     * 
     * @return the element at the top of the stack
     */
    public T peek() {
        if (size == 0) {
            return null;
        }
        return backingArray[size - 1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public String toString() {
        String s = "[";
        for (int i = 0; i < backingArray.length; i++) {
            s = s + backingArray[i];
            if (i + 1 != backingArray.length) {
                s = s + ", ";
            }
        }
        s = s + "]";
        return s;
    }
}
