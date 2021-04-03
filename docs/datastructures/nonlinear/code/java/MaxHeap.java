import java.util.NoSuchElementException;

public class MaxHeap<T extends Comparable<? super T>> {

    public static final int INITIAL_CAPACITY = 13;

    private T[] backingArray;
    private int size;

    /**
     * This is the constructor that constructs a new MaxHeap.
     *
     * Recall that Java does not allow for regular generic array creation, so
     * instead we cast a Comparable[] to a T[] to get the generic typing.
     */
    public MaxHeap() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
    }

    /**
     * Adds an item to the heap.
     * 
     * If the backing array is full (except for index 0) the capacity is doubled.
     *
     * Time complexity: amortized O(log n).
     *
     * @param data The data to add.
     * @throws java.lang.IllegalArgumentException If the data is null.
     */
    public void add(T data) {

        if (data == null) {
            throw new IllegalArgumentException("Can't add null to the MaxHeap.");
        }

        // Note: Size must be incremented as the first step to accommodate for
        // index 0 to be ignored. Size will always be > 1 and new
        // elements are inserted at index `size`
        size++;

        if (size == backingArray.length) {
            // Resize is necessary, double capacity and copy elements
            T[] backingArrayNew = (T[]) new Comparable[backingArray.length * 2];
            for (int i = 0; i < size; i++) {
                backingArrayNew[i] = backingArray[i];
            }
            backingArray = backingArrayNew;
        }

        // Insert data at the end of the array, i.e. as the right-most leaf node in the
        // bottom level
        backingArray[size] = data;

        if (size > 1) {
            // Swap data until a valid MaxHeap is achieved
            int parentIdx = size / 2;
            upHeap(size, parentIdx);
        }
    }

    private void upHeap(int currentIdx, int parentIdx) {

        T current = backingArray[currentIdx];
        T root = backingArray[1];

        if (current != root) {

            T parent = backingArray[parentIdx];
            if (parent.compareTo(current) < 0) {

                // Current data is larger than parent data, swap them
                T tempSource = backingArray[currentIdx];
                backingArray[currentIdx] = parent;
                backingArray[parentIdx] = tempSource;

                // The previous parent index now becomes the current index
                // The new index to parent with is calculated by integer division
                int newParentIdx = parentIdx / 2;
                upHeap(parentIdx, newParentIdx);
            }
        }
        // Base cases:
        // 1. Root is reached
        // 2. current data is less than its parent, no swap as order property
        // is fulfilled
    }

    /**
     * Removes and returns the max item of the heap.
     *
     * Time complexity: O(log n)
     *
     * @return The data that was removed.
     */
    public T remove() {
        if (size() == 0) {
            throw new NoSuchElementException("Can't remove from an empty MaxHeap.");
        }
        // Store the data to remove (i.e. the root's data) in a temp. variable
        T dataToReturn = backingArray[1];

        // Set the root's data (index 1) to the last node's data
        backingArray[1] = backingArray[size];

        // Delete the last node
        backingArray[size] = null;

        // Perform a down heap until a valid Max Heap is formed
        int rootIdx = 1;
        downHeap(rootIdx);
        size--;

        return dataToReturn;
    }

    private void downHeap(int currentIdx) {

        if (2 * currentIdx < size) {
            // The current node has children, i.e. it's not a leaf node
            T current = backingArray[currentIdx];
            T leftChild = backingArray[2 * currentIdx];
            T rightChild = backingArray[2 * currentIdx + 1];

            T maxChild;
            int maxChildIdx;
            // Find the largest child
            if ((rightChild == null) || (leftChild.compareTo(rightChild) >= 0)) {
                // There is either no right child, or left child is larger than right
                maxChild = leftChild;
                maxChildIdx = 2 * currentIdx;
            } else {
                // Right child is larger than left child
                maxChild = rightChild;
                maxChildIdx = 2 * currentIdx + 1;
            }

            if (current.compareTo(maxChild) < 0) {
                // Current data is smaller than the largest child, swap the data
                T temp = current;
                backingArray[currentIdx] = maxChild;
                backingArray[maxChildIdx] = temp;

                // Make recursive call with the data's new index as input, i.e. the
                // index of the child it was swapped with
                downHeap(maxChildIdx);
            }
        }

        // Base cases:
        // 1. Current node has no children and therefore is a leaf node
        // 2. Current data is smaller than the smallest child's data, thus,
        // the Max Heap is valid and no more swaps are necessary
    }

    /**
     * Returns the backing array of the heap.
     * 
     * @return The backing array of the list
     */
    public T[] getBackingArray() {
        return backingArray;
    }

    /**
     * Returns the size of the heap.
     *
     * @return The size of the list
     */
    public int size() {
        return size;
    }
}