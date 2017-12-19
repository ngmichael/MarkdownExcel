package main.api;

/**
 * Describes a cell and its basic functionality from a MarkdownTable.
 *
 * @author Noah George Michael <noah.michael@mni.thm.de>
 * @since 19.12.2017
 */
public interface Cell {

    String getValue();
    void setValue(String value);

}
