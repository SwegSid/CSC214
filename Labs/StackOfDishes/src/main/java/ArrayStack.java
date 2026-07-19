/**
 * A fixed-capacity stack backed by an array.
 * Uses generics so it can store any reference type, not just Dish objects.
 */
public class ArrayStack<T> {
    private Object[] elements;
    private int top; // index of the next free slot
    private final int capacity;

    public ArrayStack(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than zero.");
        }
        this.capacity = capacity;
        this.elements = new Object[capacity];
        this.top = 0;
    }

    /**
     * Pushes an item onto the top of the stack.
     * If the stack is full, prints a message and leaves the stack unchanged.
     */
    public void push(T item) {
        if (isFull()) {
            System.out.println("The stack is full! Cannot push " + item + ".");
            return;
        }
        elements[top] = item;
        top++;
    }

    /**
     * Removes and returns the item on top of the stack.
     * Returns null and prints a message if the stack is empty.
     */
    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) {
            System.out.println("The stack is empty! Cannot pop.");
            return null;
        }
        top--;
        T item = (T) elements[top];
        elements[top] = null; // avoid holding a stale reference
        return item;
    }

    /**
     * Returns the item on top of the stack without removing it.
     * Returns null and prints a message if the stack is empty.
     */
    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) {
            System.out.println("The stack is empty! Nothing to peek.");
            return null;
        }
        return (T) elements[top - 1];
    }

    /**
     * Returns the number of elements currently in the stack.
     */
    public int size() {
        return top;
    }

    public boolean isEmpty() {
        return top == 0;
    }

    public boolean isFull() {
        return top == capacity;
    }

    /**
     * Removes all elements from the stack.
     */
    public void clear() {
        for (int i = 0; i < top; i++) {
            elements[i] = null;
        }
        top = 0;
    }
}
