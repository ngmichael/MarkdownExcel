package main.api;

import java.util.Comparator;
import java.util.Iterator;
import java.util.OptionalDouble;

/**
 * This interface defines Vector operations. A Vector in a "table perspective"
 * is either a row or a column. The Vector itself doesn't need to know which
 * type it is however, because the performable operations and the internal
 * representation and handling stay the same for rows and columns. The only
 * time where this kind of differentiation is made is when the user selects a
 * vector ("a subset of cells") and has to decide if he wants a vertical or
 * horizontal subset.
 *
 *  @see CellOperation
 *  @see Cell
 *  @see OptionalDouble
 *  @author Noah George Michael noah.michael@mni.thm.de
 *  @since 19.12.2017
 *  @version 2
 */
@SuppressWarnings({"unused", "UnusedReturnValue"})
public interface Vector {

    /**
     * Inserts the supplied values into this vector.
     * Already present values will be overwritten if
     * enough values are supplied. If more values are supplied than
     * can be handled by this vector, the remaining values will be discarded.
     *
     * @param values The values to be inserted into this vector.
     * @return the updated vector
     * @see Vector
     * @since Version 1
     */
    Vector setValues(Cell... values);

    /**
     * Inserts the supplied values into this vector.
     * Already present values will be overwritten if
     * enough values are supplied. If more values are supplied than
     * can be handled by this vector, the remaining values will be discarded.
     *
     * @param values The values to be inserted into this vector.
     * @return the updated vector
     * @see Vector
     * @since Version 3
     */
    Vector setValues(String... values);

    /**
     * Returns an array with sufficient length to contain
     * this vectors values.
     *
     * @return a Cell-Array containing this vectors values
     * @since Version 1
     */
    Cell[] getValues();

    /**
     * Executes the supplied CellOperation on every
     * cell in this vector.
     *
     * @param op the cell operation
     * @see CellOperation
     * @since Version 1
     */
    void forEachCell(CellOperation op);

    /**
     * Executes the supplied CellOperation for only
     * one cell
     *
     * @param index the cell in question
     * @param op the operation for that cell
     * @see CellOperation
     * @since Version 1
     */
    void forSingleCell(int index, CellOperation op);

    /**
     * Sorts the values of thi vector with a default comparator.
     * The default comparator compares lexicographical order of the elements.
     *
     * @return A new Vector-Instance with the result of the sorting operation
     */
    Vector sort();

    /**
     * Sorts the values of this vector using the given comparator.
     *
     * @param comparator the comparator for determining the order of elements
     * @return A new Vector-Instance with the result of the sorting operation
     */
    Vector sort(Comparator<Cell> comparator);

    /**
     * Attempts to calculate the mean of all cells for this vector
     * and either returns a double or an empty optional, if the values
     * somehow do not allow arithmetic operations.
     *
     * @return an Optional over a double
     * @see OptionalDouble
     * @since Version 1
     */
    OptionalDouble mean();

    /**
     * Attempts to calculate the median of all cells for this vector
     * and either returns a double or an empty optional, if the values
     * somehow do not allow arithmetic operations.
     *
     * @return an Optional over a double
     * @see OptionalDouble
     * @since Version 1
     */
    OptionalDouble median();

    /**
     * Attempts to calculate the sum of all cells for this vector
     * and either returns a double or an empty optional, if the values
     * somehow do not allow arithmetic operations.
     *
     * @return an Optional over a double
     * @see OptionalDouble
     * @since Version 1
     */
    OptionalDouble sum();

    /**
     * Returns the amount of cells in this vector
     *
     * @return the amount of cells
     * @since Version 1
     */
    int length();

    /**
     * Finds the smallest numerical value in this vector and returns it.
     * NOTE: If the Vector contains one or more non-numerical values, an
     * empty optional is returned.
     *
     * @return an Optional containing the smallest value
     * @since Version 3
     */
    OptionalDouble min();

    /**
     * Finds the biggest numerical value in this vector and returns it.
     * NOTE: If the Vector contains one or more non-numerical values, an
     * empty optional is returned
     *
     * @return an Optional containing the biggest value
     * @since Version 3
     */
    OptionalDouble max();

    /**
     * Matches every value against the supplied RegularExpression and
     * returns a new Vector containing all positive matches.
     * @param regEx the Regular Expression for matching
     * @return a new Vector with positive matches
     * @since Version 3
     */
    Vector match(String regEx);

    /**
     * Returns an Iterator over the Elements of this Vector.
     * @return An Iterator-Instance
     * @see Iterator
     * @see Cell
     * @since Version 2
     */
    Iterator<Cell> iterator();

    Vector clone();
}
