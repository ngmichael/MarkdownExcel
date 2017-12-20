package main.api;

/**
 * Interface Table
 *
 * This Interface defines a set of methods for building and editing GitHub-
 * Flavored Markdown tables. For more information on the exact specification
 * of this kind of Table, consult the GitHub-Flavored Markdown specification.
 *
 * The Methods supplied by this Interface are described in more detail down
 * below. Generally, these methods perform some sort of action on the table
 * and then return the modified Table-Instance. These manipulations are
 * always performed on the same instance which means that the state of a
 * table can not be saved by simply coping the instances reference. A deep
 * copy needs to be made for that.
 *
 * @author Noah George Michael <noah.michael@mni.thm.de>
 * @since 20.12.2017
 * @see ImmutableTable
 * @see main.MarkdownTable
 */
public interface Table {

    /**
     * Creates a new markdown table by parsing the content of the
     * supplied file.
     *
     * @implNote Any data stored in the table at method invocation is lost
     * @param s The path to the Markdown file
     * @return this table instance with the content of the supplied file
     */
    Table fromFile(String s);

    /**
     * Creates a new empty markdown table with the supplied amount of columns
     * and rows+2 (for the header row and column formatting row).
     *
     * @param rows the amount of non-header rows for this table
     * @param columns the amount of columns for this table
     * @return A new table instance
     */
    Table fromScratch(int rows, int columns);

    /**
     * Creates a new markdown table by parsing the content of the
     * supplied immutable table.
     *
     * @implNote Any data stored in the table at method invocation is lost
     * @param table an ImmutableTable
     * @return this table instance with the content of the supplied ImmutableTable
     */
    Table fromImmutableTable(ImmutableTable table);

    /**
     * Sets the header row of this table to the values of the
     * supplied vector.
     *
     * @param values the new values of the header row
     * @return The Table-Instance with the updated header row
     */
    Table setHeaderRow(Vector values);

    /**
     * Sets the number of rows that this table has.
     *
     * @param rows the new number of rows for the table
     * @return The Table-Instance with the specified amount of rows
     */
    Table setRows(int rows);

    /**
     * Sets the number of columns in this table
     *
     * @param columns the new number of columns for this table
     * @return The Table-Instance with the specified amount if columns
     */
    Table setColumns(int columns);

    int getRowCount();
    int getColumnCount();

    /**
     * Inserts a new empty row at the specified index.
     *
     * @throws IndexOutOfBoundsException if the supplied index is out of bounds
     * @param index the index where the new row is to be inserted
     * @return The Table-Instance with the extra row
     */
    Table insertRow(int index) throws IndexOutOfBoundsException;

    /**
     * Inserts a pre-filled row at the specified index.
     *
     * @throws IndexOutOfBoundsException if the supplied index is out of bounds
     * @param index the index where the new row is to be inserted
     * @return The Table-Instance with the extra row and its supplied values
     */
    Table insertRow(int index, Vector vec) throws IndexOutOfBoundsException;

    /**
     * Appends an empty row to the bottom of the table.
     *
     * @return The Table-Instance with an extra appended row
     */
    Table appendRow();

    /**
     * Appends a row to the bottom of this table and fills it
     * with the values of the supplied Vector.
     *
     * @param vec The values to fill this new row with
     * @return The Table-Instance with an extra appended row
     */
    Table appendRow(Vector vec);

    /**
     * Removes the selected row from this table
     *
     * @param index the index of the row that's to be removed
     * @return The Table-Instance without the selected row
     */
    Table removeRow(int index);

    /**
     *
     * @param index
     * @return
     */
    Table insertColumn(int index);
    Table insertColumn(int index, Vector vec);
    Table appendColumn();
    Table appendColumn(Vector vec);
    Table deleteColumn(int index);

    Vector getRow(int index);
    Vector getColumn(int index);

    Table forSingleRow(int index, VectorOperation op);
    Table forSingleColumn(int index, VectorOperation op);

    Table forEachRow(VectorOperation op);
    Table forEachColumn(VectorOperation op);

    Table setFormatting(int columnIndex, ColumnFormatting formatting);

    ImmutableTable build();
}
