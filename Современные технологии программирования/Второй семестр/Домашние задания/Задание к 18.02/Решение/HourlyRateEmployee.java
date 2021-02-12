package com.company;

public class HourlyRateEmployee extends Employee {

    private final double hourlyRate;

    public HourlyRateEmployee(int id, double hourlyRate, String name) {
        super(id, name);
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculateSalary() {
        return 20.8 * 8 * hourlyRate;
    }

}
