package io.github.jeffreyxiecn.io.sonder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UnitWithDistance implements Comparable<UnitWithDistance> {
  private Unit unit;
  private double distance;

  @Override
  public int compareTo(UnitWithDistance o) {
    return Double.compare(distance, o.getDistance());
  }
}
