package com.company;

public class FixedRateEmployee extends Employee {

    private final double fixedRate;

    public FixedRateEmployee(int id, double fixedRate, String name) {
        super(id, name);
        this.fixedRate = fixedRate;
    }

    @Override
    public double calculateSalary() {
        return fixedRate;
    }
}
