package com.example.myapp.controller;

import com.example.myapp.model.Department;
import com.example.myapp.model.Employee;
import com.example.myapp.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EmployeeService employeeService;

    @Test
    @WithMockUser
    public void testSaveEmployee() throws Exception {
        Employee employee = new Employee(0, null);
        employee.setName("John Doe");

        when(employeeService.saveEmployee(employee)).thenReturn(employee);

        mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    @WithMockUser
    public void testGetAllEmployees() throws Exception {
        List<Employee> employees = Arrays.asList(
                new Employee(1L, "John Doe"),
                new Employee(2L, "Jane Smith")
        );

        when(employeeService.getAllEmployees()).thenReturn(employees);

        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[1].name").value("Jane Smith"));
    }

    @Test
    @WithMockUser
    public void testGetEmployeeCountByDepartment() throws Exception {
        Map<String, Long> employeeCountByDepartment = new HashMap<>();
        employeeCountByDepartment.put("IT", 2L);
        employeeCountByDepartment.put("HR", 1L);

        when(employeeService.getEmployeeCountByDepartment()).thenReturn(employeeCountByDepartment);

        mockMvc.perform(get("/api/employees/count-by-department"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.IT").value(2L))
                .andExpect(jsonPath("$.HR").value(1L));
    }

    // Add tests for other controller methods
}