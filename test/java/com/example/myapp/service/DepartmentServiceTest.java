package com.example.myapp.service;

import com.example.myapp.model.Department;
import com.example.myapp.repository.DepartmentRepository;
import com.example.myapp.service.impl.DepartmentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @Test
    public void testSaveDepartment() {
        Department department = new Department();
        department.setName("IT");
        when(departmentRepository.save(department)).thenReturn(department);

        Department savedDepartment = departmentService.saveDepartment(department);
        assertEquals("IT", savedDepartment.getName());
        verify(departmentRepository, times(1)).save(department);
    }

    @Test
    public void testGetAllDepartments() {
        List<Department> departments = Arrays.asList(
                new Department(),
                new Department()
        );
        when(departmentRepository.findAll()).thenReturn(departments);

        List<Department> result = departmentService.getAllDepartments();
        assertEquals(2, result.size());
        verify(departmentRepository, times(1)).findAll();
    }

    // Add tests for other methods
}