package com.techelevator.hr;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class EmployeeTests {

    @Test
    public void getFullNameReturnsCorrectFormat() {
        Employee employee = new Employee("Test", "Testerson");
        String fullName = employee.getFullName();

        assertEquals("The employee full name is not in the correct format.", "Testerson, Test", fullName);
    }

    @Test
    public void raiseSalaryTest_Positive() {
        Employee employee = new Employee("Test", "Testerson");
        employee.setSalary(100);
        employee.raiseSalary(5);

        assertEquals("The employee raise of 5% was not computed correctly.",employee.getSalary(), 100 * 1.05, 0.0);
    }

    @Test
    public void raiseSalaryTest_Negative() {
        Employee employee = new Employee("Test", "Testerson");
        employee.setSalary(100);
        employee.raiseSalary(-10); //"raise" by negative 10%

        assertEquals("Salary should remain the same when raise percentage is negative.",100, employee.getSalary(),0.0);
    }

    // Added test method. This needs to test getBalanceDue, which means we need a Map?
    @Test
    public void getBalanceDue_returns_100_when_Walking_is_200() {
        // Arrange
        Employee employee = new Employee("Test", "Testerson");
        Map <String, Double> servicesDue = new HashMap<>();

        servicesDue.put("Walking", 200.0);

        // Act
        double expected = 100.0;
        double actual = employee.getBalanceDue(servicesDue);

        // Assert. This "delta" parameter refers to acceptable variance... we had to use it because doubles have floating point decimals
        Assert.assertEquals(expected, actual, 0);

    }

    @Test
    public void getBalanceDue_returns_99_when_Walking_is_100_and_Grooming_is_49() {
        Employee employee = new Employee("Test", "Testerson");
        Map <String, Double> servicesDue = new HashMap<>();

        servicesDue.put("Walking", 100.0);
        servicesDue.put("Grooming", 49.0);

        double expected = 99.0;
        double actual = employee.getBalanceDue(servicesDue);

        Assert.assertEquals(expected, actual, 0);
    }

}
