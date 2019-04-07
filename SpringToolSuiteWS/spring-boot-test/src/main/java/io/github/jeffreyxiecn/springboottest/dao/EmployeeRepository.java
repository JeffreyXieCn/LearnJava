package io.github.jeffreyxiecn.springboottest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import io.github.jeffreyxiecn.springboottest.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
  public Employee findByName(String name);
}
