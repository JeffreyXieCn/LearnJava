package com.luv2code.jackson.json.demo;

import java.io.File;
import java.util.Arrays;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Driver {

  public static void main(String[] args) {
    try {
      // create object mapper
      ObjectMapper mapper = new ObjectMapper();

      // read JSON file and map/convert to Java POJO: data/sample-lite.json
      // Student theStudent = mapper.readValue(new File("data/sample-lite.json"), Student.class);
      Student theStudent = mapper.readValue(new File("data/sample-full.json"), Student.class);

      // print first name and last name
      System.out.println("First name = " + theStudent.getFirstName());
      System.out.println("Last name = " + theStudent.getLastName());
      System.out.println("Address = " + theStudent.getAddress());
      System.out.println("Languages = " + Arrays.toString(theStudent.getLanguages()));

    } catch (Exception exc) {
      exc.printStackTrace();
    }
  }
}
