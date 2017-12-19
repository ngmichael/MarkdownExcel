package main.api;

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
 * The following operations are available:
 *
 *  Vector setValues(String... values);
 *  String[] getValues();
 *
 *  forSingleCell(CellOperation op);
 *  void forEachCell(CellOperation op);
 *
 *  OptionalDouble mean();
 *  OptionalDouble median();
 *  OptionalDouble sum();
 *
 *  int length();
 *
 *  @see CellOperation
 *  @see Cell
 *  @see OptionalDouble
 *  @author Noah George Michael <noah.michael@mni.thm.de>
 *  @since 19.11.2017
 */
public interface Vector {

    /**
     * Inserts the supplied values into this vector.
     * Already present values will be overwritten if
     * enough values are supplied. If more values are supplied than
     * can be handled by this vector, the remaining values will be discarded.
     *
     * @param values The values to be inserted into this vector.
     * @return the updated vector
     */
    Vector setValues(String... values);

    /**
     * Returns an array with sufficient length to contain
     * this vectors values.
     *
     * @return a String-Array containing this vectors values
     */
    String[] getValues();

    /**
     * Executes the supplied CellOperation on every
     * cell in this vector.
     *
     * @param op the cell operation
     */
    void forEachCell(CellOperation op);

    /**
     * Executes the supplied CellOperation for only
     * one cell
     *
     * @param index the cell in question
     * @param op the operation for that cell
     */
    void forSingleCell(int index, CellOperation op);

    /**
     * Attempts to calculate the mean of all cells for this vector
     * and either returns a double or an empty optional, if the values
     * somehow do not allow arithmetic operations.
     *
     * @return an Optional over a double
     */
    OptionalDouble mean();

    /**
     * Attempts to calculate the median of all cells for this vector
     * and either returns a double or an empty optional, if the values
     * somehow do not allow arithmetic operations.
     *
     * @return an Optional over a double
     */
    OptionalDouble median();

    /**
     * Attempts to calculate the sum of all cells for this vector
     * and either returns a double or an empty optional, if the values
     * somehow do not allow arithmetic operations.
     *
     * @return an Optional over a double
     */
    OptionalDouble sum();

    /**
     * Returns the amount of cells in this vector
     *
     * @return the amount of cells
     */
    int length();
}
