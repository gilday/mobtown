package com.johnathangilday;

/**
 * 2-tuple
 */
public class Pair<L,R> {

    public final L left;
    public final R right;

    public static <L,R> Pair<L,R> of(final L left, final R right) {
        return new Pair<>(left, right);
    }

    private Pair(final L left, final R right) {
        this.left = left;
        this.right = right;
    }
}
