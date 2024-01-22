package com.techelevator;

import org.junit.Assert;
import org.junit.Test;
import com.techelevator.crm.Pet;

import java.util.ArrayList;
import java.util.List;

public class PetTest {

    @Test
    public void listVaccinations_returns_correctly_concatenated_String() {
        // Arrange
        Pet whiskers = new Pet("Whiskers", "Cat");
        List<String> inputVax = new ArrayList<>();

        inputVax.add("Rabies");
        inputVax.add("Parvo");
        inputVax.add("Distemper");

        whiskers.setVaccinations(inputVax);

        // Act
        String expected = "Rabies, Parvo, Distemper";
        String actual = whiskers.listVaccinations();

        // Assert
        Assert.assertEquals(expected, actual);
    }
}
