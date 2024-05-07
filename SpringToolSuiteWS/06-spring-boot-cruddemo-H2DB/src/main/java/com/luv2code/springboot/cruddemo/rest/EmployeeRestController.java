package com.luv2code.springboot.cruddemo.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
  private static final Logger log = LoggerFactory.getLogger(EmployeeRestController.class);
  private EmployeeService employeeService;

  @Autowired // this is optional if there is one and only one constructor
  public EmployeeRestController(EmployeeService theEmployeeService) {
    employeeService = theEmployeeService;
  }

  // expose "/employees" and return list of employees
  @GetMapping("/employees")
  public List<Employee> findAll() {
    log.info("Find all employees");
    return employeeService.findAll();
  }

  @GetMapping("/employees/{employeeId}")
  public Employee findById(@PathVariable int employeeId) {
    log.info("Find employee with id: {}", employeeId);
    Employee theEmployee = employeeService.findById(employeeId);
    if (theEmployee == null) {
      throw new EmployeeNotFoundException("Employee id not found - " + employeeId);
    }

    return theEmployee;
  }

  // add mapping for POST /employees - add new employee
  @PostMapping("/employees")
  @ResponseStatus(HttpStatus.CREATED)
  public Employee addEmployee(@RequestBody Employee theEmployee) {
    log.info("Adding employee: {}", theEmployee);
    if (theEmployee.getFirstName().length() < 2) {
      throw new RuntimeException("First name should have at least 2 characters");
    }

    if (theEmployee.getLastName().length() < 2) {
      throw new RuntimeException("Last name should have at least 2 characters");
    }

    // also just in case they pass an id in JSON ... set id to 0
    // this is to force a save of new item ... instead of update
    theEmployee.setId(0);
    employeeService.save(theEmployee);

    return theEmployee;
  }

  // add mapping for PUT /employees - update existing employee
  @PutMapping("/employees")
  public Employee updateEmployee(@RequestBody Employee theEmployee) {
    log.info("Updating employee: {}", theEmployee);
    employeeService.save(theEmployee);

    return theEmployee;
  }

  @DeleteMapping("/employees/{employeeId}")
  public String deleteById(@PathVariable int employeeId) {
    log.info("Deleting employee with id: {}", employeeId);
    Employee theEmployee = employeeService.findById(employeeId);
    if (theEmployee == null) {
      throw new EmployeeNotFoundException("Employee id not found - " + employeeId);
    }

    employeeService.deleteById(employeeId);
    return "Deleted employee with id - " + employeeId;
  }
}
