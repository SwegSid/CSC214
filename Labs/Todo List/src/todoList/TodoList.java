package todoList;


import java.util.ArrayList;
import java.util.List;

public class TodoList {

    private final List<String> incompleteTasks = new ArrayList<>();
    private final List<String> completedTasks = new ArrayList<>();

    // Add a new task; rejects blank/null and duplicate incomplete tasks
    public void add(String task) {
        if (task == null || task.trim().isEmpty()) {
            System.out.println("Task cannot be blank. Nothing was added.");
            return;
        }
        String trimmed = task.trim();
        if (incompleteTasks.contains(trimmed)) {
            System.out.println("\"" + trimmed + "\" is already in your list. Nothing was added.");
            return;
        }
        incompleteTasks.add(trimmed);
    }

    // Mark a task as complete by name; does nothing if task not found
    public void complete(String task) {
        if (task == null || task.trim().isEmpty()) {
            System.out.println("Task name cannot be blank.");
            return;
        }
        String trimmed = task.trim();
        if (incompleteTasks.remove(trimmed)) {
            completedTasks.add(trimmed);
            System.out.println("\"" + trimmed + "\" marked as complete!");
        } else {
            System.out.println("\"" + trimmed + "\" was not found in your incomplete tasks.");
        }
    }

    // Returns all tasks (complete + incomplete) as a list
    public List<String> all() {
        List<String> allTasks = new ArrayList<>();
        allTasks.addAll(incompleteTasks);
        allTasks.addAll(completedTasks);

        if (allTasks.isEmpty()) {
            System.out.println("Your to-do list is empty!");
        } else {
            System.out.println("=== All Tasks ===");
            for (String t : incompleteTasks) {
                System.out.println("  [ ] " + t);
            }
            for (String t : completedTasks) {
                System.out.println("  [x] " + t);
            }
        }
        return allTasks;
    }

    // Returns only completed tasks
    public List<String> completed() {
        if (completedTasks.isEmpty()) {
            System.out.println("No completed tasks yet.");
        } else {
            System.out.println("=== Completed Tasks ===");
            for (String t : completedTasks) {
                System.out.println("  [x] " + t);
            }
        }
        return new ArrayList<>(completedTasks);
    }

    // Returns only incomplete tasks
    public List<String> incomplete() {
        if (incompleteTasks.isEmpty()) {
            System.out.println("No incomplete tasks. You're all caught up!");
        } else {
            System.out.println("=== Incomplete Tasks ===");
            for (String t : incompleteTasks) {
                System.out.println("  [ ] " + t);
            }
        }
        return new ArrayList<>(incompleteTasks);
    }

    // Clears the entire list
    public void clear() {
        incompleteTasks.clear();
        completedTasks.clear();
        System.out.println("To-do list cleared.");
    }
}
