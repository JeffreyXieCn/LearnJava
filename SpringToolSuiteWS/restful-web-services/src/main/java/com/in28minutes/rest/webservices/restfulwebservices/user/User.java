package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
// @AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "All details about the user.")
@Entity
public class User {

  @Id @GeneratedValue private Integer id;

  @Size(min = 2, message = "Name should have at least 2 characters")
  @ApiModelProperty(notes = "Name should have at least 2 characters")
  private String name;

  @Past
  @ApiModelProperty(notes = "Birth date should be in the past")
  private Date birthDate;

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  @ToString.Exclude
  private List<Post> posts;

  public User(Integer id, String name, Date birthDate) {
    this.id = id;
    this.name = name;
    this.birthDate = birthDate;
  }
}
