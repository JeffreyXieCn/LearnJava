package io.github.jeffreyxiecn.collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TreeSetTest {

    @BeforeAll
    static void setUpBeforeClass() throws Exception {}

    @Test
    void testTreeSetWithCustomComparator() {
        TreeSet<String> ts = new TreeSet<>(Comparator.comparing(String::length));
        ts.add("one");
        ts.add("three");
        assertEquals(2, ts.size());
        assertTrue(ts.contains("two")); // returns true; because an entry with same length ("one") already exists

        boolean result = ts.add("two"); // the key is considered exist due to the comparator String::length
        assertFalse(result); // false
        assertEquals(2, ts.size());
    }

    @Test
    void givenTreeSet_whenIterateInDescendingOrder_thenGetExpectedResult() {
        TreeSet<String> strTreeSet = new TreeSet<>();
        strTreeSet.add("First");
        strTreeSet.add("Second");
        strTreeSet.add("Third");
        Iterator<String> itr = strTreeSet.descendingIterator();
        List<String> expected = List.of("Third", "Second", "First");
        List<String> actual = new ArrayList<>();
        while (itr.hasNext()) {
            actual.add(itr.next());
        }

        assertEquals(expected, actual);
    }

    @Test
    void givenTreeSet_whenRetrieveSubset_thenGetExpectedResult() {
        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(4);
        treeSet.add(5);
        treeSet.add(1);
        treeSet.add(3);
        treeSet.add(6);
        treeSet.add(9);

        SortedSet<Integer> subSet = treeSet.subSet(2, 9);
        Set<Integer> expected = Set.of(3, 4, 5, 6);
        assertEquals(expected, subSet);

        subSet = treeSet.subSet(2, 6);
        expected = Set.of(3, 4, 5);
        assertEquals(expected, subSet);

        subSet = treeSet.subSet(3, false, 6, true);
        expected = Set.of(4, 5, 6);
        assertEquals(expected, subSet);

        subSet = treeSet.headSet(4);
        expected = Set.of(1, 3);
        assertEquals(expected, subSet);

        subSet = treeSet.tailSet(4);
        expected = Set.of(4, 5, 6, 9);
        assertEquals(expected, subSet);
    }
}
