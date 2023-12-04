package edu.hmc.math19;

import java.util.Arrays;

/** A vector in R^n. */
public class NVector extends Vector<NVector> {
    /** The components of this vector. */
    private Scalar[] data;

    /** Consctructs a vector from its scalar components. */
    public NVector(Scalar... data) {
        this.data = data;
    }
    /** Constructs a constant vector from its components. */
    public NVector(double... data) {
        this.data = (Scalar[])Arrays.stream(data).mapToObj(i -> new Scalar(i)).toArray();
    }

    /** Returns the dimensionality of this vector. */
    public int dimension() {
        return data.length;
    }
    /** Gets a particular component of this vector. */
    public Scalar get(int i) {
        return data[i];
    }

    /** Checks a vector's dimension for compatibility with that of this vector. */
    private void checkDimensions(NVector other) {
        if(dimension() != other.dimension())
            throw new RuntimeException(String.format(
                "Cannot add vectors of dimension %s and %s",
                dimension(), other.dimension()));
    }

    @Override
    public NVector add(NVector other) {
        checkDimensions(other);
        Scalar[] newData = new Scalar[dimension()];
        for(int i = 0; i < dimension(); i++)
            newData[i] = get(i).add(other.get(i));
        return new NVector(newData);
    }
    @Override
    public NVector scale(Scalar scalar) {
        return new NVector((Scalar[])Arrays.stream(data).map(val -> val.scale(scalar)).toArray());
    }

    /**
     * Returns the dot product (L2 / Euclidean inner product) of this vector and another.
     * 
     * @param other  The other vector with which to compute the dot product.
     * @return  The inner product of this vector with the other specified vector.
     */
    public Scalar dot(NVector other) {
        checkDimensions(other);
        Scalar product = new Scalar(0);
        for(int i = 0; i < dimension(); i++)
            product = product.add(get(i).scale(other.get(i)));
        return product;
    }
}
