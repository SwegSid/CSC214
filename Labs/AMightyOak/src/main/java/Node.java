
public class Node<T> {

    private T data;
    private Node<T> left;
    private Node<T> right;

    public Node(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> left() {
        return left;
    }

    public Node<T> right() {
        return right;
    }

    public boolean hasLeft() {
        return left != null;
    }

    public boolean hasRight() {
        return right != null;
    }

    /**
     * Attaches an existing node as this node's left child.
     */
    public void attachLeft(Node<T> node) {
        this.left = node;
    }

    /**
     * Attaches an existing node as this node's right child.
     */
    public void attachRight(Node<T> node) {
        this.right = node;
    }

    /**
     * Convenience method: creates a new node wrapping the given data
     * and attaches it as this node's left child. Returns the new node
     * so it can be chained or attached to further.
     */
    public Node<T> attachLeft(T data) {
        Node<T> node = new Node<>(data);
        this.left = node;
        return node;
    }

    /**
     * Convenience method: creates a new node wrapping the given data
     * and attaches it as this node's right child. Returns the new node.
     */
    public Node<T> attachRight(T data) {
        Node<T> node = new Node<>(data);
        this.right = node;
        return node;
    }

    @Override
    public String toString() {
        return String.valueOf(data);
    }
}
