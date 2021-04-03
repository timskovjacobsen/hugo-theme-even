import java.util.NoSuchElementException;

public class ArrayList<T> {

    public static final int INITIAL_CAPACITY = 9;
    private T[] backingArray;
    private int size;

    public ArrayList() {
        // Cast Object array to generic type T array (not allowed directly in Java)
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
    }

    /**
     * Add data to the front of the list. Time complexity: O(n).
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Can't add 'null' to ArrayList.");
        }

        if (size == 0) {
            backingArray[0] = data;
        } else {
            if (size == backingArray.length) {
                // ArrayList is full and must be resized
                // Create a new array with double capacity
                T[] backingArrayNew = (T[]) new Object[backingArray.length * 2];

                // Set the new first element before copying the existing elements
                backingArrayNew[0] = data;

                // Copy all existing elements to the new array with indices incremented
                // by one
                for (int i = 0; i < backingArray.length; i++) {
                    backingArrayNew[i + 1] = backingArray[i];
                }
                // Update the backing array to the new one
                backingArray = backingArrayNew;

            } else {
                // ArrayList has capacity left
                // Shift all indices one up
                // Iterate in reverse to avoid overwriting data
                // NOTE: backingArray[size] will not exceed the bounds as capacity >= size
                for (int i = size; i > 0; i--) {
                    backingArray[i] = backingArray[i - 1];
                }
                // Add the new value to the front
                backingArray[0] = data;
            }
        }
        size++;
    }

    /**
     * Add data to the back of the list. Time complexity: amortized O(1).
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Can't add 'null' to ArrayList.");
        }
        if (size == 0) {
            backingArray[0] = data;
        } else {
            if (size == backingArray.length) {
                // Not enough capacity, resize (NOTE: rare case causing armotization)
                // Create new backing array with double capacity
                T[] backingArrayNew = (T[]) new Object[backingArray.length * 2];

                // Copy all elements to the new array with unchanged indices
                for (int i = 0; i < size; i++) {
                    backingArrayNew[i] = backingArray[i];
                }
                // Update the backing array to the new extended one
                backingArray = backingArrayNew;
            }
            // Add data at end of array elements and update size
            backingArray[size] = data;
        }
        size++;
    }

    /**
     * Remove and return the first data in the list. Time complexity: O(n).
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        checkIfSomethingToRemove();

        // Get the first element that is to be returned later, avoids it being
        // overwritten when shifting the indices
        T first = backingArray[0];

        // Shift all indices one to the left so the array list is zero-aligned
        for (int i = 0; i < size - 1; i++) {
            backingArray[i] = backingArray[i + 1];
        }
        // Set the last element to be null
        backingArray[size - 1] = null;

        size--;
        return first;
    }

    /**
     * Remove and return the last data of the list. Time complexity: O(1).
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        checkIfSomethingToRemove();

        T last = backingArray[size - 1];
        backingArray[size - 1] = null;
        size--;
        return last;
    }

    /**
     * Insert data at an index position Time complexity: O(n).
     * 
     * @param data  the data to be inserted
     * @param index the index at which to insert the data
     */
    public void addAtIndex(int index, T data) {
        if (size == backingArray.length) {
            // ArrayList is full and must be resized
            // Create a new array with double capacity
            T[] backingArrayNew = (T[]) new Object[backingArray.length * 2];

            // Copy all existing elements with index lower than the index to be added
            // These are copied directly to the same indices
            for (int i = 0; i < index; i++) {
                backingArrayNew[i] = backingArray[i];
            }
            // Insert the inputted data into the desired index
            backingArrayNew[index] = data;

            // Copy remaining elements with their indices incremented by one
            for (int i = index; i < backingArray.length; i++) {
                backingArrayNew[i + 1] = backingArray[i];
            }
            // Update the backing array to the new one
            backingArray = backingArrayNew;
        } else {
            // The data can fit in the current backing array
            // Iterate in reverse from back down to the index and push indices one up
            for (int i = size; i > index; i--) {
                backingArray[i] = backingArray[i - 1];
            }
            // Add the data at the given index
            backingArray[index] = data;
        }
        size++;
    }

    /**
     * Remove and return the element at an index. Time complexity: O(n).
     * 
     * @param index at which to remove and return the element
     * @return the removed element at the given index
     */
    public T removeAtIndex(int index) {
        // Retrieve the element to remove
        T removedElement = backingArray[index];

        // Decrement all indices that are larger than index
        for (int i = index; i < size - 1; i++) {
            backingArray[i] = backingArray[i + 1];
        }
        // Set the last element to null
        backingArray[size - 1] = null;

        size--;
        return removedElement;
    }

    /**
     * Insert new data at a given index and return the replaced data. Time
     * complexity: O(1).
     * 
     * @param index the index to replace the data
     * @param data  the data to insert in place of the existing
     * @return the replaced data at the given index
     */
    public T set(int index, T data) {
        checkIfSomethingToRemove();
        T elementToReturn = backingArray[index];
        backingArray[index] = data;
        return elementToReturn;
    }

    private void checkIfSomethingToRemove() {
        if (size == 0) {
            throw new NoSuchElementException("The ArrayList is empty. Thus, nothing no remove.");
        }
    }

    public T[] getBackingArray() {
        return backingArray;
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