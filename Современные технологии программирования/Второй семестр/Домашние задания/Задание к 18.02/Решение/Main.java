package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ArrayList<Employee> employees = new ArrayList<>() {{
            add(new HourlyRateEmployee(1, 15.5, "Bob"));
            add(new FixedRateEmployee(2, 455, "Mark"));
            add(new FixedRateEmployee(3, 455, "Olivia"));
            add(new HourlyRateEmployee(4, 15.5, "Alex"));
            add(new HourlyRateEmployee(5, 35.5, "Katya"));
        }};

        employees.sort((o1, o2) -> {
            if (o1.calculateSalary() == o2.calculateSalary())
                return o1.getName().compareTo(o2.getName());

            return (int) (o2.calculateSalary() - o1.calculateSalary());
        });

        System.out.println("------ Вывести идентификатор работника, имя и среднемесячную зарплату для всех элементов списка ------");
        for (Employee employee : employees) {
            System.out.println("[" + employee.getId() + "]: " + employee.getName() + " - " + String.format("%.2f", employee.calculateSalary()) + "$");
        }
        System.out.println();

        System.out.println("------ Вывести первые 5 имен работников из полученного выше списка ------");
        for (int index = 0; index < 5; index++) {
            Employee employee = employees.get(index);
            System.out.println(employee.getName());
        }
        System.out.println();

        System.out.println("------ Вывести последние 3 идентификатора работников из полученного више списка ------");
        for (int index = employees.size() - 3; index < employees.size(); index++) {
            Employee employee = employees.get(index);
            System.out.println(employee.getId());
        }
    }
}
