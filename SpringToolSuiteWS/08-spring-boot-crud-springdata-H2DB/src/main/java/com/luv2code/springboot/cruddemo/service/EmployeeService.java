package com.luv2code.springboot.cruddemo.service;

import java.util.List;
import java.util.Optional;
import com.luv2code.springboot.cruddemo.entity.Employee;

public interface EmployeeService {
  List<Employee> findAll();

  Optional<Employee> findById(int theId);

  Optional<Employee> findByLastName(String lastName);

  void save(Employee theEmployee);

  void deleteById(int theId);
}
