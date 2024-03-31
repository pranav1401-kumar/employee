package com.example.myapp.repository;

import com.example.myapp.model.Department;
import com.example.myapp.model.Employee;

import org.assertj.core.api.EnumerableAssert;
import org.assertj.core.api.ListAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    public void testSaveEmployeeWithDepartments() {
        Department department1 = new Department();
        department1.setName("IT");
        Department department2 = new Department();
        department2.setName("HR");

        departmentRepository.saveAll(List.of(department1, department2));

        Employee employee = new Employee();
        employee.setName("John Doe");
        Set<Department> departments = new HashSet<>();
        departments.add(department1);
        departments.add(department2);
        employee.setDepartments(departments);

        Employee savedEmployee = employeeRepository.save(employee);

        assertThat(savedEmployee.getId()).isNotNull();
        assertThat(savedEmployee.getName()).isEqualTo("John Doe");
        ((EnumerableAssert<ListAssert<Object[]>, Object[]>) assertThat(savedEmployee.getDepartments())).hasSize(2);
    }

    @Test
    public void testFindEmployeeCountByDepartment() {
        // Prepare data
        Department department1 = new Department();
        department1.setName("IT");
        Department department2 = new Department();
        department2.setName("HR");

        departmentRepository.saveAll(List.of(department1, department2));

        Employee employee1 = new Employee(0, null);
        employee1.setName("John Doe");
        Set<Department> departments1 = new HashSet<>();
        departments1.add(department1);
        employee1.setDepartments(departments1);

        Employee employee2 = new Employee();
        employee2.setName("Jane Smith");
        Set<Department> departments2 = new HashSet<>();
        departments2.add(department1);
        departments2.add(department2);
        employee2.setDepartments(departments2);

        employeeRepository.saveAll(List.of(employee1, employee2));

        List<Object[]> result = employeeRepository.findEmployeeCountByDepartment();

        assertThat(result).hasSize(2);
        
        // Extract counts from the result
        Long itDepartmentCount = (Long) result.get(0)[0];
        Long hrDepartmentCount = (Long) result.get(1)[0];
        
        // Perform assertions
        assertThat(itDepartmentCount).isEqualTo(2L); // IT department has 2 employees
        assertThat(hrDepartmentCount).isEqualTo(1L); // HR department has 1 employee
    }
}