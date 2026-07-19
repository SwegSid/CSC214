public class Main {
    public static void main(String[] args) {
        ArrayStack<Dish> stack = new ArrayStack<>(3);

        System.out.println("Size: " + stack.size());

        stack.push(new Dish("Plate", "Ceramic"));
        stack.push(new Dish("Bowl", "Glass"));
        stack.push(new Dish("Cup", "Plastic"));

        System.out.println("Size after 3 pushes: " + stack.size());

        // Stack is full (capacity 3), this push should fail
        stack.push(new Dish("Saucer", "Ceramic"));

        System.out.println("Peek: " + stack.peek());

        System.out.println("Pop: " + stack.pop());
        System.out.println("Pop: " + stack.pop());

        System.out.println("Size after 2 pops: " + stack.size());

        stack.clear();
        System.out.println("Size after clear: " + stack.size());

        // Popping from an empty stack
        stack.pop();
    }
}
