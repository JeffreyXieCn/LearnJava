package io.github.jeffreyxiecn.collection;

import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

public class SetInJava {

    public static void main(String[] args) {
        TreeSet<String> ts = new TreeSet<>(Comparator.comparing(String::length));
        ts.add("one");
        ts.add("three");
        System.out.println("TreeSet is: " + ts); // [one, three]
        System.out.println("ts.contains(\"two\") = " + ts.contains("two")); // returns true; because an entry with same
                                                                            // length ("one") already exists

        boolean result = ts.add("two"); // the key is considered exist due to the comparator String::length
        System.out.println("Adding two returns: " + result); // false
        System.out.println("TreeSet is: " + ts); // [one, three]

        TreeSet<String> strTreeSet = new TreeSet<>();
        strTreeSet.add("First");
        strTreeSet.add("Second");
        strTreeSet.add("Third");
        Iterator<String> itr = strTreeSet.descendingIterator();
        while (itr.hasNext()) {
            System.out.println(itr.next()); // Third, Second, First
        }


        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(4);
        treeSet.add(5);
        treeSet.add(1);
        treeSet.add(3);
        treeSet.add(6);
        treeSet.add(9);

        SortedSet<Integer> subSet = treeSet.subSet(2, 9);
        System.out.println("subSet is: " + subSet); // subSet is: [3, 4, 5, 6]
        subSet = treeSet.subSet(2, 6);
        System.out.println("subSet is: " + subSet); // subSet is: [3, 4, 5]
        subSet = treeSet.subSet(3, false, 6, true);
        System.out.println("subSet is: " + subSet); // subSet is: [4, 5, 6]

        System.out.println(treeSet.headSet(4)); // [1, 3]
        System.out.println(treeSet.tailSet(4)); // [4, 5, 6, 9]

    }

}
