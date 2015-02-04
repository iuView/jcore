package com.iview.visitor.model;

/**
 * Created by clara on 2/3/15.
 */
public abstract class Employee{
    String name;

    public abstract void accept(EmployeeVisitor v);

    public Employee(String name) { this.name = name; }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
