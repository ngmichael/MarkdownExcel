package main.api;

/**
 *
 */
@FunctionalInterface
public interface VectorOperation {
    /**
     * Intended to be used with Lambda-Expressions.
     * This function takes the two arguments v and index,
     * a Vector representing a row or column from a table
     * and an Integer representing the row or column number
     * respectively. Implementations of this methos allow
     * arbitrary manipulation of the supplied Vector.
     *
     * @param index the row or column index of the suppied vector
     * @param v the supplied vector itself
     */
    void manipulateVector(Integer index, Vector v);
}
