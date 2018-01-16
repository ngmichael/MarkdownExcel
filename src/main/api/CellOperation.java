package main.api;

/**
 * Functional Interface CellOperation
 *
 * This Interface describes a single non-default method
 * for manipulating Cells of a Vector.
 *
 * @author Noah George Michael noah.michael@mni.thm.de
 * @since 21.12.2017
 */
@FunctionalInterface
public interface CellOperation {

    /**
     * This method is intended to be used with Lambda-Expressions.
     *
     * It executes the method described ba the Lambda-Expression
     * with the supplied arguments. The aforementioned method can perform
     * any arbitrary manipulation on the supplied cell.
     *
     *
     * @param index the index of this cell in the parent vector
     * @param cell the cell itself
     * @see Cell
     * @see Vector
     */
    void manipulateCell(Integer index, Cell cell);

}
