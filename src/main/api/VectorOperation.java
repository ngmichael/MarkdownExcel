package main.api;

/**
 *
 */
@FunctionalInterface
public interface VectorOperation {
    /**
     * Intended to be used with Lambda-Expressions.
     *
     * This function takes three arguments.
     * The first one is an integer value which represents the row or
     * column index of this vector.
     * The second argument is the tableBuilder of which the vector is a part of.
     * The third argument is the vector itself.
     *
     * Implementations of this methods allow
     * arbitrary manipulation of the supplied Vector.
     *
     * @param index the row or column index of the supplied vector
     * @param tableBuilder the current tableBuilder builder
     * @param vector the supplied vector itself
     * @see TableBuilder
     * @see Vector
     */
    void manipulateVector(Integer index, TableBuilder tableBuilder, Vector vector);
}
