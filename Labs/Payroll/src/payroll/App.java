package payroll;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Payroll Program!");
        System.out.println();

        System.out.print("How many hours did you work this week? ");
        double hours = scanner.nextDouble();

        System.out.print("How many children do you have?  ");
        int dependents = scanner.nextInt();

        // Negative children validation (add-on)
        if (dependents < 0) {
            dependents = 0;
        }

        // Life insurance menu (add-on)
        double lifeInsurance = 0;
        boolean validPlan = false;
        while (!validPlan) {
            System.out.println("Which life insurance plan do you want to select?");
            System.out.println();
            System.out.println("  (1) no plan");
            System.out.println("  (2) single plan");
            System.out.println("  (3) married plan");
            System.out.println("  (4) married with children plan");
            System.out.println();
            int plan = scanner.nextInt();
            System.out.println();

            switch (plan) {
                case 1:
                    lifeInsurance = 0;
                    validPlan = true;
                    break;
                case 2:
                    lifeInsurance = 5.00;
                    validPlan = true;
                    break;
                case 3:
                    lifeInsurance = 10.00;
                    validPlan = true;
                    break;
                case 4:
                    if (dependents < 1) {
                        System.out.println("Sorry! You need at least one child to select that plan.");
                        System.out.println();
                    } else {
                        lifeInsurance = 15.00;
                        validPlan = true;
                    }
                    break;
                default:
                    System.out.println("Invalid option. Please select 1-4.");
                    System.out.println();
            }
        }

        Payroll payroll = new Payroll(hours, dependents, lifeInsurance);
        payroll.printStub();

        System.out.println("Thank you for using the Payroll Program!");
        scanner.close();
    }
}
