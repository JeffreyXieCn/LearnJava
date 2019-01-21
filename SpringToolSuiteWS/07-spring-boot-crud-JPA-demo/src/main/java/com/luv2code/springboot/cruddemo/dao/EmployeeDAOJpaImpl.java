package com.luv2code.springboot.cruddemo.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.luv2code.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {

  // define field for entitymanager
  private EntityManager entityManager;

  // set up constructor injections
  @Autowired
  public EmployeeDAOJpaImpl(EntityManager em) {
    entityManager = em;
  }

  @Override
  public List<Employee> findAll() {
    // create the query
    Query theQuery = entityManager.createQuery("from Employee", Employee.class);

    // execute query and get result list
    List<Employee> employees = theQuery.getResultList();

    // return the list
    return employees;
  }

  @Override
  public Employee findById(int theId) {
    Employee theEmployee = entityManager.find(Employee.class, theId);
    return theEmployee;
  }

  @Override
  public void save(Employee theEmployee) {
    // save or update the employee
    Employee dbEmployee = entityManager.merge(theEmployee);

    // update with id from db ... so we can get generated id for save / insert
    // for insert, the id won't be set on theEmployee automatically
    theEmployee.setId(dbEmployee.getId());
  }

  @Override
  public void deleteById(int theId) {
    // delete object with primary key
    Query theQuery = entityManager.createQuery("delete from Employee where id=:employeeId");
    theQuery.setParameter("employeeId", theId);
    theQuery.executeUpdate();
  }
}
