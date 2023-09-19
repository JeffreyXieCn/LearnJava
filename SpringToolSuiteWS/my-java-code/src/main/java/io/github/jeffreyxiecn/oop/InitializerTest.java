package io.github.jeffreyxiecn.oop;

public class InitializerTest {
    static int i = 3;

    static {
        System.out.println("Static Initialization block is called");
        i = 10;
    }

    InitializerTest() {
        System.out.println("Static Test Constructor is called");
    }
}
