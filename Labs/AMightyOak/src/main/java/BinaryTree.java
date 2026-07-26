
public class BinaryTree<T> {

    private Node<T> root;

    public BinaryTree(T rootData) {
        this.root = new Node<>(rootData);
    }

    public Node<T> getRoot() {
        return root;
    }

    public boolean isEmpty() {
        return root == null;
    }
}
