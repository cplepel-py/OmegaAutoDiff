package edu.hmc.math19;

/** A (real) scalar value. */
public class Scalar implements Vector<Scalar> {
    private double value;

    public Scalar(double value) {
        this.value = value;
    }

    @Override
    public Scalar add(Scalar other) {
        return new Scalar(this.value + other.value);
    }
    @Override
    public Scalar scale(Scalar scalar) {
        return new Scalar(value * scalar.value);
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
