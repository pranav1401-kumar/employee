package com.example.myapp.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.function.IntPredicate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Employee {

    public Employee(long l, String string) {
		// TODO Auto-generated constructor stub
	}

	
	public Employee() {
		// TODO Auto-generated constructor stub
	}


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(
        name = "employee_department",
        joinColumns = @JoinColumn(name = "employee_id"),
        inverseJoinColumns = @JoinColumn(name = "department_id")
    )
    private Set<Department> departments = new HashSet<>();

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

	public void setName(String string) {
		// TODO Auto-generated method stub
		
	}

	public Object getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setDepartments(Set<Department> departments1) {
		// TODO Auto-generated method stub
		
	}


	public IntPredicate getId() {
		// TODO Auto-generated method stub
		return null;
	}


	public IntPredicate getDepartments() {
		// TODO Auto-generated method stub
		return null;
	}

    // Constructors, getters, and setters
}