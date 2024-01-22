package com.techelevator;

// Overarching Item class everything for sale in this Vending Machine inherits from
public abstract class Item {
    // We're not doing anything crazy here, just universal Getters essentially
    public abstract String getKeyCode();
    public abstract String getName();
    public abstract double getPrice();
    public abstract String getFlavorText();
}
