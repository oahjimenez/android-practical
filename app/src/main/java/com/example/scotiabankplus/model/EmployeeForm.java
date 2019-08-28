package com.example.scotiabankplus.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class EmployeeForm {

    static EmployeeForm form;

    @Getter
    List<Employee> employees;

    public EmployeeForm() {
        employees = new ArrayList<>();
    }

    public static EmployeeForm getInstance() {
        if (form==null) {
            form = new EmployeeForm();
        }
        if (form.getEmployees().isEmpty()) {
            form.getEmployees().add(getSampleEmployee());
        }

        return form;
    }

    protected static Employee getSampleEmployee() {
        Employee employee = new Employee();
        employee.setNames("Speedy");
        employee.setFullnames("Gonzalez");
        return employee;
    }
}
