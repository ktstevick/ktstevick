package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

public class MiscellaneousTest {
    // It would be nice to write a test that ensures the sale of this item is legal. Alas, not in our wheelhouse yet
    private final Miscellaneous testMisc = new Miscellaneous("E5", "Test Shrek DVD", 15.00);

    // Just explicitly testing Getters
    @Test
    public void returns_E5_when_keyCode_is_E5() {
        String expected = "E5";
        String actual = testMisc.getKeyCode();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void returns_Test_Shrek_DVD_when_name_is_Test_Shrek_DVD() {
        String expected = "Test Shrek DVD";
        String actual = testMisc.getName();

        Assert.assertEquals(expected, actual);
    }
    @Test
    public void returns_15_00_when_price_is_15_00() {
        double expected = 15.00;
        double actual = testMisc.getPrice();

        Assert.assertEquals(expected, actual,0);
    }
    @Test
    public void returns_appropriate_flavor_text() {
        String expected = "Whether this item is edible or not, please enjoy!";
        String actual = testMisc.getFlavorText();

        Assert.assertEquals(expected,actual);
    }
}