package edu.hmc.math19;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/** A (real) scalar value. */
public class Scalar extends Vector<Scalar> {
    private double value;

    public Scalar(double value, Map<Vector<?>,Supplier<Vector<?>>> parentEdges) {
        this.parentEdges = parentEdges;
        this.value = value;
    }
    public Scalar(double value) {
        this(value, new HashMap<>());
    }


    @Override
    public Scalar add(Scalar other) {
        Map<Vector<?>, Supplier<Vector<?>>> edges = new HashMap<>();
        edges.put(other, () -> new Scalar(1));
        edges.put(this, () -> new Scalar(1));

        return new Scalar(this.value + other.value, edges);
    }
    @Override
    public Scalar scale(Scalar scalar) {
        Map<Vector<?>, Supplier<Vector<?>>> edges = new HashMap<>();
        edges.put(scalar, () -> this);
        edges.put(this, () -> scalar);
        return new Scalar(value * scalar.value, edges);
    }

    /**
     * Inverts this scalar.
     * 
     * @return The multiplicative inverse of this scalar.
     */
    public Scalar inverse() {
        return power(new Scalar(-1));
    }

    /**
     * Raises this scalar to a scalar power.
     * 
     * @param exponent  The exponent to which to raise this scalar value.
     * @return This power raised to the specified power.
     */
    public Scalar power(Scalar exponent) {
        Map<Vector<?>, Supplier<Vector<?>>> edges = new HashMap<>();
        edges.put(this, () -> exponent.scale(this.power(exponent.add(new Scalar(-1)))));
        edges.put(exponent, () -> this.ln().scale(this.power(exponent)));
        return new Scalar(Math.pow(this.value, exponent.value), edges);
    }

    /**
     * Calculates the natural log of this scalar.
     * 
     * @return The natural log of this scalar.
     */
    public Scalar ln() {
        Map<Vector<?>, Supplier<Vector<?>>> edges = new HashMap<>();
        edges.put(this, () -> inverse());
        return new Scalar(Math.log(value),  edges);
    }

    /** Gets the value of this scalar as a double. */
    public double getValue() {
        return value;
    }
}
