package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

public class ChipTest {
    // Creating test object
    private final Chip testChip = new Chip("A1", "Test Chip", 1.50);

    // Just explicitly testing Getters
    @Test
    public void returns_A1_when_keyCode_is_A1() {
        String expected = "A1";
        String actual = testChip.getKeyCode();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void returns_Test_Chip_when_name_is_Test_Chip() {
        String expected = "Test Chip";
        String actual = testChip.getName();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void returns_appropriate_flavor_text() {
        String expected = "Crunch Crunch, Yum!";
        String actual = testChip.getFlavorText();

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void returns_1_50_when_price_is_1_50() {
        double expected = 1.50;
        double actual = testChip.getPrice();

        Assert.assertEquals(expected, actual,0);
    }
}
