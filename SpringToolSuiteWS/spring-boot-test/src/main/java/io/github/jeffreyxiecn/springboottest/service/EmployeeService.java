package io.github.jeffreyxiecn.springboottest.service;

import java.util.List;
import io.github.jeffreyxiecn.springboottest.entity.Employee;

public interface EmployeeService {
  Employee getEmployeeByName(String name);

  List<Employee> getAllEmployees();
}
