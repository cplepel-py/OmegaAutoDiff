package edu.hmc.math19;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/** An element of a real normed vector space */
public abstract class Vector<T extends Vector<T>> {
    /**
     * Dictionary of gradients for each child.
     * 
     * @key The Child Vector
     * @Value The assosiated gradient
     */
    protected Map<Vector<?>, Supplier<Vector<?>>> parentEdges = new HashMap<>();
    /**
     * Adds this vector and another vector.
     * 
     * <p>The addition operation should be commutative.
     * 
     * @param other  The vector to add to this one.
     * @return  The sum of this vector and the other vector.
     */
    public abstract T add(T other);
    /**
     * Scales this vector.
     * 
     * @param scalar  The amount by which to scale this vector.
     * @return  This vector scaled by the scalar.
     */
    public abstract T scale(Scalar scalar);

    /**
     * Negates this vector.
     * 
     * @return  The addative inverse of this vector.
     */
    public T negate() {
        return scale(new Scalar(-1));
    }
    /**
     * Subtracts a vector from this vector.
     * 
     * <p>The subtraction operation should be anticommutative.
     * 
     * @param other  The vector to subtract from this vector.
     * @return  The difference of this vector and the other vector.
     */
    public T subtract(T other) {
        return add(other.negate());
    }
    /**
     * Divides this vector by a scalar divisor.
     * 
     * @param divisor  The amount by which to scale down this vector.
     * @return  This vector scaled down by the specified amount.
     */
    public T divide(Scalar divisor) {
        return scale(divisor.inverse());
    }
}
