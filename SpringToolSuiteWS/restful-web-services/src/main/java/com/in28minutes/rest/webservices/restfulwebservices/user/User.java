package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.util.Date;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {

  private Integer id;

  @Size(min = 2, message = "Name should have at least 2 characters")
  private String name;

  @Past private Date birthDate;
}