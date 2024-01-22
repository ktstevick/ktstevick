package com.techelevator;

import com.techelevator.crm.Customer;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class CustomerTest {

    @Test
    public void getBalanceDue_returns_233_when_Walking_100_Grooming_130_Soda_3() {
        // Arrange
        // What we need is a Customer, AND a service map
        Customer thisCustomer = new Customer("Testy", "Testerson");
        Map<String, Double> serviceMap = new HashMap<>();

        serviceMap.put("Walking", 100.0);
        serviceMap.put("Grooming", 130.0);
        serviceMap.put("Soda", 3.0);

        // Act
        double expected = 233.0;
        double actual = thisCustomer.getBalanceDue(serviceMap);

        // Assert
        Assert.assertEquals(expected, actual, 0);

    }

    // Constructo check for funsies
    @Test
    public void getBalanceDue_constructor_check_and_return_phoneNumber() {
        Customer thisCustomer = new Customer("Ulysses S", "Grant", "1776");

        String expected = "1776";
        String actual = thisCustomer.getPhoneNumber();

        Assert.assertEquals(expected, actual);
    }
}
