package com.iview.visitor.model;

/**
 * Created by clara on 2/3/15.
 */
public class NonManager extends Employee{
    public NonManager(String name) {
        super(name);
    }

    @Override
    public void accept(EmployeeVisitor v) {
        v.visit(this);
    }
}
