package com.luv2code.springdemo.rest;

import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.luv2code.springdemo.entity.Student;

@RestController
@RequestMapping("/api")
public class StudentRestController {
  private List<Student> students;

  // define @PostConstruct to load the student data ... only once!
  @PostConstruct
  public void loadData() {
    students =
        Arrays.asList(
            new Student("Poornima", "Patel"),
            new Student("Mario", "Rossi"),
            new Student("Mary", "Smith"));
  }

  // define endpoint for "/students" - return list of students
  @GetMapping("/students")
  public List<Student> getStudents() {
    return students;
  }

  // define endpoint for "/students/{studentId}" - return student at index
  @GetMapping("/students/{studentId}")
  public Student getStudent(@PathVariable int studentId) {
    // just index into the list ... keep it simple for now
    if (studentId >= students.size() || studentId < 0) {
      throw new StudentNotFoundException("Student id not found - " + studentId);
    }

    return students.get(studentId);
  }

  //  // Add an exception handler using @ExceptionHandler
  //  @ExceptionHandler
  //  public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) {
  //    // create a StudentErrorResponse
  //    StudentErrorResponse error = new StudentErrorResponse();
  //    error.setStatus(HttpStatus.NOT_FOUND.value());
  //    error.setMessage(exc.getMessage());
  //    error.setTimeStamp(System.currentTimeMillis());
  //
  //    // return ResponseEntity
  //    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  //  }
  //
  //  // add another exception handler ... to catch any exception (catch all)
  //  @ExceptionHandler
  //  public ResponseEntity<StudentErrorResponse> handleException(Exception exc) {
  //    // create a StudentErrorResponse
  //    StudentErrorResponse error = new StudentErrorResponse();
  //    error.setStatus(HttpStatus.BAD_REQUEST.value());
  //    error.setMessage(exc.getMessage());
  //    error.setTimeStamp(System.currentTimeMillis());
  //
  //    // return ResponseEntity
  //    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  //  }
}
