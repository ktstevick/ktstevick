package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

public class DrinkTest {
    private final Drink testDrink = new Drink("C3", "Test Drink", 2.95);

    // Just explicitly testing Getters
    @Test
    public void returns_C3_when_keyCode_is_C3() {
        String expected = "C3";
        String actual = testDrink.getKeyCode();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void returns_Test_Drink_when_name_is_Test_Drink() {
        String expected = "Test Drink";
        String actual = testDrink.getName();

        Assert.assertEquals(expected, actual);
    }
    @Test
    public void returns_2_95_when_price_is_2_95() {
        double expected = 2.95;
        double actual = testDrink.getPrice();

        Assert.assertEquals(expected, actual,0);
    }
    @Test
    public void returns_appropriate_flavor_text() {
        String expected = "Glug Glug, Yum!";
        String actual = testDrink.getFlavorText();

        Assert.assertEquals(expected,actual);
    }
}
