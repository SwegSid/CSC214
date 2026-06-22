package payroll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestPayroll {

    Payroll payroll;

    @BeforeEach
    void setUp() {
        payroll = new Payroll();
    }

    // --- calculateGrossPay() ---

    @Test
    public void testGrossPayNoOvertime() {
        // 30 hours * $16.78 = $503.40
        assertEquals(503.40, payroll.calculateGrossPay(30), 0.01);
    }

    @Test
    public void testGrossPayExactly40Hours() {
        // 40 * 16.78 = $671.20
        assertEquals(671.20, payroll.calculateGrossPay(40), 0.01);
    }

    @Test
    public void testGrossPayWithOvertime() {
        // 40 * 16.78 + 5 * (16.78 * 1.5) = 671.20 + 125.85 = 797.05
        assertEquals(797.05, payroll.calculateGrossPay(45), 0.01);
    }

    @Test
    public void testGrossPayZeroHours() {
        assertEquals(0.0, payroll.calculateGrossPay(0), 0.01);
    }

    // --- calculateSocialSecurity() ---

    @Test
    public void testSocialSecurity() {
        // 6% of $503.40 = $30.20
        assertEquals(30.20, payroll.calculateSocialSecurity(503.40), 0.01);
    }

    @Test
    public void testSocialSecurityZeroGross() {
        assertEquals(0.0, payroll.calculateSocialSecurity(0), 0.01);
    }

    // --- calculateFederalTax() ---

    @Test
    public void testFederalTax() {
        // 14% of $503.40 = $70.48
        assertEquals(70.48, payroll.calculateFederalTax(503.40), 0.01);
    }

    @Test
    public void testFederalTaxZeroGross() {
        assertEquals(0.0, payroll.calculateFederalTax(0), 0.01);
    }

    // --- calculateStateTax() ---

    @Test
    public void testStateTax() {
        // 5% of $503.40 = $25.17
        assertEquals(25.17, payroll.calculateStateTax(503.40), 0.01);
    }

    @Test
    public void testStateTaxZeroGross() {
        assertEquals(0.0, payroll.calculateStateTax(0), 0.01);
    }

    // --- calculateInsurance() ---

    @Test
    public void testInsuranceLessThanThreeDependents() {
        assertEquals(15.0, payroll.calculateInsurance(0), 0.01);
    }

    @Test
    public void testInsuranceTwoDependents() {
        assertEquals(15.0, payroll.calculateInsurance(2), 0.01);
    }

    @Test
    public void testInsuranceThreeOrMoreDependents() {
        assertEquals(35.0, payroll.calculateInsurance(3), 0.01);
    }

    @Test
    public void testInsuranceFourDependents() {
        assertEquals(35.0, payroll.calculateInsurance(4), 0.01);
    }

    // --- calculateNetPay() ---

    @Test
    public void testNetPayNoOvertime() {
        // gross=503.40, socSec=30.20, fed=70.48, state=25.17, union=10, ins=35
        // net = 503.40 - 30.20 - 70.48 - 25.17 - 10.00 - 35.00 = 332.55
        double gross = payroll.calculateGrossPay(30);
        double socSec = payroll.calculateSocialSecurity(gross);
        double fed = payroll.calculateFederalTax(gross);
        double state = payroll.calculateStateTax(gross);
        double ins = payroll.calculateInsurance(4);
        assertEquals(332.55, payroll.calculateNetPay(gross, socSec, fed, state, 10.0, ins), 0.01);
    }

    @Test
    public void testNetPayWithOvertime() {
        // gross=797.05, ins=15 (0 dependents)
        double gross = payroll.calculateGrossPay(45);
        double socSec = payroll.calculateSocialSecurity(gross);
        double fed = payroll.calculateFederalTax(gross);
        double state = payroll.calculateStateTax(gross);
        double ins = payroll.calculateInsurance(0);
        double expected = gross - socSec - fed - state - 10.0 - ins;
        assertEquals(expected, payroll.calculateNetPay(gross, socSec, fed, state, 10.0, ins), 0.01);
    }
}
