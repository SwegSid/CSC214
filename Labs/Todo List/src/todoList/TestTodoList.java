package todoList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestTodoList {

    TodoList list;

    @BeforeEach
    void setUp() {
        list = new TodoList();
    }



    @Test
    public void testAddOneTask() {
        list.add("Buy milk");
        assertTrue(list.incomplete().contains("Buy milk"));
    }

    @Test
    public void testAddMultipleTasks() {
        list.add("Buy milk");
        list.add("Buy eggs");
        List<String> tasks = list.incomplete();
        assertEquals(2, tasks.size());
        assertTrue(tasks.contains("Buy milk"));
        assertTrue(tasks.contains("Buy eggs"));
    }

    @Test
    public void testAddBlankTaskIsRejected() {
        list.add("");
        assertEquals(0, list.all().size());
    }

    @Test
    public void testAddNullTaskIsRejected() {
        list.add(null);
        assertEquals(0, list.all().size());
    }

    @Test
    public void testAddDuplicateIncompleteTaskIsRejected() {
        list.add("Buy milk");
        list.add("Buy milk");
        assertEquals(1, list.incomplete().size());
    }


    @Test
    public void testCompleteATask() {
        list.add("Buy milk");
        list.complete("Buy milk");
        assertTrue(list.completed().contains("Buy milk"));
        assertFalse(list.incomplete().contains("Buy milk"));
    }

    @Test
    public void testCompleteNonExistentTaskDoesNotCrash() {
        list.add("Buy milk");
        list.complete("Walk the dog"); // should not throw
        assertEquals(1, list.incomplete().size());
    }

    @Test
    public void testCompleteBlankTaskDoesNotCrash() {
        list.add("Buy milk");
        list.complete(""); // should not throw
        assertEquals(1, list.incomplete().size());
    }


    @Test
    public void testAllReturnsCompleteAndIncomplete() {
        list.add("Buy milk");
        list.add("Buy eggs");
        list.complete("Buy eggs");
        List<String> all = list.all();
        assertEquals(2, all.size());
        assertTrue(all.contains("Buy milk"));
        assertTrue(all.contains("Buy eggs"));
    }

    @Test
    public void testAllOnEmptyListReturnsEmpty() {
        assertEquals(0, list.all().size());
    }


    @Test
    public void testCompletedReturnsOnlyCompletedTasks() {
        list.add("Buy milk");
        list.add("Buy eggs");
        list.complete("Buy milk");
        List<String> done = list.completed();
        assertEquals(1, done.size());
        assertTrue(done.contains("Buy milk"));
        assertFalse(done.contains("Buy eggs"));
    }

    @Test
    public void testCompletedWhenNoneCompleted() {
        list.add("Buy milk");
        assertEquals(0, list.completed().size());
    }


    @Test
    public void testIncompleteReturnsOnlyIncompleteTasks() {
        list.add("Buy milk");
        list.add("Buy eggs");
        list.complete("Buy milk");
        List<String> pending = list.incomplete();
        assertEquals(1, pending.size());
        assertTrue(pending.contains("Buy eggs"));
        assertFalse(pending.contains("Buy milk"));
    }

    @Test
    public void testIncompleteWhenAllComplete() {
        list.add("Buy milk");
        list.complete("Buy milk");
        assertEquals(0, list.incomplete().size());
    }


    @Test
    public void testClearEmptiesTheList() {
        list.add("Buy milk");
        list.add("Buy eggs");
        list.complete("Buy eggs");
        list.clear();
        assertEquals(0, list.all().size());
    }

    @Test
    public void testClearOnEmptyListDoesNotCrash() {
        list.clear(); // should not throw
        assertEquals(0, list.all().size());
    }
}
