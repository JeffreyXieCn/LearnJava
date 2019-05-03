package com.luv2code.springboot.cruddemo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "employee")
@Getter // using Project Lombok
@Setter
@NoArgsConstructor
@ToString
@ApiModel(description = "Definition of resource/entity/model Employee")
public class Employee {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "first_name")
  @ApiModelProperty(notes = "First name should have at least 2 characters")
  private String firstName;

  @Column(name = "last_name")
  @ApiModelProperty(notes = "Last name should have at least 2 characters")
  private String lastName;

  @Column(name = "email")
  private String email;

  public Employee(String firstName, String lastName, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }
}
