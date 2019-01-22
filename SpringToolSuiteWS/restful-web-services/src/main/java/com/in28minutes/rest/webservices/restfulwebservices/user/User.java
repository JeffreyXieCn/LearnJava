package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.util.Date;
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
  private String name;
  private Date birthDate;
}
