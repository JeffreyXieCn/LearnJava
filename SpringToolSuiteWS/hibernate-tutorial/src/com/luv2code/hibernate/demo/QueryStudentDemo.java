package com.luv2code.hibernate.demo;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.luv2code.hibernate.demo.entity.Student;

public class QueryStudentDemo {

  public static void main(String[] args) {
    // create session factory
    SessionFactory factory =
        new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Student.class)
            .buildSessionFactory();

    // create session
    Session session = factory.getCurrentSession();

    try {
      // start a transaction
      session.beginTransaction();

      // query students
      List<Student> theStudents = session.createQuery("from Student").getResultList();

      // display the students
      theStudents.forEach(System.out::println);

      // query students: lastName='Doe'
      theStudents = session.createQuery("from Student s where s.lastName='Doe'").getResultList();
      // display the students
      System.out.println("\n\nStudents who have last name of Doe");
      theStudents.forEach(System.out::println);

      // query students: lastName='Doe' OR firstName='Daffy'
      theStudents =
          session
              .createQuery("from Student s where s.lastName='Doe' OR s.firstName='Daffy'")
              .getResultList();
      // display the students
      System.out.println("\n\nStudents who have last name of Doe or first name Daffy");
      theStudents.forEach(System.out::println);

      // query students: email ends with luv2code.com
      theStudents =
          session.createQuery("from Student s where s.email LIKE '%luv2code.com'").getResultList();
      // display the students
      System.out.println("\n\nStudents whose email ends with luv2code.com");
      theStudents.forEach(System.out::println);

      // commit transaction
      session.getTransaction().commit();

      System.out.println("Done!");
    } finally {
      factory.close();
    }
  }
}
