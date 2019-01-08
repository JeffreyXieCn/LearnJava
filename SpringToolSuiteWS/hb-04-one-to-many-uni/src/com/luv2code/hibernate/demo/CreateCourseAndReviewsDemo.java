package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;

public class CreateCourseAndReviewsDemo {

  public static void main(String[] args) {
    // create session factory
    SessionFactory factory =
        new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Instructor.class)
            .addAnnotatedClass(InstructorDetail.class)
            .addAnnotatedClass(Course.class)
            .addAnnotatedClass(Review.class)
            .buildSessionFactory();

    // create session
    Session session = factory.getCurrentSession();

    try {

      // start a transaction
      session.beginTransaction();

      // get the instructor from db
      //      int theId = 1;
      //      Instructor tempInstructor = session.get(Instructor.class, theId);

      // create some course
      Course tempCourse = new Course("Pacman - How To Score One Million Points");

      // add some reviews
      tempCourse.addReview(new Review("Great course ... loved it!"));
      tempCourse.addReview(new Review("Cool course, job well done"));
      tempCourse.addReview(new Review("What a dumb course, you are an idiot"));
      //      Course tempCourse2 = new Course("The Pinball Masterclass");
      //
      //      // add courses to instructor
      //      tempInstructor.add(tempCourse1);
      //      tempInstructor.add(tempCourse2);

      // save the course
      // session.save(tempInstructor); // this doesn't work
      // save the course ... and leverage the cascade all :-)
      System.out.println("Saving the course");
      System.out.println(tempCourse);
      System.out.println(tempCourse.getReviews());
      session.save(tempCourse);
      //      session.save(tempCourse2);

      // commit transaction
      session.getTransaction().commit();

      System.out.println("Done!");
    } finally {
      // add clean up code
      session.close();
      factory.close();
    }
  }
}
