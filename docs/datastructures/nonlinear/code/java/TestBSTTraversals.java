import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestBSTTraversals {
    BST<Integer> traversal = new BST<>();

    public BSTNode<Integer> binaryTreeAssignmentStatement() {
        BSTNode<Integer> root = new BSTNode<>(50);
        root.setLeft(new BSTNode<>(25));
        root.getLeft().setLeft(new BSTNode<>(10));

        root.setRight(new BSTNode<>(100));
        root.getRight().setLeft(new BSTNode<>(75));
        root.getRight().setRight(new BSTNode<>(125));
        root.getRight().getRight().setLeft(new BSTNode<>(110));
        return root;
    }

    // ----- Preorder tests -----
    @Test
    public void testPreorderAssignment() {
        BSTNode<Integer> root = binaryTreeAssignmentStatement();
        assertEquals("[50, 25, 10, 100, 75, 125, 110]", traversal.preorder(root).toString());
    }

    @Test
    public void testPreorderEmptyTree() {
        assertEquals("[]", traversal.preorder(null).toString());
    }

    // ----- Postorder tests -----
    @Test
    public void testPostorderAssignment() {
        BSTNode<Integer> root = binaryTreeAssignmentStatement();
        assertEquals("[10, 25, 75, 110, 125, 100, 50]", traversal.postorder(root).toString());
    }

    @Test
    public void testPostorderEmptyTree() {
        assertEquals("[]", traversal.postorder(null).toString());
    }

    // ----- Inorder tests -----
    @Test
    public void testInorderAssignment() {
        BSTNode<Integer> root = binaryTreeAssignmentStatement();
        assertEquals("[10, 25, 50, 75, 100, 110, 125]", traversal.inorder(root).toString());
    }

    @Test
    public void testInorderEmptyTree() {
        assertEquals("[]", traversal.inorder(null).toString());
    }

}