package com.dev.rohmts.crudsqlite.Pojo;

/**
 * Created by Rohmats on 11/9/2017.
 */

public class Employee {

    private long empId;
    private String firstName, lastName, salary;

    public Employee() {
    }

    public long getEmpId() {
        return empId;
    }

    public void setEmpId(long empId) {
        this.empId = empId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String toString() {
        return empId+". "+firstName+" "+lastName+" - Rp."+salary;
    }
}
