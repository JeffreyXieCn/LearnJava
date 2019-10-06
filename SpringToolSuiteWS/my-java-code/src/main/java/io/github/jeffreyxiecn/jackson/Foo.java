package io.github.jeffreyxiecn.jackson;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Foo {
  private int id;
  private String firstName;
  private List<String> hobbies;
}
