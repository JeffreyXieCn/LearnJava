package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class DeleteDemo {

  public static void main(String[] args) {
    // create session factory
    SessionFactory factory =
        new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Instructor.class)
            .addAnnotatedClass(InstructorDetail.class)
            .buildSessionFactory();

    // create session
    Session session = factory.getCurrentSession();

    try {

      // start a transaction
      session.beginTransaction();

      // get the instructor by primary key / id
      int theId = 1;
      Instructor tempInstructor = session.get(Instructor.class, theId);

      System.out.println("Found instructor: " + tempInstructor);

      // delete the instructor
      if (tempInstructor != null) {
        System.out.println("Deleting: " + tempInstructor);
        // Note: will ALSO delete associated "details" object because of CascadeType.ALL
        session.delete(tempInstructor);
      }

      // commit transaction
      session.getTransaction().commit();

      System.out.println("Done!");
    } finally {
      factory.close();
    }
  }
}

/*
FAQ: How To View Hibernate SQL Parameter Values
Section 21, Lecture 195
FAQ: How To View Hibernate SQL Parameter Values

Question:

I see hibernate printing out the query parameters as ? in the console.
Is it possible to printout the value that was actually queried on the
database. Asking as this would help in the debugging purpose.

Answer:

When using Hibernate, if you log the Hibernate SQL statements, you will see this:

Hibernate: insert into student (email, first_name, last_name, id) values (?, ?, ?, ?)

However, for debugging your application, you want to see the actual parameter values in the Hibernate logs. Basically, you want to get rid of the question marks in the Hibernate logs.

You can view the actual parameters by viewing the low-level trace of the Hibernate logs. This is not set up by default. However, we can add log4j to allow us to see these low-level logs.



Here is an overview of the process:
1. Add log4j to your project classpath

2. Add log4j.properties to your ��src�� directory



Here are the detailed steps:
1. Add log4j to your project classpath

1a. Download log4j v1.2.17 from this link: �C http://central.maven.org/maven2/log4j/log4j/1.2.17/log4j-1.2.17.jar

1b. Copy this file to your project��s lib directory




1c. Right-click your Eclipse project and select Properties

1d. Select Build Path > Libraries > Add JARS��

1e. Select the log4j-1.2.17.jar file from the lib directory




2. Add log4j.properties to your ��src�� directory

2a. Copy the text from below

# Root logger option
log4j.rootLogger=DEBUG, stdout

# Redirect log messages to console
log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.Target=System.out
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n

log4j.logger.org.hibernate=TRACE
2b. Save this file as "log4j.properties" in your ��src�� directory




Note: This file has an important setting:

log4j.logger.org.hibernate=TRACE

This allows you see a low-level trace of Hibernate and this allows you see the real SQL parameter values.

Now run your application. You will see a lot of low-level TRACE logs in the Eclipse Console window.

Right-click in the Eclipse Console window and select Find/Replace��

Search for: binding parameter

or search for: extracted value

(the search string changes depending on which version of Hibernate you are using)

You will see the logs with the real parameter values. Congrats!

*/
