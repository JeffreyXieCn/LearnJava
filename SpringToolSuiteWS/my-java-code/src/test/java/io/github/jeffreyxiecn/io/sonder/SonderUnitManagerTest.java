package io.github.jeffreyxiecn.io.sonder;

// import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
// import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import java.io.File;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

class SonderUnitManagerTest {
  private static List<Unit> units;
  private static UnitManager um;
  private static SonderUnitManager sum;
  public static int LIMIT = 25;
  public static double deltaForDistance = 0.000000000000001;
  private static Random random;

  @BeforeAll
  static void setUpBeforeClass() throws Exception {
    ObjectMapper om = new ObjectMapper();
    units =
        om.readValue(
            new File("src/test/resources/units_small.json"), new TypeReference<List<Unit>>() {});

    System.out.println("Number of units in the JSON file:" + units.size());

    um = new UnitManager();
    units.forEach(um::addUnit);

    sum = new SonderUnitManager();
    units.forEach(sum::addUnit);

    System.out.println("Number of units added to UnitManager:" + um.getNumberOfUnits());
    System.out.println("Number of units added to SonderUnitManager:" + sum.getNumberOfUnits());

    random = new Random();
  }

  @RepeatedTest(100)
  void whenBothMethodsFindTheSameUnits_thenCorrect(RepetitionInfo repInfo) {
    System.out.println(
        String.format(
            "\n\n====Run %d of %d====",
            repInfo.getCurrentRepetition(), repInfo.getTotalRepetitions()));

    int index = random.nextInt(units.size());
    Unit unit = units.get(index);
    System.out.println(
        "Searching for " + LIMIT + " nearest similar " + "units around unit: " + unit.toString());

    List<Unit> resultWithTotalSort = um.nearestSimilarUnits(unit, LIMIT);
    System.out.println(
        "resultWithTotalSort:" + resultWithTotalSort.size() + "\n" + resultWithTotalSort);
    List<Unit> resultWithPrebuiltMap = sum.nearestSimilarUnits(unit, LIMIT);
    System.out.println(
        "resultWithPrebuiltMap:" + resultWithPrebuiltMap.size() + "\n" + resultWithPrebuiltMap);

    verifySimilarResults(resultWithTotalSort, resultWithPrebuiltMap, unit);

    // Can't make below assertions, see the explanation in UnitManagerTest.java
    // assertEquals(resultWithTotalSort, resultWithPrebuiltMap);
    // assertThat(resultWithTotalSort, containsInAnyOrder(resultWithPrebuiltMap.toArray()));
  }

  private void verifySimilarResults(List<Unit> first, List<Unit> second, Unit unit) {
    assertEquals(first.size(), second.size());
    for (int i = 0; i < first.size(); i++) {
      System.out.println("Comparing " + i);
      System.out.println("first :" + first.get(i));
      System.out.println("second:" + second.get(i));
      assertTrue(UnitManager.isSimilar(first.get(i), unit));
      assertTrue(UnitManager.isSimilar(second.get(i), unit));
      assertNotEquals(first.get(i).getId(), unit.getId());
      assertNotEquals(second.get(i).getId(), unit.getId());
      assertEquals(
          UnitManager.calcDistance(first.get(i), unit),
          UnitManager.calcDistance(second.get(i), unit),
          deltaForDistance);
    }
  }
}
