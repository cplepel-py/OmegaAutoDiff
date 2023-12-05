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
        this.data = new Scalar[data.length];
        for(int i = 0; i < data.length; i++)
            this.data[i] = new Scalar(data[i]);
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

    /**
     * Computes the divergence of this vector.
     * 
     * @param bases  The basis variables of the space.
     * @return  The divergence of this vector with respect to the provided bases.
     */
    public Scalar div(Scalar... bases) {
        if(bases.length != dimension())
            throw new RuntimeException(
                "The divergence of a vector field is defined only over a space of the same dimension.");
        Scalar div = new Scalar(0);
        for(int i = 0; i < dimension(); i++){
            div = div.add(get(i).diff(bases[i]));
        }
        return div;
    }

    /** Computes the 3D curl of this vector. */
    public NVector curl(Scalar x, Scalar y, Scalar z) {
        if(dimension() != 3)
            throw new RuntimeException("3D vector curl is only defined for 3D vectors");
        return new NVector(
            get(2).diff(y).subtract(get(1).diff(z)),
            get(0).diff(z).subtract(get(2).diff(x)),
            get(1).diff(x).subtract(get(0).diff(y))
        );
    }

    /** Computes the 2D curl of this vector. */
    public Scalar curl(Scalar x, Scalar y) {
        if(dimension() != 2)
            throw new RuntimeException("Scalar curl is only defined for 2D vectors");
        return get(1).diff(x).subtract(get(0).diff(y));
    }

    /**
     * Computes the cross of this vector cross other vector.
     * 
     * @param other The NVector of three dimensions you would like to cross this vector with.
     * @return The result of crossing this vector with the other vector
     */
    public NVector cross(NVector other){
        if(dimension() !=3 || other.dimension() !=3)
            throw new RuntimeException("Cross is only defined for two 3D vectors.");
            return new NVector(
                get(1).scale(other.get(2)).add(get(2).scale(other.get(1).negate())),
                get(0).scale(other.get(2)).add(get(2).scale(other.get(0).negate())).negate(),
                get(0).scale(other.get(1)).add(get(1).scale(other.get(0).negate()))
                );
    }
}
