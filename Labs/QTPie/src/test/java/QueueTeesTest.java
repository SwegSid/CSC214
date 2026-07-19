import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QueueTeesTest {

    private QueueTees<Cutie> queue;
    private Puppy puppy;
    private Kitty kitty;
    private PygmyMarmoset marmoset;

    @BeforeEach
    void setUp() {
        queue = new QueueTees<>(3);
        puppy = new Puppy("Rex");
        kitty = new Kitty("Whiskers");
        marmoset = new PygmyMarmoset("Pip");
    }

    @Test
    void newQueueIsEmpty() {
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }

    @Test
    void enqueueIncreasesSize() {
        queue.enqueue(puppy);
        assertEquals(1, queue.size());
        assertFalse(queue.isEmpty());
    }

    @Test
    void dequeueReturnsItemsInFifoOrder() {
        queue.enqueue(puppy);
        queue.enqueue(kitty);
        queue.enqueue(marmoset);

        assertEquals(puppy, queue.dequeue());
        assertEquals(kitty, queue.dequeue());
        assertEquals(marmoset, queue.dequeue());
    }

    @Test
    void dequeueDecreasesSize() {
        queue.enqueue(puppy);
        queue.enqueue(kitty);
        queue.dequeue();
        assertEquals(1, queue.size());
    }

    @Test
    void dequeueOnEmptyQueueReturnsNull() {
        assertNull(queue.dequeue());
    }

    @Test
    void enqueueOnFullQueueDoesNotChangeQueue() {
        queue.enqueue(puppy);
        queue.enqueue(kitty);
        queue.enqueue(marmoset);

        assertTrue(queue.isFull());

        queue.enqueue(new Puppy("Fido"));

        assertEquals(3, queue.size());
        assertEquals(puppy, queue.dequeue());
    }

    @Test
    void queueWrapsAroundCircularBufferCorrectly() {
        queue.enqueue(puppy);
        queue.enqueue(kitty);
        queue.dequeue();
        queue.enqueue(marmoset);
        queue.enqueue(puppy);

        assertEquals(kitty, queue.dequeue());
        assertEquals(marmoset, queue.dequeue());
        assertEquals(puppy, queue.dequeue());
    }

    @Test
    void clearEmptiesTheQueue() {
        queue.enqueue(puppy);
        queue.enqueue(kitty);
        queue.clear();

        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
        assertNull(queue.dequeue());
    }

    @Test
    void queueCanBeReusedAfterClear() {
        queue.enqueue(puppy);
        queue.clear();
        queue.enqueue(kitty);

        assertEquals(1, queue.size());
        assertEquals(kitty, queue.dequeue());
    }

    @Test
    void genericQueueWorksWithNonCutieTypes() {
        QueueTees<String> stringQueue = new QueueTees<>(2);
        stringQueue.enqueue("Hello");
        stringQueue.enqueue("World");

        assertEquals("Hello", stringQueue.dequeue());
        assertEquals("World", stringQueue.dequeue());
    }
}
