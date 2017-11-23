package main.markdown;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class Table
 *
 * This code is part of the Markdown-Excel project.
 * This class represents a MarkdownTable as defined
 * in the GitHub-Flavored Markdown specification.
 *
 * THIS CODE IS INTENTIONALLY NOT USING EXCEPTION HANDLING IN MOST CASES CURRENTLY!
 *
 * Instances of this class provide the following methods:
 *
 * String get(int row, int column) ->               returns the value of a cell
 *   void set(int row, int column, String value) -> sets the value of a cell
 *   void setColumnType(ColumnType type) ->         set formatting type of the column
 *   void setHeaderRow(String... values) ->         set the values of the header row
 *   void appendRow() ->                            appends one row to the bottom of the table
 *   void appendColumn() ->                         appends one column to the right side of the table
 *   void insertRow(int index) ->                   inserts one row into the table at the specified index
 *   void insertColumn(int index) ->                inserts one column into the table at the specified index
 * String toString() ->                             returns a Markdown-Compliant string representing the table
 *
 * Additionally, this class provides the following static methods:
 *
 * static Optional<Table> fromLineStream(Stream<String> lines)
 *      -> attempts to parse the supplied Stream of Strings into a Table instance
 *         and returns it on success. Otherwise an empty Optional will be returned.
 *
 *  @author Noah George Michael <noah.michael@mni.thm.de>
 *  @version 0.0.1
 */
public class Table {

    private String[][] elements;
    private ColumnType[] columnTypes;
    private int columnWidth;
    private int rows, columns;

    /**
     * Creates an empty table instance.
     *
     * @param numberColumns - The number of columns of this table
     * @param numberRows - The number of rows in this column
     */
    public Table(int numberRows, int numberColumns) {
        this.columns = numberColumns;
        this.rows = numberRows + 2;
        elements = new String[rows][columns];
        columnTypes = new ColumnType[columns];
        // Prefill table cells
        for (int i = 0; i < rows; i++) {
            elements[i] = new String[columns];
            Arrays.fill(elements[i], " ");
        }
        // Prefill formating array
        Arrays.fill(columnTypes, ColumnType.NO_BOUND);
        columnWidth = 3;
    }

    /**
     * Returns the value of a cell from this table
     * @param row the row of the cell
     * @param column the column of the cell
     * @return a string representing the cells value
     * @throws IllegalArgumentException if row or column index is out of bounds
     */
    public String get(int row, int column) throws IllegalArgumentException {
        if (row < 0 || row >= elements.length-2)
            throw new IllegalArgumentException("Row " + row + " is out of bounds!");
        if (column < 0 || column >= elements[row].length-2)
            throw new IllegalArgumentException("Column " + column + " is out of bounds!");
        return elements[row+2][column];
    }

    /**
     * Sets the value of a cell
     * @param row the row of the cell
     * @param column the column of the cell
     * @param value the value to be set
     * @throws IllegalArgumentException if row or column index is out of bounds
     */
    public void set(int row, int column, String value) throws IllegalArgumentException {
        if (row < 0 || row >= elements.length-2)
            throw new IllegalArgumentException("Row " + row + " is out of bounds!");
        if (column < 0 || column >= elements[row].length-2)
            throw new IllegalArgumentException("Column " + column + " is out of bounds!");
        columnWidth = 2 + value.length();
        elements[row+2][column] = value;
    }

    /**
     * Sets the type of a specified column.
     *
     *
     * @param columnIndex the index of the column
     * @param type
     */
    public void setColumnType(int columnIndex, ColumnType type) {
        if (columnIndex < 0 || columnIndex >= columnTypes.length )
            throw new IllegalArgumentException("Column index out of bounds!");
        if (type == null) {
            throw new IllegalArgumentException("ColumnType can't be null!");
        }

        columnTypes[columnIndex] = type;
    }

    /**
     * Updates the header row.
     *
     * @param values a variable amount of strings representing the new header values
     */
    public void setHeaderRow(String... values) {
        System.arraycopy(values, 0, elements[0], 0, Math.min(values.length, columns));
    }

    /**
     * Appends a row to the bottom of the table
     */
    public void appendRow(){
        rows++;
        elements = Arrays.copyOf(elements, rows);
        String[] values = new String[columns];
        Arrays.fill(values, " ");
        elements[rows-1] = values;
    }

    /**
     * Appends a column to the right side of the table
     */
    public void appendColumn() {
        columns++;
        for(int i = 0; i < rows; i++) {
            String[] column = elements[i];
            elements[i] = Arrays.copyOf(column, columns);
            elements[i][columns-1] = " ";
        }
        columnTypes = Arrays.copyOf(columnTypes, columns);
        columnTypes[columns-1] = ColumnType.NO_BOUND;
    }

    /**
     * Inserts a new row into the table
     * @param index the index where the row is to be inserted
     */
    public void insertRow(int index) {
        rows++;
        String[][] newElements = new String[rows][columns];
        Iterator<String[]> oldElements = Arrays.asList(elements).iterator();
        for (int i = 0; i < rows; i++) {
            if (i == index+2) {
                newElements[i] = new String[columns];
                Arrays.fill(newElements[i], " ");
            }
            else {
                newElements[i] = oldElements.next();
            }
        }
        elements = newElements;
    }

    public void insertColumn(int index, ColumnType type) {
        columns++;
        String[][] newElements = new String[rows][columns];
        for(int row = 0; row < rows; row++) {
            Iterator<String> rowIterator = Arrays.asList(elements[row]).iterator();
            for (int column = 0; column < columns; column++) {
                newElements[row][column] = column == index ? " " : rowIterator.next();
            }
        }
        elements = newElements;
        ColumnType[] types = new ColumnType[columns];
        Iterator<ColumnType> typeIter = Arrays.asList(columnTypes).iterator();
        for (int i = 0; i < types.length; i++) {
            types[i] = i == index ? type : typeIter.next();
        }
        columnTypes = types;
    }

    /**
     * Removes a row from the table
     * @param index the index of the row to be removed
     */
    public void removeRow(int index) {
        rows--;
        String[][] newElements = new String[rows][columns];
        for (int i = 0; i < elements.length; i++) {
            if (i != index + 2) newElements[i] = elements[i];
        }
        elements = newElements;
    }

    /**
     * Removes a column from the table
     * @param index the index of the column to be removed
     */
    public void removeColumn(int index) {
        columns--;
        String[][] newElements = new String[rows][columns];
        for(int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                if (col != index + 2) newElements[row][col] = elements[row][col];
            }
        }
        elements = newElements;
    }

    /**
     * Converts this table into a markdown string
     * @return a string representation of this object
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\n");

        for (int row = 0; row < elements.length; row++) {
            for (int column = 0; column < elements[row].length; column++) {
                // Bounding side row
                if (row == 1) {
                    sb.append('|');
                    switch (columnTypes[column]) {
                        case LEFT_BOUND: { // left colon |:--|
                            sb.append(":---");
                            break;
                        }
                        case CENTER_BOUND: { // both colons |:-:|
                            sb.append(":---:");
                            break;
                        }
                        case RIGHT_BOUND: { // right colon |--:|
                            sb.append("---:");
                            break;
                        }
                        case NO_BOUND: { // No colon   |---|
                            sb.append("---");
                            break;
                        }
                    }
                    if (column == elements[row].length-1) {
                        sb.append("|\n");
                    }
                    continue;
                }

                sb.append("| ").append(elements[row][column]).append(' ');
                if (column == elements[row].length-1) {
                    sb.append("|\n");
                }
            }
        }
        sb.append("\n");
        return sb.toString();
    }

    /**
     * Default getter
     * @return the number of Rows
     */
    public int getRowCount() {
        return rows;
    }

    /**
     * Default getter
     * @return the number of columns
     */
    public int getColumnCount() {
        return columns;
    }

    public static Optional<Table> fromLineStream(Stream<String> lines) {
        int rows, columns;
        // First, filter out all lines that don't contain pipes, since they cant be part of a table
        List<String> usableLines = lines.filter(s -> s.contains("|")).collect(Collectors.toList());

        /* Secondly, there must be three consecutive lines like this to count as a table:
           header|line
              ---|---
           one or|more value lines
         */

        rows = usableLines.size();
        columns = (int) Arrays.stream(usableLines.get(0).split(Pattern.quote("|"))).filter(s -> s.length() > 0).count();

        Table table = new Table(rows, columns);
        return Optional.of(table);
    }
}
