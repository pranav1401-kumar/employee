package com.example.myapp.repository;

import com.example.myapp.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e.departments.size, d.name FROM Employee e JOIN e.departments d GROUP BY d.name")
    List<Object[]> findEmployeeCountByDepartment();
}