import java.util.List;
import java.util.ArrayList;

import java.util.NoSuchElementException;

public class BST<T extends Comparable<? super T>> {

    private BSTNode<T> root;
    private int size;

    public int height() {
        return height(root);
    }

    private int height(BSTNode<T> root) {

        if (root == null) {
            return 0;
        }
        BSTNode<T> leftChild = root.getLeft();
        BSTNode<T> rightChild = root.getRight();
        return Math.max(height(leftChild), height(rightChild)) + 1;
    }

    /**
     * Adds the data to the tree.
     *
     *
     * The new data is added as a leaf node in the tree. The tree is traverses to
     * find the appropriate location. If the data is already in the tree, then
     * nothing is done.
     *
     * Time complexity: - Best case: O(log n) - Average case: O(log n) - Worst case:
     * O(n)
     *
     * @param data The data to add to the tree.
     * @throws java.lang.IllegalArgumentException If data is null.
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }

        // Call private add-method with the BSTs root as the first input to
        root = addBackend(root, data);
    }

    private BSTNode<T> addBackend(BSTNode<T> current, T data) {
        // 1. base case - Node is not found
        if (current == null) {
            // If the current node is null, this is the location to add the new data.
            // This might be the root if the tree is empty, or a leaf if we have
            // traversed to the end of a "path"
            size++;
            return new BSTNode<>(data);
        }

        // Pointer reinforcement logic
        boolean isCurrentGreaterThanData = current.getData().compareTo(data) > 0;
        boolean isCurrentLessThanData = current.getData().compareTo(data) < 0;
        if (isCurrentGreaterThanData) {
            //
            current.setLeft(addBackend(current.getLeft(), data));

        } else if (isCurrentLessThanData) {
            //
            current.setRight(addBackend(current.getRight(), data));
        }

        // NOTE: No node is added to the tree here, we are continuing the
        // traversal to find where to add the node and returning the nodes we traverse
        // in order to "detach" or "reinforce" their pointers.
        // Detachment is when traversing down prior to adding, reinforcing is when
        // traversing up to relink the detached pointers.
        return current;
    }

    /**
     * Removes and returns the data from the tree matching the given parameter.
     *
     * There are three cases to consider: 1: The node containing the data is a leaf
     * (no children). In this case, it is removes. 2: The node containing the data
     * has one child. In this case, we simply replace it with its child. 3: The node
     * containing the data has 2 children. The SUCCESSOR is used to replace the
     * data.
     *
     * Time complexity: - Best case: O(log n) - Average case: O(log n) - Worst case:
     * O(n)
     *
     * @param data The data to remove.
     * @return The data that was removed.
     * @throws java.lang.IllegalArgumentException If data is null.
     * @throws java.util.NoSuchElementException   If the data is not in the tree.
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
        // Create a dummy node to pass into the recursive stack. Once the node to
        // remove has been found (if it exists), the removed nodes data is copied to
        // the dummy nodes data. This way, the data is available after the recursive
        // stack is empty and the removed data can be returned as the dummy node's data
        BSTNode<T> removeDummy = new BSTNode<>(null);

        root = removeBackend(root, data, removeDummy);
        size--;
        return removeDummy.getData();
    }

    private BSTNode<T> removeBackend(BSTNode<T> current, T data, BSTNode<T> removeDummy) {
        if (current == null) {
            // The data to remove was not found and thus, does not exist in the tree
            throw new NoSuchElementException();
        }

        boolean isCurrentGreaterThanData = current.getData().compareTo(data) > 0;
        boolean isCurrentLessThanData = current.getData().compareTo(data) < 0;

        if (isCurrentGreaterThanData) {
            // Reinforce the left child to be the returned value from a recursive call
            // with itself as root
            current.setLeft(removeBackend(current.getLeft(), data, removeDummy));

        } else if (isCurrentLessThanData) {
            current.setRight(removeBackend(current.getRight(), data, removeDummy));

        } else {
            // Set the dummy nodes data to the removed node's (current node's) data
            // This is the data to be returned from the public user-facing method
            removeDummy.setData(current.getData());

            // 2. base case - Node is found and removed
            if (data.equals(current.getData())) {
                // There are four cases that could be present for the data to remove

                BSTNode<T> leftChild = current.getLeft();
                BSTNode<T> rightChild = current.getRight();

                if (leftChild != null && rightChild != null) {
                    // a. The node has two children
                    // Remove the successor by creating a dummy node
                    BSTNode<T> successorDummy = new BSTNode<>(null);
                    current.setRight(removeSuccessor(current.getRight(), successorDummy));

                    // Copy the data from the dummy node into the node where the data is
                    // to be removed
                    current.setData(successorDummy.getData());

                } else if (leftChild != null) {
                    // b1. The node has one child, the left
                    // Return the left child. This sets the parent's child to it's
                    // previous grandchild, skipping over the node that is being removed.
                    // Nothing points to the removed node ==> garbage collected.
                    return leftChild;

                } else if (rightChild != null) {
                    // b2. The node has one child, the right
                    // Return the right child. This sets the parent's child to it's
                    // previous grandchild, skipping over the node that is being removed.
                    // Nothing points to the removed node ==> garbage collected.
                    return rightChild;

                } else {
                    // c. The node has zero children
                    // Return null. This effectively sets the parent's pointer to null
                    // using pointer reinforcement
                    return null;
                }
            }
        }
        return current;
    }

    private BSTNode<T> removeSuccessor(BSTNode<T> current, BSTNode<T> dummy) {
        if (current.getLeft() == null) {
            // Base case: The node must have only a right child or no children
            // The successor has been found

            // Copy the data of the current node into the dummy node so it is retained
            dummy.setData(current.getData());

            // Return the current node's right child
            // This also covers the case where current has no children, since the
            // right child will be null
            return current.getRight();

        } else {
            // The current node's left child exists, keep recursing till we hit the
            // successor
            // Call the node's left child recursively to reinforce the pointers of
            // unchanged nodes
            current.setLeft(removeSuccessor(current.getLeft(), dummy));
        }
        return current;
    }

    public BSTNode<T> search(T data) {

        if (data == null) {
            throw new IllegalArgumentException();
        }

        return searchBackend(root, data);
    }

    private BSTNode<T> searchBackend(BSTNode<T> root, T data) {

        if (root == null || root.getData().equals(data)) {
            // Base cases:
            // If data is not in the tree, return null, which is the root
            // of the last subtree that was treated.
            // If data is found, return the root node
            return root;
        }

        if (data.compareTo(root.getData()) > 0) {
            // Recurse to the right subtree
            return searchBackend(root.getRight(), data);
        }

        // Recurse to the left subtree
        return searchBackend(root.getLeft(), data);
    }

    /**
     * Check if the BST contains the given data.
     * 
     * Time complexity: - Best: O(1) - Average: O(log n) - Worst: O(n)
     * 
     * @param data
     * @return true if data is in the tree, false otherwise
     */
    public boolean contains(T data) {

        if (data == null) {
            throw new IllegalArgumentException();
        }

        BSTNode<T> searchResult = search(data);
        return searchResult != null;
    }

    /**
     * Returns the root of the tree.
     * 
     * @return The root of the tree
     */
    public BSTNode<T> getRoot() {
        return root;
    }

    /**
     * Returns the size of the tree.
     *
     * @return The size of the tree
     */
    public int size() {
        return size;
    }

    /**
     * Given the root of a binary search tree, generate a pre-order traversal of the
     * tree. The original tree should not be modified in any way.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @param <T>  Generic type.
     * @param root The root of a BST.
     * @return List containing the pre-order traversal of the tree.
     */
    public List<T> preorder(BSTNode<T> root) {
        // Create an empty list as initial input
        List<T> list = new ArrayList<>();

        // Create the preorder traversal sequence
        return preorderBackend(root, list);
    }

    private List<T> preorderBackend(BSTNode<T> root, List<T> list) {
        if (root == null) {
            return list;
        }

        list.add(root.getData());
        preorderBackend(root.getLeft(), list);
        preorderBackend(root.getRight(), list);

        return list;
    }

    /**
     * Return the in-order traversal sequence of the BST.
     *
     * Time complexity: O(n).
     *
     * @param <T>  Generic type.
     * @param root The root of a BST.
     * @return List containing the in-order traversal of the tree.
     */
    public List<T> inorder(BSTNode<T> root) {
        List<T> list = new ArrayList<>();
        return inorderBackend(root, list);
    }

    private List<T> inorderBackend(BSTNode<T> root, List<T> list) {
        if (root == null) {
            return list;
        }

        inorderBackend(root.getLeft(), list);
        list.add(root.getData());
        inorderBackend(root.getRight(), list);

        return list;
    }

    /**
     * Return the post-order traversal sequence of the BST.
     *
     * Time complexity: O(n).
     *
     * @param <T>  Generic type.
     * @param root The root of a BST.
     * @return List containing the post-order traversal of the tree.
     */
    public List<T> postorder(BSTNode<T> root) {
        List<T> list = new ArrayList<>();
        return postorderBackend(root, list);
    }

    private List<T> postorderBackend(BSTNode<T> root, List<T> list) {
        if (root == null) {
            return list;
        }

        postorderBackend(root.getLeft(), list);
        postorderBackend(root.getRight(), list);
        list.add(root.getData());

        return list;
    }

    /**
     * Return the level-order traversal sequence of the BST.
     *
     * Time complexity: O(n).
     *
     * @param <T>  Generic type.
     * @param root The root of a BST.
     * @return List containing the post-order traversal of the tree.
     */
    public List<T> levelorder(BSTNode<T> root) {
        // TODO:
    }

}
