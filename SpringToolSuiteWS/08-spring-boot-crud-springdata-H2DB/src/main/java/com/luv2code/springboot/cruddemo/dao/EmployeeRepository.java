package com.luv2code.springboot.cruddemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.luv2code.springboot.cruddemo.entity.Employee;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
  // that's it ... no need to write any code LOL!
  // we get the CRUD methods for free thanks to Spring Data

    public Optional<Employee> findByLastName(String lastName);
}
