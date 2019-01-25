package com.in28minutes.rest.webservices.restfulwebservices.user;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Post {
  @Id @GeneratedValue private Integer id;

  @NotNull
  @Size(max = 255, message = "Description can't be more than 255 characters")
  private String description;

  @ToString.Exclude
  @ManyToOne(fetch = FetchType.LAZY)
  @JsonIgnore // very important to avoid recursive reference
  private User user;
}
