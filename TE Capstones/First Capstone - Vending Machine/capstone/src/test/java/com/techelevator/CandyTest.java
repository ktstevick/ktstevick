package com.techelevator;

import org.junit.Assert;
import org.junit.Test;
public class CandyTest {
    private final Candy testCandy = new Candy("B2", "Test Candy", .75);

    // Just explicitly testing Getters
    @Test
    public void returns_B2_when_keyCode_is_B2() {
        String expected = "B2";
        String actual = testCandy.getKeyCode();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void returns_Test_Candy_when_name_is_Test_Candy() {
        String expected = "Test Candy";
        String actual = testCandy.getName();

        Assert.assertEquals(expected, actual);
    }
    @Test
    public void returns_point_75_when_price_is_point_75() {
        double expected = .75;
        double actual = testCandy.getPrice();

        Assert.assertEquals(expected, actual,0);
    }
    @Test
    public void returns_appropriate_flavor_text() {
        String expected = "Munch Munch, Yum!";
        String actual = testCandy.getFlavorText();

        Assert.assertEquals(expected,actual);
    }
}
