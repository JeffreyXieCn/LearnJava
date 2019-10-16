package io.github.jeffreyxiecn.io.sonder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class Unit {
  private int id;
  private String address;
  private float baths;
  private int beds;
  private String city;
  private int floor;
  private int has_elevator;
  private int has_parking;
  private int has_pool;
  private int has_view;
  private double lat;
  private double lng;
  private float square_feet;
}
