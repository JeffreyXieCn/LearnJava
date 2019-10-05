package io.github.jeffreyxiecn.sonder;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Foo {
  private int id;
  private String firstName;
  private List<String> hobbies;
}
