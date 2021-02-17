package com.company;

import com.company.entities.Employee;
import com.company.services.JsonImporter;

import java.io.IOException;
import java.util.List;

public class Main {

    private static List<Employee> employees;

    public static void main(String[] args) {

        try {
            employees = JsonImporter.getEmployeeFromJson();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        employees.sort((o1, o2) -> {
            if (o1.calculateSalary() == o2.calculateSalary())
                return o1.getName().compareTo(o2.getName());

            return (int) (o2.calculateSalary() - o1.calculateSalary());
        });

        Employee.printEmployees(employees);
        System.out.println("\n--------------------------\n");
        Employee.printFirstFivesEmployees(employees);
        System.out.println("\n--------------------------\n");
        Employee.printLastThreeEmployeeIndexes(employees);
    }
}