package com.johnathangilday;

/**
 * Static utilities for dealing with {@link String}.
 * Class is named "Strings" in the style of Guava
 */
public class Strings {

    public static boolean isNotEmpty(final String s) {
        return s != null && !s.trim().isEmpty();
    }

    /**
     * Strictly static utilities
     */
    private Strings() {

    }
}
