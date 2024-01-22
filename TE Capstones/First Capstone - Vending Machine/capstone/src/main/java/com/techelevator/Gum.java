package com.techelevator;

public class Gum extends Item{
    private String keyCode;
    private String name;
    private double price;
    private String flavorText = "Chew Chew, Yum!";

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
    public Gum(String keyCode, String name, double price) {
        this.keyCode = keyCode;
        this.name = name;
        this.price = price;
    }
}
