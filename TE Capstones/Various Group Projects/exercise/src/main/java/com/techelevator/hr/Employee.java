package com.techelevator.hr;

import com.techelevator.Billable;
import com.techelevator.Person;

import java.util.Map;

public class Employee extends Person implements Billable {

    private int employeeId;
    private String title;
    private Department department;
    private double salary;

    public Employee(String firstName, String lastName) {
        this(firstName, lastName, "", 0);
    }

    public Employee(String firstName, String lastName, String title, double salary) {
        super(firstName,lastName);
        this.title = title;
        this.salary = salary;
    }

    @Override
    public String getFullName() {
        return this.getLastName() + ", " + this.getFirstName();
    }

    public void raiseSalary(double percentage) {
        if( percentage > 0) {
            this.salary += salary * (percentage / 100);
        }
    }
    @Override
    public double getBalanceDue(Map<String, Double> serviceMap){
        double dueBalance = 0.0;

        /* We need to find out how much is owed for each service, but if it's Walking it's only half.
        The loop syntax means -> for each (String, that we're calling "service" : in serviceMap.keySet()) DO THIS <-
        We also have to add that value to the total.
         */
        for(String service : serviceMap.keySet()){
            // Quick check for Walking
            if(service.equals("Walking")){
                // 50% off! What a deal!
                dueBalance += (serviceMap.get(service) / 2);

            } else {
                dueBalance = dueBalance + serviceMap.get(service);
            }
        }

        return dueBalance;
    }


    // getters and setters

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

}
