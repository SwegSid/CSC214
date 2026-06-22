package terriblyWeightySubject;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestConverter {
    // In this lab, I will write the tests for you to give you
    // an example of what they should look like. In the future,
    // you will be responsible for writing the tests. Update the
    // Converter class to make these tests pass.

    Converter converter;

    @BeforeEach
    void setUp() {
        converter = new Converter();
    }

    @Test
    public void testZeroOuncesToPounds() {
        assertEquals("0.0000 lbs", converter.toPounds(0));
    }

    @Test
    public void test1OunceToPounds() {
        assertEquals("0.0625 lbs", converter.toPounds(1));
    }

    @Test
    public void test2OuncesToPounds() {
        assertEquals("0.1250 lbs", converter.toPounds(2));
    }

    @Test
    public void test16OuncesToPounds() {
        // Normally, we would write "1.0000 lb" (singular), but the acceptance
        // criteria say that you can use "lbs" for everything for now. There
        // is an add-on for this lab where you update the functions to use "lb"
        // vs "lbs" appropriately. Update this test if you decide to implement
        // that add-on.
        assertEquals("1.0000 lbs", converter.toPounds(16));
    }

    @Test
    public void test24OuncesToPounds() {
        assertEquals("1.5000 lbs", converter.toPounds(24));
    }

    @Test
    public void test32OuncesToPounds() {
        assertEquals("2.0000 lbs", converter.toPounds(32));
    }

    @Test
    public void testZeroOuncesToPoundsAndOunces() {
        assertEquals("0 lbs 0 oz", converter.toPoundsAndOunces(0));
    }

    @Test
    public void test1OunceToPoundsAndOunces() {
        assertEquals("0 lbs 1 oz", converter.toPoundsAndOunces(1));
    }

    @Test
    public void test2OuncesToPoundsAndOunces() {
        assertEquals("0 lbs 2 oz", converter.toPoundsAndOunces(2));
    }

    @Test
    public void test16OuncesToPoundsAndOunces() {
        // Normally, we would write "1 lb" (singular), but the acceptance
        // criteria say that you can use "lbs" for everything for now. There
        // is an add-on for this lab where you update the functions to use "lb"
        // vs "lbs" appropriately. Update this test if you decide to implement
        // that add-on.
        assertEquals("1 lbs 0 oz", converter.toPoundsAndOunces(16));
    }

    @Test
    public void test24OuncesToPoundsAndOunces() {
        // Normally, we would write "1 lb" (singular), but the acceptance
        // criteria say that you can use "lbs" for everything for now. There
        // is an add-on for this lab where you update the functions to use "lb"
        // vs "lbs" appropriately. Update this test if you decide to implement
        // that add-on.
        assertEquals("1 lbs 8 oz", converter.toPoundsAndOunces(24));
    }

    @Test
    public void test32OuncesToPoundsAndOunces() {
        assertEquals("2 lbs 0 oz", converter.toPoundsAndOunces(32));
    }
}