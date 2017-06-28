package de.beuth.algorithmen.datatypes.bintree;


public class IntKeyable implements KeyAble {

    private int v;

    public IntKeyable(int x) {
        v = x;
    }

    @Override
    public int getKey() {
        return v;
    }

    public int getValue() {
        return v;
    }

    public boolean equals(Object other) {
        if (other == null) return false;
        if (other instanceof IntKeyable) {
            return v == ((IntKeyable) other).v;
        } else return false;
    }

}
