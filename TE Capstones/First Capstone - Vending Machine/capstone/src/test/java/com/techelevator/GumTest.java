package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

public class GumTest {
    private final Gum testGum = new Gum("D4", "Test Mint Gum", .05);

    // Just explicitly testing Getters
    @Test
    public void returns_D4_when_keyCode_is_D4() {
        String expected = "D4";
        String actual = testGum.getKeyCode();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void returns_Test_Mint_Gum_when_name_is_Test_Mint_Gum() {
        String expected = "Test Mint Gum";
        String actual = testGum.getName();

        Assert.assertEquals(expected, actual);
    }
    @Test
    public void returns_0_point_05_when_price_is_0_point_05() {
        double expected = 0.05;
        double actual = testGum.getPrice();

        Assert.assertEquals(expected, actual,0);
    }
    @Test
    public void returns_appropriate_flavor_text() {
        String expected = "Chew Chew, Yum!";
        String actual = testGum.getFlavorText();

        Assert.assertEquals(expected,actual);
    }
}
