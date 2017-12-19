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
     * The second argument is the table of which the vector is a part of.
     * The third argument is the vector itself.
     *
     * Implementations of this methods allow
     * arbitrary manipulation of the supplied Vector.
     *
     * @param index the row or column index of the supplied vector
     * @param table the current table builder
     * @param vector the supplied vector itself
     * @see Table
     * @see Vector
     */
    void manipulateVector(Integer index, Table table, Vector vector);
}
