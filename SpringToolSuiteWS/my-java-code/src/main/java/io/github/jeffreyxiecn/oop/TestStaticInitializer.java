package io.github.jeffreyxiecn.oop;

public class TestStaticInitializer {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        // System.out.println(InitializerTest.i);
        System.out.println(InitializerTest.class.getCanonicalName()); // static initialization block won't be called
    }
}
