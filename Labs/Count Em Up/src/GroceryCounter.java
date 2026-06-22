
public class GroceryCounter {

    private int count;
    private int overflowCount;
    private final int max;

    public GroceryCounter() {
        this.count = 0;
        this.overflowCount = 0;
        this.max = 9999;
    }

    // Add-on: Custom Starting Value
    public GroceryCounter(int startValue) {
        this.max = 9999;
        this.overflowCount = 0;
        if (startValue >= 0 && startValue <= 9999) {
            this.count = startValue;
        } else {
            this.count = 0;
        }
    }

    // Add-on: Custom Counter Maximum
    public GroceryCounter(int startValue, int max) {
        if (max <= 0) {
            this.max = 9999;
        } else {
            this.max = max;
        }
        this.overflowCount = 0;
        if (startValue >= 0 && startValue <= this.max) {
            this.count = startValue;
        } else {
            this.count = 0;
        }
    }


    public void tens() {
        increment(1000);
    }

    public void ones() {
        increment(100);
    }

    public void tenths() {
        increment(10);
    }

    public void hundreths() {
        increment(1);
    }

    // Add-on: Customized Increment
    public void increment(int amount) {
        count += amount;
        while (count > max) {
            count -= (max + 1);
            overflowCount++;
        }
    }

    // --- Decrement methods (Add-on) ---

    public void decrementTens() {
        decrement(1000);
    }

    public void decrementOnes() {
        decrement(100);
    }

    public void decrementTenths() {
        decrement(10);
    }

    public void decrementHundreths() {
        decrement(1);
    }

    private void decrement(int amount) {
        count -= amount;
        while (count < 0) {
            count += (max + 1);
            overflowCount++;
        }
    }


    public String total() {
        int dollars = count / 100;
        int cents = count % 100;
        return String.format("$%d.%02d", dollars, cents);
    }



    public int overflows() {
        return overflowCount;
    }



    public void clear() {
        count = 0;
        overflowCount = 0;
    }



    public int getCount() {
        return count;
    }
}
