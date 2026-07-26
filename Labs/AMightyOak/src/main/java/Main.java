public class Main {

    public static void main(String[] args) {
        // Build a small tree of squirrels
        BinaryTree<Squirrel> tree = new BinaryTree<>(
                new Squirrel("Sandy", 2, "Loves acorns more than anything")
        );

        Node<Squirrel> root = tree.getRoot();
        Node<Squirrel> leftChild = root.attachLeft(
                new Squirrel("Nutkin", 1, "Buries snacks in the same spot every time")
        );
        Node<Squirrel> rightChild = root.attachRight(
                new Squirrel("Chip", 3, "Can jump between branches 10 feet apart")
        );

        leftChild.attachLeft(new Squirrel("Pip", 0, "Youngest of the bunch"));
        rightChild.attachRight(new Squirrel("Dale", 4, "Oldest and slowest climber"));

        System.out.println("Root: " + root.getData());
        System.out.println("Root's left child: " + root.left().getData());
        System.out.println("Root's right child: " + root.right().getData());
        System.out.println("Left child's left child: " + root.left().left().getData());
        System.out.println("Right child's right child: " + root.right().right().getData());

        System.out.println();
        System.out.println("Root has left? " + root.hasLeft());
        System.out.println("Right child has left? " + rightChild.hasLeft());

        // Demonstrate the tree is generic and can store other types too
        System.out.println();
        BinaryTree<String> wordTree = new BinaryTree<>("root");
        wordTree.getRoot().attachLeft("left branch");
        wordTree.getRoot().attachRight("right branch");
        System.out.println("Generic String tree root: " + wordTree.getRoot().getData());
        System.out.println("Generic String tree left: " + wordTree.getRoot().left().getData());
    }
}
