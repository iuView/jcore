package com.iview.visitor.model;

/**
 * Created by clara on 2/3/15.
 */
public class Manager extends Employee{
    int salary;
    String manages;

    public Manager(String name, String manges) {
        super(name);
        this.manages = manges;
    }


    public String getManages() {
        return manages;
    }

    public void setManages(String manages) {
        this.manages = manages;
    }

    @Override
    public void accept(EmployeeVisitor v) {
        v.visit(this);
    }
}
