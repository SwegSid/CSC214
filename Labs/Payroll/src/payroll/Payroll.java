package payroll;

public class Payroll {
    private static final double HOURLY_RATE = 16.78;
    private static final double OVERTIME_MULTIPLIER = 1.5;
    private static final double SOCIAL_SECURITY_RATE = 0.06;
    private static final double FEDERAL_TAX_RATE = 0.14;
    private static final double STATE_TAX_RATE = 0.05;
    private static final double UNION_DUES = 10.00;
    private static final double INSURANCE_HIGH = 35.00;
    private static final double INSURANCE_LOW = 15.00;

    private double hours;
    private int dependents;
    private double lifeInsurance;

    private double grossPay;
    private double socialSecurity;
    private double federalTax;
    private double stateTax;
    private double insurance;

    public Payroll() {}

    public Payroll(double hours, int dependents, double lifeInsurance) {
        this.hours = hours;
        this.dependents = dependents;
        this.lifeInsurance = lifeInsurance;
        calculate();
    }

    public double calculateGrossPay(double hours) {
        if (hours <= 40) {
            return hours * HOURLY_RATE;
        } else {
            return (40 * HOURLY_RATE) + ((hours - 40) * HOURLY_RATE * OVERTIME_MULTIPLIER);
        }
    }

    public double calculateSocialSecurity(double gross) {
        return gross * SOCIAL_SECURITY_RATE;
    }

    public double calculateFederalTax(double gross) {
        return gross * FEDERAL_TAX_RATE;
    }

    public double calculateStateTax(double gross) {
        return gross * STATE_TAX_RATE;
    }

    public double calculateInsurance(int dependents) {
        return (dependents >= 3) ? INSURANCE_HIGH : INSURANCE_LOW;
    }

    public double calculateNetPay(double gross, double socSec, double fed, double state, double union, double ins) {
        double afterTax = gross - socSec - fed - state;
        double fixedCosts = union + ins;
        if (afterTax >= fixedCosts) {
            return afterTax - fixedCosts;
        } else {
            return afterTax;
        }
    }


    private void calculate() {
        if (hours <= 40) {
            grossPay = hours * HOURLY_RATE;
        } else {
            grossPay = (40 * HOURLY_RATE) + ((hours - 40) * HOURLY_RATE * OVERTIME_MULTIPLIER);
        }

        socialSecurity = grossPay * SOCIAL_SECURITY_RATE;
        federalTax = grossPay * FEDERAL_TAX_RATE;
        stateTax = grossPay * STATE_TAX_RATE;
        insurance = (dependents >= 3) ? INSURANCE_HIGH : INSURANCE_LOW;
    }

    public void printStub() {
        System.out.println("Payroll Stub:");
        System.out.println();
        System.out.printf("%8s   %.1f%n", "Hours:", hours);
        System.out.printf("%8s   %.2f $/hr%n", "Rate:", HOURLY_RATE);
        System.out.printf("%8s   $ %.2f%n", "Gross:", grossPay);
        System.out.println();

        double taxTotal = socialSecurity + federalTax + stateTax;
        double afterTax = grossPay - taxTotal;

        System.out.printf("%8s   $ %.2f%n", "SocSec:", socialSecurity);
        System.out.printf("%8s   $ %.2f%n", "FedTax:", federalTax);
        System.out.printf("%8s   $ %.2f%n", "StTax:", stateTax);

        // Check if enough after tax to cover union + insurance + life
        double fixedCosts = UNION_DUES + insurance + lifeInsurance;

        if (afterTax >= fixedCosts) {
            System.out.printf("%8s   $ %.2f%n", "Union:", UNION_DUES);
            System.out.printf("%8s   $ %.2f%n", "Ins:", insurance);
            if (lifeInsurance > 0) {
                System.out.printf("%8s   $ %.2f%n", "LifeIns:", lifeInsurance);
            }
            System.out.println();
            double net = afterTax - fixedCosts;
            System.out.printf("%8s   $ %.2f%n", "Net:", net);
        } else {
            //don't subtract what they can't cover
            System.out.println();
            System.out.printf("%8s   $ %.2f%n", "Net:", afterTax);
            System.out.println();
            System.out.println("The employee still owes:");
            System.out.println();
            System.out.printf("%8s   $ %.2f%n", "Union:", UNION_DUES);
            System.out.printf("%8s   $ %.2f%n", "Ins:", insurance);
            if (lifeInsurance > 0) {
                System.out.printf("%8s   $ %.2f%n", "LifeIns:", lifeInsurance);
            }
        }

        System.out.println();
    }
}
