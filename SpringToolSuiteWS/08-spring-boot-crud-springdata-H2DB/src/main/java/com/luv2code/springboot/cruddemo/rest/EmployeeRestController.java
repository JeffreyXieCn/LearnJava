package com.luv2code.springboot.cruddemo.rest;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

  private EmployeeService employeeService;

  @Autowired // this is optional if there is one and only one constructor
  public EmployeeRestController(EmployeeService theEmployeeService) {
    employeeService = theEmployeeService;
  }

  // expose "/employees" and return list of employees
  @GetMapping("/employees")
  public List<Employee> findAll() {
    return employeeService.findAll();
  }

  @GetMapping("/employees/{employeeId}")
  public Employee findById(@PathVariable int employeeId) {
    Optional<Employee> theEmployee = employeeService.findById(employeeId);
    if (!theEmployee.isPresent()) {
      throw new RuntimeException("Employee id not found - " + employeeId);
    }

    return theEmployee.get();
  }

  @GetMapping("/employees/search")
  public Employee findById(@RequestParam("lastName") String employeeLastName) {
    Optional<Employee> theEmployee = employeeService.findByLastName(employeeLastName);
    if (!theEmployee.isPresent()) {
      throw new RuntimeException("Employee with last name " + employeeLastName + " not found");
    }

    return theEmployee.get();
  }

  // add mapping for POST /employees - add new employee
  @PostMapping("/employees")
  public Employee addEmployee(@RequestBody Employee theEmployee) {
    // also just in case they pass an id in JSON ... set id to 0
    // this is to force a save of new item ... instead of update
    theEmployee.setId(0);
    employeeService.save(theEmployee);

    return theEmployee;
  }

  // add mapping for PUT /employees - update existing employee
  @PutMapping("/employees")
  public Employee updateEmployee(@RequestBody Employee theEmployee) {
    employeeService.save(theEmployee);

    return theEmployee;
  }

  @DeleteMapping("/employees/{employeeId}")
  public String deleteById(@PathVariable int employeeId) {
    Optional<Employee> theEmployee = employeeService.findById(employeeId);
    if (!theEmployee.isPresent()) {
      throw new RuntimeException("Employee id not found - " + employeeId);
    }

    employeeService.deleteById(employeeId);
    return "Deleted employee with id - " + employeeId;
  }
}
