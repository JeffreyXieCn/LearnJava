package com.luv2code.springboot.cruddemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.luv2code.springboot.cruddemo.entity.Employee;

// Spring Data REST also provides for free:
// http://localhost:8080/magic-api/employees?page=1&size=3
// http://localhost:8080/magic-api/employees?sort=lastName,desc

// @RepositoryRestResource(path = "members")
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
  // that's it ... no need to write any code LOL!
  // we get the CRUD methods for free thanks to Spring Data
}
