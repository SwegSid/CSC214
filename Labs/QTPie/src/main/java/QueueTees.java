/**
 * The Generic Type add-on is satisfied by parameterizing the class with
 * <T>, allowing a QueueTees to store any object type, not just Cutie
 * objects. The Clear Method add-on is satisfied by the clear() method.
 */
public class QueueTees<T> {
    private Object[] items;
    private int front;
    private int rear;
    private int count;
    private int capacity;

    public QueueTees(int capacity) {
        this.capacity = capacity;
        this.items = new Object[capacity];
        this.front = 0;
        this.rear = -1;
        this.count = 0;
    }

    public void enqueue(T item) {
        if (isFull()) {
            System.out.println("Queue is full. Cannot enqueue " + item + ".");
            return;
        }

        rear = (rear + 1) % capacity;
        items[rear] = item;
        count++;
    }

    @SuppressWarnings("unchecked")
    public T dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty. Cannot dequeue.");
            return null;
        }

        T item = (T) items[front];
        items[front] = null;
        front = (front + 1) % capacity;
        count--;
        return item;
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == capacity;
    }

    public void clear() {
        for (int i = 0; i < capacity; i++) {
            items[i] = null;
        }
        front = 0;
        rear = -1;
        count = 0;
    }
}
