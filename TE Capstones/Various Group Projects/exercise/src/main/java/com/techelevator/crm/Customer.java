package com.techelevator.crm;

import com.techelevator.Billable;
import com.techelevator.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Customer extends Person implements Billable {

    private String phoneNumber;

    private List<Pet> pets = new ArrayList<>();

    public String getPhoneNumber(){
        return this.phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public List<Pet> getPets(){
        return this.pets;
    }
    public void setPets(List<Pet> pets){
        this.pets = pets;
    }
    //This is the constructor, we have to call the parent class first "Person" using "super" key word.
    public Customer(String firstName, String lastName){
        super(firstName, lastName);
        this.phoneNumber = "";
    }
    public Customer(String firstName, String lastname, String phoneNumber){
        super(firstName, lastname);
        this.phoneNumber = phoneNumber;
    }

    @Override
    public double getBalanceDue(Map<String, Double> serviceMap){
        double dueBalance = 0;

        //We need to access the values of each key and add to the balance
        for(String service : serviceMap.keySet()){
            dueBalance = dueBalance + serviceMap.get(service);
        }
        return dueBalance;
    }
}
