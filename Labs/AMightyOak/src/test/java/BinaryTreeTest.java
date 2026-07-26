import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BinaryTreeTest {

    private BinaryTree<Squirrel> tree;
    private Squirrel rootSquirrel;

    @BeforeEach
    void setUp() {
        rootSquirrel = new Squirrel("Sandy", 2, "Loves acorns");
        tree = new BinaryTree<>(rootSquirrel);
    }

    @Test
    void newTreeHasRootWithCorrectData() {
        assertEquals(rootSquirrel, tree.getRoot().getData());
    }

    @Test
    void newTreeIsNotEmpty() {
        assertFalse(tree.isEmpty());
    }

    @Test
    void rootStartsWithNoChildren() {
        assertFalse(tree.getRoot().hasLeft());
        assertFalse(tree.getRoot().hasRight());
        assertNull(tree.getRoot().left());
        assertNull(tree.getRoot().right());
    }

    @Test
    void attachLeftWithDataCreatesAndAttachesNode() {
        Squirrel child = new Squirrel("Nutkin", 1, "Buries snacks");
        Node<Squirrel> leftNode = tree.getRoot().attachLeft(child);

        assertTrue(tree.getRoot().hasLeft());
        assertEquals(child, tree.getRoot().left().getData());
        assertEquals(leftNode, tree.getRoot().left());
    }

    @Test
    void attachRightWithDataCreatesAndAttachesNode() {
        Squirrel child = new Squirrel("Chip", 3, "Great jumper");
        Node<Squirrel> rightNode = tree.getRoot().attachRight(child);

        assertTrue(tree.getRoot().hasRight());
        assertEquals(child, tree.getRoot().right().getData());
        assertEquals(rightNode, tree.getRoot().right());
    }

    @Test
    void attachExistingNodeAsLeftChild() {
        Node<Squirrel> existingNode = new Node<>(new Squirrel("Pip", 0, "Youngest"));
        tree.getRoot().attachLeft(existingNode);

        assertSame(existingNode, tree.getRoot().left());
    }

    @Test
    void attachExistingNodeAsRightChild() {
        Node<Squirrel> existingNode = new Node<>(new Squirrel("Dale", 4, "Oldest"));
        tree.getRoot().attachRight(existingNode);

        assertSame(existingNode, tree.getRoot().right());
    }

    @Test
    void canBuildMultipleLevelsDeep() {
        Node<Squirrel> left = tree.getRoot().attachLeft(new Squirrel("Nutkin", 1, "Snacks"));
        left.attachLeft(new Squirrel("Pip", 0, "Youngest"));

        assertTrue(tree.getRoot().left().hasLeft());
        assertEquals("Pip", tree.getRoot().left().left().getData().getName());
    }

    @Test
    void overwritingLeftChildReplacesPreviousNode() {
        tree.getRoot().attachLeft(new Squirrel("Nutkin", 1, "First"));
        tree.getRoot().attachLeft(new Squirrel("Chip", 3, "Replacement"));

        assertEquals("Chip", tree.getRoot().left().getData().getName());
    }

    @Test
    void nodeIsGenericAndCanStoreOtherTypes() {
        Node<String> stringNode = new Node<>("hello");
        stringNode.attachLeft("left value");
        stringNode.attachRight("right value");

        assertEquals("hello", stringNode.getData());
        assertEquals("left value", stringNode.left().getData());
        assertEquals("right value", stringNode.right().getData());
    }

    @Test
    void setDataUpdatesNodeContents() {
        Node<Squirrel> node = new Node<>(rootSquirrel);
        Squirrel replacement = new Squirrel("Chip", 3, "Replacement squirrel");
        node.setData(replacement);

        assertEquals(replacement, node.getData());
    }
}
