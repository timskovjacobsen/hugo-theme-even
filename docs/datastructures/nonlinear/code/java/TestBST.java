import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.List;
import java.util.ArrayList;

public class TestBST {
    /**
     * Initial tree from problem description
     * 
     * @return
     */
    public BST<Integer> initialTree() {
        BST<Integer> tree = new BST<>();
        tree.add(50);
        tree.add(15);
        tree.add(5);
        tree.add(10);
        tree.add(75);
        tree.add(100);
        return tree;
    }

    @Test
    public void binaryTreeFromAssignment() {
        BST<Integer> tree = initialTree();

        // Root
        System.out.println(tree.getRoot().getData());

        // Left subtree
        System.out.println(tree.getRoot().getLeft().getData());
        System.out.println(tree.getRoot().getLeft().getLeft().getData());
        System.out.println(tree.getRoot().getLeft().getLeft().getRight().getData());

        // Right subtree
        System.out.println(tree.getRoot().getRight().getData());
        System.out.println(tree.getRoot().getRight().getRight().getData());
    }

    @Test
    public void testHeight1() {
        BST<Integer> tree = initialTree();
        assertEquals(4, tree.height());
    }

    @Test
    public void testHeightEmptyTree() {
        BST<Integer> tree = new BST<>();
        assertEquals(0, tree.height());
    }

    @Test
    public void testSearch() {
        BST<Integer> tree = initialTree();

        int s1 = tree.search(15).getData();
        int s2 = tree.search(50).getData();
        int s3 = tree.search(100).getData();
        assertEquals(15, s1);
        assertEquals(50, s2);
        assertEquals(100, s3);

        assertEquals(null, tree.search(19));
        assertEquals(null, tree.search(199));
    }

    @Test
    public void testContains() {
        BST<Integer> tree = initialTree();
        assertEquals(true, tree.contains(15));
        assertEquals(true, tree.contains(50));
        assertEquals(true, tree.contains(100));
        assertEquals(false, tree.contains(19));
        assertEquals(false, tree.contains(199));
    }

    @Test
    public void testAdd() {

        BST<Integer> tree = initialTree();

        int data = 25;
        tree.add(data);

        // Test that the added node sits in the right location
        int newlyAddedNode = tree.getRoot().getLeft().getRight().getData();
        assertEquals(25, newlyAddedNode);

        assertEquals(7, tree.size());

        // Check the inorder traversal (just for an additional check)
        System.out.println(inorder(tree));
    }

    @Test
    public void testRemoveNoChildren() {
        BST<Integer> tree = initialTree();

        // Make sure that the node is there with data before removal
        int nodeDataToRemove = tree.getRoot().getLeft().getLeft().getRight().getData();
        assertEquals(10, nodeDataToRemove);

        // Perform the removal
        int removedData = tree.remove(10);
        assertEquals(10, removedData);

        // Get the node that was removed to check if it is null
        BSTNode<Integer> nullNode = tree.getRoot().getLeft().getLeft().getRight();
        assertEquals(null, nullNode);
    }

    @Test
    public void testRemoveOneChild() {
        BST<Integer> tree = initialTree();
        int removeData = tree.remove(5);
        assertEquals(5, removeData);

        // Test that the node has been skipped over. That the removed node's parent
        // now points to its grand child instead of the removed child node
        BSTNode<Integer> node10 = tree.getRoot().getLeft().getLeft();
        int node10Data = node10.getData();
        assertEquals(10, node10Data);

        // Test that 10 does not have any children
        assertEquals(null, node10.getLeft());
        assertEquals(null, node10.getRight());
    }

    @Test
    public void testRemoveTwoChildrenWithSuccessor() {
        // Prepare the correct input tree
        // (assumes that these operations are properly tested)
        BST<Integer> tree = initialTree();
        tree.remove(10);
        tree.add(25);
        tree.add(20);

        System.out.println(preorder(tree));

        // Perform the remove operation to test
        int removedData = tree.remove(15);
        assertEquals(15, removedData);

        // Get node 20
        BSTNode<Integer> node20 = tree.getRoot().getLeft();
        int node20LeftChildData = node20.getLeft().getData();
        int node20RightChildData = node20.getRight().getData();
        assertEquals(5, node20LeftChildData);
        assertEquals(25, node20RightChildData);

        assertEquals(6, tree.size());
    }

    // TRAVERSAL ALGORITHMS FOR PRINTING THE TREES

    // Inorder traversal methods
    public List<Integer> inorder(BST<Integer> tree) {
        List<Integer> list = new ArrayList<>();

        BSTNode<Integer> root = tree.getRoot();
        return inorderTraversal(root, list);
    }

    public List<Integer> inorderTraversal(BSTNode<Integer> root, List<Integer> list) {
        if (root == null) {
            return list;
        }

        inorderTraversal(root.getLeft(), list);
        list.add(root.getData());
        inorderTraversal(root.getRight(), list);

        return list;
    }

    // Inorder traversal methods
    public List<Integer> preorder(BST<Integer> tree) {
        List<Integer> list = new ArrayList<>();

        BSTNode<Integer> root = tree.getRoot();
        return preorderTraversal(root, list);
    }

    public List<Integer> preorderTraversal(BSTNode<Integer> root, List<Integer> list) {
        if (root == null) {
            return list;
        }

        list.add(root.getData());
        preorderTraversal(root.getLeft(), list);
        preorderTraversal(root.getRight(), list);

        return list;
    }

}
