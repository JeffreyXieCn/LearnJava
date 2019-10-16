package io.github.jeffreyxiecn.io.sonder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.io.File;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

class UnitManagerTest {
  private static UnitManager um;
  public static int LIMIT = 25;
  public static double deltaForDistance = 0.000000000000001;
  private static Random random;

  @BeforeAll
  static void setUpBeforeClass() throws Exception {
    ObjectMapper om = new ObjectMapper();
    List<Unit> units =
        om.readValue(
            new File("src/test/resources/units_small.json"), new TypeReference<List<Unit>>() {});

    System.out.println("Number of units in the JSON file:" + units.size());

    um = new UnitManager();
    units.forEach(um::addUnit);

    System.out.println("Number of units added to UnitManager:" + um.getNumberOfUnits());

    random = new Random();
  }

  @RepeatedTest(100)
  void whenBothMethodsFindSimilarUnits_thenCorrect(RepetitionInfo repInfo) {
    System.out.println(
        String.format(
            "\n\n====Run %d of %d====",
            repInfo.getCurrentRepetition(), repInfo.getTotalRepetitions()));

    int index = random.nextInt(um.getNumberOfUnits());
    Unit unit = um.getUnit(index);
    System.out.println(
        "Searching for " + LIMIT + " nearest similar " + "units around unit: " + unit.toString());

    List<Unit> resultWithTotalSort = um.nearestSimilarUnits(unit, LIMIT);
    System.out.println(
        "resultWithTotalSort:" + resultWithTotalSort.size() + "\n" + resultWithTotalSort);
    List<Unit> resultWithMaxHeap = um.nearestSimilarUnitsWithMaxHeap(unit, LIMIT);
    System.out.println("resultWithMaxHeap:" + resultWithMaxHeap.size() + "\n" + resultWithMaxHeap);

    verifySimilarResults(resultWithTotalSort, resultWithMaxHeap, unit);

    // Can't make below assertion, although the sort algorithms used in UnitManager are stable,
    // MaxHeap is used to produce resultWithMaxHeap, and MaxHeap, by definition, maintains partial
    // ordering. Also the MaxHeap is constantly modified as closer unit is discovered
    // assertEquals(resultWithTotalSort, resultWithMaxHeap);

    // Can't make below assertion, since many similar units will have the same distances, let's call
    // them set S, different algorithms may result in different subsets of S being included in the
    // result
    //    assertThat(resultWithTotalSort, containsInAnyOrder(resultWithMaxHeap.toArray()));

    // Can't make below assertion, same reason as above
    //    List<Integer> unitIdWithTotalSort =
    //        resultWithTotalSort.stream().map(Unit::getId).sorted().collect(Collectors.toList());
    //
    //    List<Integer> unitIdWithMaxHeap =
    //        resultWithMaxHeap.stream().map(Unit::getId).sorted().collect(Collectors.toList());
    //
    //    assertEquals(unitIdWithTotalSort, unitIdWithMaxHeap);
  }

  private void verifySimilarResults(List<Unit> first, List<Unit> second, Unit unit) {
    assertEquals(first.size(), second.size());
    for (int i = 0; i < first.size(); i++) {
      assertTrue(UnitManager.isSimilar(first.get(i), unit));
      assertTrue(UnitManager.isSimilar(second.get(i), unit));
      assertEquals(
          UnitManager.calcDistance(first.get(i), unit),
          UnitManager.calcDistance(second.get(i), unit),
          deltaForDistance);
    }
  }
}
