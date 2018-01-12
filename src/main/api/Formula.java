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
     * @return a string representation of the result
     */
    String execute(TableBuilder table);

}
