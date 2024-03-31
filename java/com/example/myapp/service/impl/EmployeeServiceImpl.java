package com.example.myapp.service.impl;

import com.example.myapp.model.Employee;
import com.example.myapp.repository.EmployeeRepository;
import com.example.myapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow();
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Map<String, Long> getEmployeeCountByDepartment() {
        List<Object[]> resultList = employeeRepository.findEmployeeCountByDepartment();
        Map<String, Long> employeeCountByDepartment = new HashMap<>();
        for (Object[] result : resultList) {
            employeeCountByDepartment.put((String) result[1], ((Number) result[0]).longValue());
        }
        return employeeCountByDepartment;
    }
}