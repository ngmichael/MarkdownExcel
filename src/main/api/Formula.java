package main.api;

/**
 * Functional Interface Formula
 *
 * This functional interface enables the user of this api to
 * insert arbitrary formulas into cells instead of fixed values.
 * These formulas are supposed to mimic the formula capability of
 * Excel and other spreadsheet software.
 */
@FunctionalInterface
public interface Formula {

    /**
     * Acts as a formula for a cell.
     *
     * @param table the current Markdown-Table
     * @param cell the cell on which the formula is applied
     * @param index the index of that cell in its vector
     * @return a string representation of the result
     */
    public String execute(Integer index, Cell cell, TableBuilder table);

}
