import java.util.NoSuchElementException;

public class MinHeap<T extends Comparable<? super T>> {

    public static final int INITIAL_CAPACITY = 13;

    private T[] backingArray;
    private int size;

    /**
     * Constructor that constructs a new MinHeap.
     *
     * Recall that Java does not allow for regular generic array creation, so
     * instead we cast a Comparable[] to a T[] to get the generic typing.
     */
    public MinHeap() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
    }

    /**
     * Alternative constructor to create a new MinHeap from an array, possibly
     * unsorted. The method uses the buildheap algorithm for creating the MinHeap.
     * 
     * @param arr the array to create the MinHeap from
     */
    public MinHeap(T[] arr) {
        // Call the constructor that has no arguments to initialize the backing array
        this();

        // Set the size acc. to the input array
        // This is used to calculate the starting index for the downheap operation
        size = arr.length;

        // Transfer the input array to the backing array, preserving first index empty
        // (as null)
        for (int i = 0; i < arr.length; i++) {
            backingArray[i + 1] = arr[i];
        }
        // Start the downheap operation from the last node that has children (size/2)
        int startIndex = size / 2;

        // Recursively downheap from the bottom and up to the root
        for (int i = startIndex; i >= 1; i--) {
            downHeap(i);
        }
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
            throw new IllegalArgumentException("Can't add null to the MinHeap.");
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
            // Swap data until a valid MinHeap is achieved
            int parentIdx = size / 2;
            upHeap(size, parentIdx);
        }
    }

    private void upHeap(int currentIdx, int parentIdx) {

        T current = backingArray[currentIdx];
        T root = backingArray[1];

        if (current != root) {

            T parent = backingArray[parentIdx];
            if (parent.compareTo(current) > 0) {
                // Parent data is larger than current data, swap them
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
     * Removes and returns the min item of the heap.
     *
     * Time complexity: O(log n).
     *
     * @return The data that was removed.
     * @throws java.util.NoSuchElementException If the heap is empty.
     */
    public T remove() {

        if (size() == 0) {
            throw new NoSuchElementException("Can't remove from an empty MinHeap.");
        }

        // Store the data to remove (i.e. the root's data) in a temp. variable
        T dataToReturn = backingArray[1];

        // Set the root's data (index 1) to the last node's data
        backingArray[1] = backingArray[size];

        // Delete the last node
        backingArray[size] = null;

        // Perform a down heap until a valid Min Heap is formed
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

            T minChild;
            int minChildIdx;
            // Find the smallest child
            if ((rightChild == null) || (leftChild.compareTo(rightChild) <= 0)) {
                // There is either no right child, or left child is smaller than right
                minChild = leftChild;
                minChildIdx = 2 * currentIdx;
            } else {
                // Right child is smaller than left child
                minChild = rightChild;
                minChildIdx = 2 * currentIdx + 1;
            }

            if (current.compareTo(minChild) > 0) {
                // Current data is larger than the smallest child, swap the data
                T temp = current;
                backingArray[currentIdx] = minChild;
                backingArray[minChildIdx] = temp;

                // Make recursive call with the data's new index as input, i.e. the
                // index of the child it was swapped with
                downHeap(minChildIdx);
            }
        }

        // Base cases:
        // 1. Current node has no children and therefore is a leaf node
        // 2. Current data is smaller than the smallest child's data, thus,
        // the Min Heap is valid and no more swaps are necessary
    }

    /**
     * Returns the backing array of the heap.
     *
     * @return The backing array of the MinHeap
     */
    public T[] getBackingArray() {
        return backingArray;
    }

    /**
     * Returns the size of the MinHeap.
     *
     * @return The size of the list
     */
    public int size() {
        return size;
    }
}