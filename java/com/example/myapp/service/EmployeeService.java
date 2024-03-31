package com.example.myapp.service;

import com.example.myapp.model.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Employee getEmployeeById(Long id);
    void deleteEmployee(Long id);
    Map<String, Long> getEmployeeCountByDepartment();
}