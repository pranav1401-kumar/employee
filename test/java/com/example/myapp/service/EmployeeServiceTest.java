package com.example.myapp.service;

import com.example.myapp.model.Department;
import com.example.myapp.model.Employee;
import com.example.myapp.repository.EmployeeRepository;
import com.example.myapp.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    public void testSaveEmployee() {
        Employee employee = new Employee(0, null);
        employee.setName("John Doe");
        when(employeeRepository.save(employee)).thenReturn(employee);

        Employee savedEmployee = employeeService.saveEmployee(employee);
        assertEquals("John Doe", savedEmployee.getName());
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    public void testGetAllEmployees() {
        List<Employee> employees = Arrays.asList(
                new Employee(1L, "John Doe"),
                new Employee(2L, "Jane Smith")
        );
        when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> result = employeeService.getAllEmployees();
        assertEquals(2, result.size());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    public void testGetEmployeeCountByDepartment() {
        List<Object[]> resultList = Arrays.asList(
                new Object[]{2L, "IT"},
                new Object[]{1L, "HR"}
        );
        when(employeeRepository.findEmployeeCountByDepartment()).thenReturn(resultList);

        Map<String, Long> expected = new HashMap<>();
        expected.put("IT", 2L);
        expected.put("HR", 1L);

        Map<String, Long> result = employeeService.getEmployeeCountByDepartment();
        assertEquals(expected, result);
        verify(employeeRepository, times(1)).findEmployeeCountByDepartment();
    }

    // Add tests for other methods
}