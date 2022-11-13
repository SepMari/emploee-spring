package com.skypro.emploee.service;

import com.skypro.emploee.model.Employee;
import com.skypro.emploee.record.EmployeeRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {
    private final Map<Integer, Employee> employees = new HashMap<>();

    public Collection<Employee> getAllEmployees() {
        return this.employees.values();
    }

    public Employee addEmployee(EmployeeRequest employeeRequest) {
        if (employeeRequest.getFirstName() == null || employeeRequest.getLastName() == null) {
            throw new IllegalArgumentException("Неверно заполнены данные имя или фамилия!");
        }
        Employee employee = new Employee(employeeRequest.getFirstName(), employeeRequest.getLastName(), employeeRequest.getDepartment(), employeeRequest.getSalary());

        this.employees.put(employee.getId(), employee);
        return employee;
    }

    public int getSalarySum() {
        return employees.values().stream()
                .mapToInt(e -> e.getSalary())
                .sum();
    }

    public OptionalInt getSalaryMin() {
        return employees.values().stream()
                .mapToInt(Employee::getSalary)
                .min();
    }


    public OptionalInt getSalaryMax() {
        return employees.values().stream()
                .mapToInt(e -> e.getSalary())
                .max();
    }

    public OptionalDouble getSalaryAverage() {
        return employees.values().stream()
                .mapToInt(e -> e.getSalary())
                .average();
    }

}
