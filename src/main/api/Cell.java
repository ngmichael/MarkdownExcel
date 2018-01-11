package main.api;

/**
 * Describes a cell and its basic functionality for a MarkdownTable.
 *
 * @author Noah George Michael <noah.michael@mni.thm.de>
 * @since 19.12.2017
 */
public interface Cell {

    /**
     * Returns the value of this cell as a string
     * @return the value
     * @since Version 1
     */
    String getValue();

    /**
     * Sets the value of this cell
     * @param value the value for this cell
     * @since Version 1
     */
    void setValue(String value);

    /**
     * Replaces this cells value with a formula.
     *
     * @param formula the formula for calculating the value of this cell
     * @since Version 2
     */
    void setFormula(Formula formula);

    /**
     * Returns the formula of this cell
     * @return a Formula-Instance or null if this cell doesn't have a formula
     * @since Version 2
     */
    Formula getFormula();
}
