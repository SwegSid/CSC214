import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayStackTest {

    private ArrayStack<Dish> stack;

    @BeforeEach
    public void setUp() {
        stack = new ArrayStack<>(3);
    }

    @Test
    public void newStackIsEmpty() {
        assertEquals(0, stack.size());
        assertTrue(stack.isEmpty());
    }

    @Test
    public void pushIncreasesSize() {
        stack.push(new Dish("Plate", "Ceramic"));
        assertEquals(1, stack.size());
    }

    @Test
    public void pushThenPeekReturnsTopItemWithoutRemovingIt() {
        Dish plate = new Dish("Plate", "Ceramic");
        stack.push(plate);
        assertEquals(plate, stack.peek());
        assertEquals(1, stack.size()); // peek should not remove
    }

    @Test
    public void pushThenPopReturnsItemsInLifoOrder() {
        Dish plate = new Dish("Plate", "Ceramic");
        Dish bowl = new Dish("Bowl", "Glass");

        stack.push(plate);
        stack.push(bowl);

        assertEquals(bowl, stack.pop());
        assertEquals(plate, stack.pop());
        assertEquals(0, stack.size());
    }

    @Test
    public void popDecreasesSize() {
        stack.push(new Dish("Plate", "Ceramic"));
        stack.push(new Dish("Bowl", "Glass"));
        stack.pop();
        assertEquals(1, stack.size());
    }

    @Test
    public void poppingEmptyStackReturnsNullAndDoesNotThrow() {
        assertNull(stack.pop());
        assertEquals(0, stack.size());
    }

    @Test
    public void peekingEmptyStackReturnsNull() {
        assertNull(stack.peek());
    }

    @Test
    public void pushingOntoFullStackLeavesStackUnchanged() {
        Dish plate = new Dish("Plate", "Ceramic");
        Dish bowl = new Dish("Bowl", "Glass");
        Dish cup = new Dish("Cup", "Plastic");
        Dish saucer = new Dish("Saucer", "Ceramic");

        stack.push(plate);
        stack.push(bowl);
        stack.push(cup);

        assertTrue(stack.isFull());

        stack.push(saucer); // should be rejected

        assertEquals(3, stack.size());
        assertEquals(cup, stack.peek()); // top is still the last successful push
    }

    @Test
    public void isFullReturnsTrueOnlyWhenAtCapacity() {
        assertFalse(stack.isFull());
        stack.push(new Dish("Plate", "Ceramic"));
        stack.push(new Dish("Bowl", "Glass"));
        assertFalse(stack.isFull());
        stack.push(new Dish("Cup", "Plastic"));
        assertTrue(stack.isFull());
    }

    @Test
    public void clearRemovesAllElements() {
        stack.push(new Dish("Plate", "Ceramic"));
        stack.push(new Dish("Bowl", "Glass"));

        stack.clear();

        assertEquals(0, stack.size());
        assertTrue(stack.isEmpty());
        assertNull(stack.peek());
    }

    @Test
    public void stackCanBeReusedAfterClear() {
        stack.push(new Dish("Plate", "Ceramic"));
        stack.clear();
        stack.push(new Dish("Bowl", "Glass"));
        assertEquals(1, stack.size());
        assertEquals(new Dish("Bowl", "Glass"), stack.peek());
    }

    @Test
    public void constructorRejectsNonPositiveCapacity() {
        assertThrows(IllegalArgumentException.class, () -> new ArrayStack<Dish>(0));
        assertThrows(IllegalArgumentException.class, () -> new ArrayStack<Dish>(-1));
    }

    @Test
    public void stackWorksWithGenericTypeOtherThanDish() {
        ArrayStack<String> stringStack = new ArrayStack<>(2);
        stringStack.push("Fork");
        stringStack.push("Spoon");

        assertEquals("Spoon", stringStack.pop());
        assertEquals("Fork", stringStack.pop());
    }

    @Test
    public void stackWorksWithIntegerType() {
        ArrayStack<Integer> intStack = new ArrayStack<>(3);
        intStack.push(1);
        intStack.push(2);
        intStack.push(3);

        assertEquals(3, intStack.pop());
        assertEquals(2, intStack.pop());
        assertEquals(1, intStack.pop());
    }
}
