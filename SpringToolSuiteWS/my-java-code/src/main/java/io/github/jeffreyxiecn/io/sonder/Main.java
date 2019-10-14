package io.github.jeffreyxiecn.io.sonder;

import java.io.File;
import java.io.IOException;
import java.util.List;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {

  public static void main(String[] args)
      throws JsonParseException, JsonMappingException, IOException {
    // TODO Auto-generated method stub
    ObjectMapper om = new ObjectMapper();
    List<Unit> units =
        om.readValue(
            new File("src/main/resources/units_small.json"), new TypeReference<List<Unit>>() {});

    System.out.println("Number of units:" + units.size());

    UnitManager um = new UnitManager();
    units.forEach(um::addUnit);

    System.out.println("Number of units:" + um.getNumberOfUnits());

    List<Unit> result = um.nearestSimilarUnits(units.get(0), 10);
    System.out.println("Result:" + result.size() + "\n" + result);
  }
}
