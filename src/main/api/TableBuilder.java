package main.api;

import main.markdownExcel.MarkdownTable;

/**
 * Interface TableBuilder
 *
 * This Interface defines a set of methods for building and editing GitHub-
 * Flavored Markdown tables. For more information on the exact specification
 * of this kind of TableBuilder, consult the GitHub-Flavored Markdown specification.
 *
 * The Methods supplied by this Interface are described in more detail down
 * below. Generally, these methods perform some sort of action on the table
 * and then return the modified TableBuilder-Instance. These manipulations are
 * always performed on the same instance.
 *
 * @author Noah George Michael noah.michael@mni.thm.de
 * @since 20.12.2017
 * @see ImmutableTable
 * @see MarkdownTable
 */
@SuppressWarnings({"unused", "UnusedReturnValue"})
public interface TableBuilder {

    /**
     * Creates a new markdown table by parsing the content of the
     * supplied file.
     * NOTE: Any data stored in the table at method invocation is lost
     * 
     * @param s The path to the Markdown file
     * @return this table instance with the content of the supplied file
     */
    TableBuilder fromFile(String s);

    /**
     * Creates a new empty markdown table with the supplied amount of columns
     * and rows+2 (for the header row and column formatting row).
     *
     * @param rows the amount of non-header rows for this table
     * @param columns the amount of columns for this table
     * @return A new table instance
     */
    TableBuilder fromScratch(int rows, int columns);

    /**
     * Creates a new markdown table by parsing the content of the
     * supplied immutable table.
     *
     * NOTE:  Any data stored in the table at method invocation is lost
     * @param table an ImmutableTable
     * @return this table instance with the content of the supplied ImmutableTable
     */
    TableBuilder fromImmutableTable(ImmutableTable table);

    /**
     * Sets the header row of this table to the values of the
     * supplied vector.
     *
     * @param values the new values of the header row
     * @return The TableBuilder-Instance with the updated header row
     */
    TableBuilder setHeader(Vector values);

    /**
     * Sets the values of the header row to the supplied String[].
     *
     * @param values the new values for the header row
     * @return The TableBuilder-Instance with the updated header
     */
    TableBuilder setHeader(String... values);

    /**
     * Inserts a new empty row at the specified index.
     *
     * @throws IndexOutOfBoundsException if the supplied index is out of bounds
     * @param index the index where the new row is to be inserted
     * @return The TableBuilder-Instance with the extra row
     */
    TableBuilder insertRow(int index) throws IndexOutOfBoundsException;

    /**
     * Inserts a pre-filled row at the specified index.
     *
     * @throws IndexOutOfBoundsException if the supplied index is out of bounds
     * @param index the index where the new row is to be inserted
     * @param vec the values for pre-filling
     * @return The TableBuilder-Instance with the extra row and its supplied values
     */
    TableBuilder insertRow(int index, Vector vec) throws IndexOutOfBoundsException;

    /**
     * Appends an empty row to the bottom of the table.
     *
     * @return The TableBuilder-Instance with an extra appended row
     */
    TableBuilder appendRow();

    /**
     * Appends a row to the bottom of this table and fills it
     * with the values of the supplied Vector.
     *
     * @param vec The values to fill this new row with
     * @return The TableBuilder-Instance with an extra appended row
     */
    TableBuilder appendRow(Vector vec);

    /**
     * Removes the selected row from this table
     *
     * @param index the index of the row that's to be removed
     * @return The TableBuilder-Instance without the selected row
     */
    TableBuilder removeRow(int index);

    /**
     * Inserts a new empty column at the specified index
     *
     * @param index the index where the new row should be inserted
     * @param title the title of the new column
     * @return The TableBuilder-Instance with one additional column
     */
    TableBuilder insertColumn(int index, String title);

    /**
     * Inserts a new column into the table at the specified index and fills
     * it with the values of the specified vector.
     *
     * @param values the values which are to be inserted
     * @param index the index where the new row should be inserted
     * @param title the title of the new column
     * @return The TableBuilder-Instance with one additional pre-filled column
     */
    TableBuilder insertColumn(int index, String title, String... values);

    /**
     * Appends one empty column to the right end of the table
     *
     * @param title the title of the new column
     * @return The TableBuilder-Instance with one additional column at the end
     */
    TableBuilder appendColumn(String title);

    /**
     * Appends a new  column to the right end of the table and fills it
     * with the values of the specified vector.
     *
     * @param title the title of the new column
     * @param values the values which are to be inserted
     * @return The TableBuilder-Instance with one additional column at the end
     */
    TableBuilder appendColumn(String title, String... values);

    /**
     * Removes the column with the specified index from the table
     * @param index the columns index
     * @return The TableBuilder-Instance without the specified column
     */
    TableBuilder removeColumn(int index);

    /**
     * Returns a row.
     *
     * NOTE: This is a terminating operation, meaning that the vector has
     * no information about this table and thus can't reach it.
     * @param index the index of the row
     * @return a Vector-Instance containing the values of the row
     * @see Vector
     */
    Vector getRow(int index);

    /**
     * Returns a column.
     *
     * NOTE: This is a terminating operation, meaning that the vector has
     * no information about this table and thus can't reach it.
     * @param index the index of the column
     * @return a Vector-Instance containing the values of the column
     * @see Vector
     */
    Vector getColumn(int index);

    /**
     * Executes the VectorOperation op for the given row.
     *
     * NOTE: VectorOperation is a functional interface.
     * @param index the row on which the operation is to be executed
     * @param op the operation itself
     * @return The TableBuilder-Instance with one modified row
     * @see VectorOperation
     */
    TableBuilder forSingleRow(int index, VectorOperation op);

    /**
     * Executes the VectorOperation op for the given column.
     *
     * NOTE: VectorOperation is a functional interface.
     * @param index the column on which the operation is to be executed
     * @param op the operation itself
     * @return The TableBuilder-Instance with one modified column
     * @see VectorOperation
     */
    TableBuilder forSingleColumn(int index, VectorOperation op);

    /**
     * Executes the VectorOperation op for all rows.
     *
     * NOTE: VectorOperation is a functional interface.
     * @param op the operation to be performed
     * @return The TableBuilder-Instance with modifications
     * @see VectorOperation
     */
    TableBuilder forEachRow(VectorOperation op);

    /**
     * Executes the VectorOperation op for all columns.
     *
     * NOTE: VectorOperation is a functional interface.
     * @param op the operation to be performed
     * @return The TableBuilder-Instance with modifications
     * @see VectorOperation
     */
    TableBuilder forEachColumn(VectorOperation op);

    /**
     * Sets the formatting for a specific column.
     *
     * @param columnIndex the index of the column to be formatted
     * @param formatting the desired formatting of that column
     * @return The TableBuilder-Instance with modified format settings for the
     * desired column
     * @see ColumnFormatting
     */
    TableBuilder setFormatting(int columnIndex, ColumnFormatting formatting);

    /**
     * Sets the formatting for every column.
     * If more values are supplied than columns exist, the remaining
     * values will be discarded.
     * If less values are supplied, the remaining columns stay unchanged.
     *
     * @param formattings the format values that are to be applied
     * @return The TableBuilder-Instance with updated format values
     */
    TableBuilder setFormattings(ColumnFormatting... formattings);

    /**
     * Creates an ImmutableTable-Instance from this table builder
     * and returns it.
     * NOTE: This is a terminating operation. ImmutableTable has no
     * direct access to this builder and can't modify its own values. A
     * new TableBuilder-Instance is needed if further modification is desired.
     * @return An ImmutableTable.
     */
    ImmutableTable build();
}
