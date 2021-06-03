import java.util.NoSuchElementException;

public class AVL<T extends Comparable<? super T>> {

    private AVLNode<T> root;
    private int size;

    /**
     * Adds the element to the tree.
     *
     * Adding will first add the new node as a leaf node, just like for a standard
     * BST. Afterwards, a potential rotation is be made to keep the balance in the
     * tree.
     *
     * If the data is already contained in the tree, nothing is done.
     *
     * @param data The data to add.
     * @throws java.lang.IllegalArgumentException If data is null.
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }

        // Call private add-method to add the data with the same algorithm as a
        // standard BST
        root = addBackend(root, data);
    }

    private AVLNode<T> addBackend(AVLNode<T> current, T data) {

        // 1. base case - Node is not found
        if (current == null) {
            // If the current node is null, this is the location to add the new data.
            // This might be the root if the tree is empty, or a leaf if we have
            // traversed to the end of a "path"
            size++;
            return new AVLNode<>(data);
        }

        // Pointer reinforcement logic for adding to a standard BST
        boolean isCurrentGreaterThanData = current.getData().compareTo(data) > 0;
        boolean isCurrentLessThanData = current.getData().compareTo(data) < 0;
        if (isCurrentGreaterThanData) {
            //
            current.setLeft(addBackend(current.getLeft(), data));

        } else if (isCurrentLessThanData) {
            //
            current.setRight(addBackend(current.getRight(), data));
        }

        // Balance the node if it's necessary, otherwise the balance method returns
        // the unchanged node
        current = balance(current);

        // NOTE: No node is added to the tree here, we are continuing the
        // traversal to find where to add the node and returning the nodes we traverse
        // in order to "detach" or "reinforce" their pointers.
        // Detachment is when traversing down prior to adding, reinforcing is when
        // traversing up to relink the detached pointers.
        return current;
    }

    /**
     * Removes and returns the element from the tree matching the given parameter.
     *
     * There are 3 cases to consider: 1: The node containing the data is a leaf (no
     * children). In this case, simply remove it. 2: The node containing the data
     * has one child. In this case, simply replace it with its child. 3: The node
     * containing the data has 2 children. Use the successor to replace the data,
     * NOT predecessor. As a reminder, rotations can occur after removing the
     * successor node.
     *
     * Remember to recalculate heights and balance factors while going back up the
     * tree after removing the element, making sure to rebalance if necessary. This
     * is as simple as calling the balance() method on the current node, before
     * returning it (assuming that your balance method is written correctly from
     * part 1 of this assignment).
     *
     * Do NOT return the same data that was passed in. Return the data that was
     * stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data The data to remove.
     * @return The data that was removed.
     * @throws java.lang.IllegalArgumentException If the data is null.
     * @throws java.util.NoSuchElementException   If the data is not found.
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }

        // Create a dummy node to pass into the recursive stack. Once the node to
        // remove has been found (if it exists), the removed nodes data is copied to
        // the dummy nodes data. This way, the data is available after the recursive
        // stack is empty and the removed data can be returned as the dummy node's data
        AVLNode<T> removeDummy = new AVLNode<>(null);

        root = removeBackend(root, data, removeDummy);
        size--;
        return removeDummy.getData();
    }

    private AVLNode<T> removeBackend(AVLNode<T> current, T data, AVLNode<T> removeDummy) {
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

                AVLNode<T> leftChild = current.getLeft();
                AVLNode<T> rightChild = current.getRight();

                if (leftChild != null && rightChild != null) {
                    // a. The node has two children
                    // Create a dummy node prior to removing the successor
                    AVLNode<T> successorDummy = new AVLNode<>(null);

                    // Remove the successor
                    // Note that the balancing behavior is defined in the
                    // removeSuccessor methods return statement
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
        // Balance node (if necessary) and return it
        return balance(current);
    }

    private AVLNode<T> removeSuccessor(AVLNode<T> current, AVLNode<T> dummy) {
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
        // Balance the node and return it
        // This will balance the nodes upwards in the tree after the removal while
        // pointers are getting reinforced
        return balance(current);
    }

    /**
     * Updates the height and balance factor of a node using its setter methods.
     *
     * Recall that a null node has a height of -1. If a node is not null, then the
     * height of that node will be its height instance data. The height of a node is
     * the max of its left child's height and right child's height, plus one.
     *
     * The balance factor of a node is the height of its left child minus the height
     * of its right child.
     *
     * This method should run in O(1). You may assume that the passed in node is not
     * null.
     *
     * This method should only be called in rotateLeft(), rotateRight(), and
     * balance().
     *
     * @param currentNode The node to update the height and balance factor of.
     */
    private void updateHeightAndBF(AVLNode<T> node) {
        // Compute the height of the children, accounting for null nodes (w/ -1 heights)
        int leftChildHeight = getLeftChildHeight(node);
        int rightChildHeight = getRightChildHeight(node);

        // Set height and balance factor of the node
        node.setHeight(Math.max(leftChildHeight, rightChildHeight) + 1);
        node.setBalanceFactor(leftChildHeight - rightChildHeight);
    }

    /**
     * Method that rotates a current node to the left. After saving the current's
     * right node to a variable, the right node's left subtree will become the
     * current node's right subtree. The current node will become the right node's
     * left subtree.
     *
     * Don't forget to recalculate the height and balance factor of all affected
     * nodes, using updateHeightAndBF().
     *
     * This method should run in O(1).
     *
     * You may assume that the passed in node is not null and that the subtree
     * starting at that node is right heavy. Therefore, you do not need to perform
     * any preliminary checks, rather, you can immediately perform a left rotation
     * on the passed in node and return the new root of the subtree.
     *
     * This method should only be called in balance().
     *
     * @param currentNode The current node under inspection that will rotate.
     * @return The parent of the node passed in (after the rotation).
     */
    private AVLNode<T> rotateLeft(AVLNode<T> currentNode) {
        AVLNode<T> rightChild = currentNode.getRight();
        currentNode.setRight(rightChild.getLeft());
        rightChild.setLeft(currentNode);
        updateHeightAndBF(currentNode);
        updateHeightAndBF(rightChild);
        return rightChild;
    }

    /**
     * Method that rotates a current node to the right. After saving the current's
     * left node to a variable, the left node's right subtree will become the
     * current node's left subtree. The current node will become the left node's
     * right subtree.
     *
     * Don't forget to recalculate the height and balance factor of all affected
     * nodes, using updateHeightAndBF().
     *
     * This method should run in O(1).
     *
     * You may assume that the passed in node is not null and that the subtree
     * starting at that node is left heavy. Therefore, you do not need to perform
     * any preliminary checks, rather, you can immediately perform a right rotation
     * on the passed in node and return the new root of the subtree.
     *
     * This method should only be called in balance().
     *
     * @param currentNode The current node under inspection that will rotate.
     * @return The parent of the node passed in (after the rotation).
     */
    private AVLNode<T> rotateRight(AVLNode<T> currentNode) {
        AVLNode<T> leftChild = currentNode.getLeft();
        currentNode.setLeft(leftChild.getRight());
        leftChild.setRight(currentNode);
        updateHeightAndBF(currentNode);
        updateHeightAndBF(leftChild);
        return leftChild;
    }

    /**
     * Method that balances out the tree starting at the node passed in. This method
     * should be called in your add() and remove() methods to facilitate rebalancing
     * your tree after an operation.
     *
     * The height and balance factor of the current node is first recalculated.
     * Based on the balance factor, a no rotation, a single rotation, or a double
     * rotation takes place. The current node is returned.
     *
     * You may assume that the passed in node is not null. Therefore, you do not
     * need to perform any preliminary checks, rather, you can immediately check to
     * see if any rotations need to be performed.
     *
     * This method should run in O(1).
     *
     * @param currentNode The current node under inspection.
     * @return The AVLNode that the caller should return.
     */
    private AVLNode<T> balance(AVLNode<T> currentNode) {
        /* First, we update the height and balance factor of the current node. */
        updateHeightAndBF(currentNode);

        int leftHeight = getLeftChildHeight(currentNode);
        int rightHeight = getRightChildHeight(currentNode);

        // First, check if a rotation/rebalancing is needed at all
        // It will be needed if |BF| > 1
        if (Math.abs(currentNode.getBalanceFactor()) > 1) {
            // Check if tree if right-heavy
            if (rightHeight > leftHeight) {
                // Check if a double rotation of right-left shape is needed
                if (currentNode.getRight().getBalanceFactor() == 1) {
                    // There is a "bend" detected in the tree (shape: ">")
                    // This pushes the middle one of the three nodes up between the upper
                    // and previous middle. I.e. the middle node is rotated right around
                    // the lower.
                    // Perform a right rotation
                    currentNode.setRight(rotateRight(currentNode.getRight()));
                }
                // Perform a left rotation
                currentNode = rotateLeft(currentNode);
            }
            // Check if the tree is left-heavy
            else if (leftHeight > rightHeight) {
                // Check if a double rotation of left-right shape is needed
                if (currentNode.getLeft().getBalanceFactor() == -1) {
                    // There is a "bend" detected in the tree (shape: "<")
                    // This pushes the middle one of the three nodes up between the upper
                    // and previous middle. I.e. the middle node is rotated left around
                    // the lower.
                    // Perform the left rotation
                    currentNode.setLeft(rotateLeft(currentNode.getLeft()));
                }
                // Perform the right rotation
                currentNode = rotateRight(currentNode);
            }
        }

        return currentNode;
    }

    private int getLeftChildHeight(AVLNode<T> node) {
        int height;
        if (node.getLeft() == null) {
            height = -1;
        } else {
            height = node.getLeft().getHeight();
        }
        return height;
    }

    private int getRightChildHeight(AVLNode<T> node) {
        int height;
        if (node.getRight() == null) {
            height = -1;
        } else {
            height = node.getRight().getHeight();
        }
        return height;
    }

    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since you
     * have direct access to the variable.
     *
     * @return The root of the tree.
     */
    public AVLNode<T> getRoot() {
        return root;
    }

    /**
     * Returns the size of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since you
     * have direct access to the variable.
     *
     * @return The size of the tree.
     */
    public int size() {
        return size;
    }
}
