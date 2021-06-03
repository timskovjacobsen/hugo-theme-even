import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.List;
import java.util.ArrayList;

public class TestAVL {

    // ----- ADD METHOD -----
    public AVL<Integer> initialAddAVLTree() {
        AVL<Integer> tree = new AVL<>();

        tree.add(2);
        tree.add(0);
        tree.add(7);
        tree.add(1);
        tree.add(4);
        tree.add(8);
        tree.add(3);
        tree.add(6);

        return tree;
    }

    @Test
    public void testInitialAddTree() {
        AVL<Integer> tree = initialAddAVLTree();

        String actual = preorder(tree).toString();
        String expected = "[2, 0, 1, 7, 4, 3, 6, 8]";
        assertEquals(expected, actual);
    }

    @Test
    public void testAdd() {
        AVL<Integer> tree = initialAddAVLTree();

        tree.add(5);

        String actual = preorder(tree).toString();
        String expected = "[2, 0, 1, 6, 4, 3, 5, 7, 8]";
        assertEquals(expected, actual);

    }

    // ----- REMOVE METHOD -----
    public AVL<Integer> initialRemoveAVLTree() {
        AVL<Integer> tree = new AVL<>();
        tree.add(2);
        tree.add(0);
        tree.add(4);
        tree.add(1);
        tree.add(3);
        tree.add(6);
        tree.add(5);

        return tree;
    }

    @Test
    public void testRemove() {
        AVL<Integer> tree = initialRemoveAVLTree();
        tree.remove(3);

        String actual = preorder(tree).toString();
        String expected = "[2, 0, 1, 5, 4, 6]";
        assertEquals(expected, actual);
    }

    @Test
    public void testRemove4ExampleFromAutoGrader() {
        // Build initial tree from auto grader test case
        AVL<Integer> tree = new AVL<>();
        tree.add(7);
        tree.add(4);
        tree.add(10);
        tree.add(2);
        tree.add(6);
        tree.add(8);
        tree.add(11);
        tree.add(0);
        tree.add(3);
        tree.add(5);
        tree.add(9);
        tree.add(1);

        tree.remove(4);

        String actual = preorder(tree).toString();
        String expected = "[7, 2, 0, 1, 5, 3, 6, 10, 8, 9, 11]";
        assertEquals(expected, actual);
    }

    // ----- PREORDER TRAVERSAL FOR PRINTING THE AVL TREE ----
    public List<Integer> preorder(AVL<Integer> tree) {
        List<Integer> list = new ArrayList<>();

        AVLNode<Integer> root = tree.getRoot();
        return preorderTraversal(root, list);
    }

    private List<Integer> preorderTraversal(AVLNode<Integer> root, List<Integer> list) {
        if (root == null) {
            return list;
        }

        list.add(root.getData());
        preorderTraversal(root.getLeft(), list);
        preorderTraversal(root.getRight(), list);

        return list;
    }

}
