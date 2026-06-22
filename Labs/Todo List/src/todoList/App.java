package todoList;

public class App {
    public static void main(String[] args) {
        TodoList list = new TodoList();

        System.out.println("Welcome to the Simple To-Do List App\n");

        list.add("Buy milk");
        list.add("Buy eggs");
        list.add("Prepare a lesson for CSC 122");
        list.add("Sow beet seeds");


        list.add("");
        list.add(null);


        list.add("Buy milk");

        System.out.println();


        list.all();
        System.out.println();


        list.complete("Buy eggs");
        System.out.println();


        list.complete("Walk the dog");
        System.out.println();


        list.completed();
        System.out.println();


        list.incomplete();
        System.out.println();


        list.all();
        System.out.println();


        list.clear();
        System.out.println();

        list.all();

        System.out.println("\nThanks for using the To-Do List App");
    }
}
