package edu.hmc.math19;

import java.util.HashMap;
import java.util.Map;

/** A (real) scalar value. */
public class Scalar extends Vector<Scalar> {
    private double value;

    public Scalar(double value, Map<Vector<?>,Vector<?>> parentEdges) {
        this.parentEdges = parentEdges;
        this.value = value;
    }
    public Scalar(double value) {
        this(value, new HashMap<>());
    }


    @Override
    public Scalar add(Scalar other) {
        Map<Vector<?>, Vector<?>> edges = new HashMap<>();
        edges.put(other, new Scalar(1));
        edges.put(this, new Scalar(1));

        return new Scalar(this.value + other.value, edges);
    }
    @Override
    public Scalar scale(Scalar scalar) {
        Map<Vector<?>, Vector<?>> edges = new HashMap<>();
        edges.put(scalar, this);
        edges.put(this, scalar);
        return new Scalar(value * scalar.value, edges);
    }

    /**
     * Inverts this scalar.
     * 
     * @return The multiplicative inverse of this scalar.
     */
    public Scalar inverse() {
        return new Scalar(1 / value);
    }

    /** Gets the value of this scalar as a double. */
    public double getValue() {
        return value;
    }
}
