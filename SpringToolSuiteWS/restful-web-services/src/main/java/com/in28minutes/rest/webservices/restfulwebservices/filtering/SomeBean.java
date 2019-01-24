package com.in28minutes.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
// @JsonIgnoreProperties(value = {"field1"})
public class SomeBean {
  private String field1;
  private String field2;

  @JsonIgnore private String field3;
}
