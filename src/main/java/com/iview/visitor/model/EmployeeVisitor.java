package com.iview.visitor.model;

/**
 * Created by clara on 2/3/15.
 */
public interface EmployeeVisitor {
    public void visit(Manager m);

    public void visit(NonManager n);
}
