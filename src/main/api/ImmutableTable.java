package main.api;

/**
 * Interface ImmutableTable
 *
 * This Interface describes a read-only Table-Instance.
 * Implementing classes of this Interface shall only have
 * methods for reading the content of this table.
 *
 * @see TableBuilder
 * @since 19.12.2017
 */
public interface ImmutableTable {

    /**
     * Returns the content of the header row
     *
     * @return content of header row
     */
    String[] getHeader();

    /**
     * Returns the content of the requested row
     *
     * @param index the index of the row in this table
     * @return the row content
     * @throws IndexOutOfBoundsException if there is no row with the supplied index
     */
    String[] getRow(int index);

    /**
     * Returns the content of the requested column.
     *
     * @param index The index of the column in this table
     * @return the column content
     * @throws IndexOutOfBoundsException if there is no column with the supplied index
     */
    String[] getColumn(int index);

    /**
     * Returns the content of the specified cell.
     *
     * @param row the row index
     * @param column the column index
     * @return the content of the cell
     * @throws IndexOutOfBoundsException if at least one of the indices is out of bounds
     */
    String getCell(int row, int column);

    /**
     * Writes this table to a file. If the file does not exist
     * it will be created, if it exists, its content will be overwritten.
     *
     * @param path The path and name of the file
     */
    void writeToFile(String path);

    String toString();

}
