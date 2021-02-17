package com.company.entities;

public class FixedRateEmployee extends Employee {

    private final double fixedRate;

    public FixedRateEmployee(int id, double fixedRate, String name) {
        super(id, name);
        this.fixedRate = fixedRate;
    }

    /**
     * Метод, рассчитывающий заработную плату сотрудника с фиксированной ставкой.
     *
     * @return зарплату, которая равна ставке.
     */
    @Override
    public double calculateSalary() {
        return fixedRate;
    }
}