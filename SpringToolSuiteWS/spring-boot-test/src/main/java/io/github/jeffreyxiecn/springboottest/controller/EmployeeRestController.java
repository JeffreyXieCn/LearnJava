package io.github.jeffreyxiecn.springboottest.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.jeffreyxiecn.springboottest.entity.Employee;
import io.github.jeffreyxiecn.springboottest.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

  @Autowired private EmployeeService employeeService;

  @GetMapping("/employees")
  public List<Employee> getAllEmployees() {
    return employeeService.getAllEmployees();
  }
}
