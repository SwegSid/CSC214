public class Main {
    public static void main(String[] args) {
        QueueTees<Cutie> queue = new QueueTees<>(3);

        Puppy puppy = new Puppy("Rex");
        Kitty kitty = new Kitty("Whiskers");
        PygmyMarmoset marmoset = new PygmyMarmoset("Pip");

        queue.enqueue(puppy);
        queue.enqueue(kitty);
        queue.enqueue(marmoset);

        System.out.println("Queue size: " + queue.size());

        // Queue is full, this should print a message and leave the queue unchanged.
        queue.enqueue(new Puppy("Fido"));

        Cutie first = queue.dequeue();
        System.out.println(first.getName() + " says " + first.makeCuteSound());

        System.out.println("Queue size after dequeue: " + queue.size());

        queue.clear();
        System.out.println("Queue size after clear: " + queue.size());

        // Demonstrate that the generic type works with something other than Cutie.
        QueueTees<Integer> numbers = new QueueTees<>(2);
        numbers.enqueue(1);
        numbers.enqueue(2);
        System.out.println("Dequeued number: " + numbers.dequeue());
    }
}
