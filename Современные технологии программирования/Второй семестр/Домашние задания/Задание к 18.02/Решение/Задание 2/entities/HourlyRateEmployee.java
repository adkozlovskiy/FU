package com.company.entities;

public class HourlyRateEmployee extends Employee {

    private final double hourlyRate;

    public HourlyRateEmployee(int id, double hourlyRate, String name) {
        super(id, name);
        this.hourlyRate = hourlyRate;
    }

    /**
     * Метод, рассчитывающий заработную плату сотрудника с фиксированной ставкой.
     *
     * @return зарплату, расчитанную по формуле 20.8 * 8 * часовую ставку.
     */
    
    @Override
    public double calculateSalary() {
        return 20.8 * 8 * hourlyRate;
    }

}
