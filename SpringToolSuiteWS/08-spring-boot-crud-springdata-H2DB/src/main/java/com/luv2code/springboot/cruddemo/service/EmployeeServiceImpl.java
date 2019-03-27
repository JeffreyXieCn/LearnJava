package com.luv2code.springboot.cruddemo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.luv2code.springboot.cruddemo.dao.EmployeeRepository;
import com.luv2code.springboot.cruddemo.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  private EmployeeRepository employeeRepository;

  @Autowired
  public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository) {
    System.out.println(
        "In EmployeeServiceImpl's constructor: theEmployeeRepository.getClass().getName() = "
            + theEmployeeRepository.getClass().getName());
    employeeRepository = theEmployeeRepository;
  }

  @Override
  public List<Employee> findAll() {
    return employeeRepository.findAll();
  }

  @Override
  public Optional<Employee> findById(int theId) {
    return employeeRepository.findById(theId);
  }

  @Override
  public Optional<Employee> findByLastName(String lastName) {
    return employeeRepository.findByLastName(lastName);
  }

  @Override
  public void save(Employee theEmployee) {
    employeeRepository.save(theEmployee);
  }

  @Override
  @Transactional
  public void deleteById(int theId) {
    employeeRepository.deleteById(theId);
  }
}
