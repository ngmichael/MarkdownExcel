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
     */
    String getValue();

    /**
     * Sets the value of this cell
     * @param value the value for this cell
     */
    void setValue(String value);

}
