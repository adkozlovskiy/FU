package com.company.entities;

import java.util.List;

public abstract class Employee {

    private final int id;
    private final String name;

    Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * @return id работника.
     */
    private int getId() {
        return id;
    }

    /**
     * @return имя работника.
     */
    public String getName() {
        return name;
    }

    /**
     * Метод, рассчитывающий заработную плату.
     * Переопределен в {@link HourlyRateEmployee} и {@link FixedRateEmployee}.
     *
     * @return заработную плату сотрулника.
     */
    public abstract double calculateSalary();

    /**
     * Вывести идентификатор работника, имя и среднемесячную зарплату для всех элементов списка
     *
     * @param employees - список объектов-наследников от {@link Employee}.
     */
    public static void printEmployees(List<Employee> employees) {
        for (Employee employee : employees)
            System.out.println("[" + employee.getId() + "]: "
                    + employee.getName() + " - "
                    + String.format("%.2f", employee.calculateSalary()) + "$");
    }

    /**
     * Вывести первые 5 имен работников из списка.
     *
     * @param employees - список объектов-наследников от {@link Employee}.
     */
    public static void printFirstFivesEmployees(List<Employee> employees) {
        for (int index = 0; index < 5; index++) {
            Employee employee = employees.get(index);
            System.out.println(employee.getName());
        }
    }

    /**
     * Вывести последние 3 идентификатора работников из полученного више списка.
     *
     * @param employees - список объектов-наследников от {@link Employee}.
     */
    public static void printLastThreeEmployeeIndexes(List<Employee> employees) {
        for (int index = employees.size() - 3; index < employees.size(); index++) {
            Employee employee = employees.get(index);
            System.out.println(employee.getId());
        }
    }
}