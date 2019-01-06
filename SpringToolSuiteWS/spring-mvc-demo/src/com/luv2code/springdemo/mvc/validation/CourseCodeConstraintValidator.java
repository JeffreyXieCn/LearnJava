package com.luv2code.springdemo.mvc.validation;

import java.util.stream.Stream;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode, String> {

  private String[] coursePrefix;

  @Override
  public void initialize(CourseCode theCourseCode) {
    coursePrefix = theCourseCode.value();
  }

  @Override
  public boolean isValid(String theCode, ConstraintValidatorContext theConstraintValidatorContext) {
    if (theCode != null) {
      return Stream.of(coursePrefix).anyMatch(theCode::startsWith);
      // return theCode.startsWith(coursePrefix);
    }
    return true;
  }
}
