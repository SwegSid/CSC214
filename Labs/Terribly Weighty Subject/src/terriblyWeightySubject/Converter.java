package terriblyWeightySubject;

public class Converter {

    public String toPounds(int ounces) {
        double pounds = ounces / 16.0;
        return String.format("%.4f lbs", pounds);
    }

    public String toPoundsAndOunces(int ounces) {
        int pounds = ounces / 16;
        int remainingOunces = ounces % 16;
        return pounds + " lbs " + remainingOunces + " oz";
    }

    public String toOunces(int pounds, int ounces) {
        int totalOunces = pounds * 16 + ounces;
        return totalOunces + " oz";
    }
}