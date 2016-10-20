package ru.sbthome.model;

/**
 * Simple salary payment
 *
 * @version 1.0
 * @autor Trotsenko Konstantin
 */
public class SalaryPayment {
    private Long id;
    private String name;
    private Double salary;

    public SalaryPayment(Long id, String name, Double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getSalary() {
        return salary;
    }
}
