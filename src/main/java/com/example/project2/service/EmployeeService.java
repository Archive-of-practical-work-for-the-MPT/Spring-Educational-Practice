package com.example.project2.service;

import com.example.project2.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAllEmployees();
    Employee createEmployee(Employee employee);
    Employee updateEmployee(Employee employee);
    Employee findEmployeeById(Long id);
    void deleteEmployee(Long id);
    Employee findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
}