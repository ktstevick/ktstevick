package com.techelevator;

public class Miscellaneous extends Item{
    private String keyCode;
    private String name;
    private double price;
    private String flavorText = "Whether this item is edible or not, please enjoy!";

    //Getters
    @Override
    public String getKeyCode() {
        return keyCode;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String getFlavorText() {
        return flavorText;
    }

    //Constructor
    public Miscellaneous(String keyCode, String name, double price) {
        this.keyCode = keyCode;
        this.name = name;
        this.price = price;
    }
}
