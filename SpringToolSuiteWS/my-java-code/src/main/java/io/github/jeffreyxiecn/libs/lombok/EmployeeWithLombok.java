package io.github.jeffreyxiecn.libs.lombok;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class EmployeeWithLombok {
  private String firstName;
  private String lastName;
  private String email;
  private int age;
  private double salary;
}
