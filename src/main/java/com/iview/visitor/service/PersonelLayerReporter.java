package com.iview.visitor.service;

import com.google.common.collect.ImmutableList;
import com.iview.visitor.model.Employee;
import com.iview.visitor.model.EmployeeVisitor;
import com.iview.visitor.model.Manager;
import com.iview.visitor.model.NonManager;

import java.util.List;

public class PersonelLayerReporter implements EmployeeVisitor{
    List<Employee> allEmployees = ImmutableList.of(new Manager("lisa", "bob"), new NonManager("bob"));
    public void report() {
        for (int i = 0; i < allEmployees.size(); i++) {
            allEmployees.get(i).accept(this);
        }
    }

    public static void main(String[] args) {
        PersonelLayerReporter reporter = new PersonelLayerReporter();
        reporter.report();
    }

    @Override
    public void visit(Manager m)
    {
        System.out.println(m.getName() + " manages " + m.getManages());
    }

    @Override
    public void visit(NonManager n) {
        System.out.println(n.getName() + " do not manage anyone");
    }
}
