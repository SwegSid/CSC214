package terriblyWeightySubject;


import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Converter converter = new Converter();

        System.out.println("Welcome to the Ounce Conversion Program!");
        System.out.println();

        System.out.print("How many ounces do you have? ");
        int ounces = scanner.nextInt();

        System.out.println("Thank you! Converting ounces to pounds.");
        System.out.println();

        System.out.println(ounces + " oz is equal to " + converter.toPoundsAndOunces(ounces) + ".");
        System.out.println(ounces + " oz is equal to " + converter.toPounds(ounces) + ".");

        System.out.println();
        System.out.println("Thank you for using the OCP!");
    }
}
