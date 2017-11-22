package main.markdown;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.Optional;
import java.util.stream.Stream;

public class MarkdownTable {

    private String[][] elements;
    private ColumnType[] columnTypes;
    private int columnWidth;

    /**
     * Creates an empty table instance.
     *
     * @param numberColumns - The number of columns of this table
     * @param numberRows - The number of rows in this column
     */
    public MarkdownTable(int numberRows, int numberColumns) {
        elements = new String[numberRows][numberColumns];
        columnTypes = new ColumnType[numberColumns];
        // Prefill table cells
        for (int i = 0; i < numberRows; i++) {
            String[] init = new String[numberColumns];
            Arrays.fill(init, " ");
            elements[i] = init;
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
        if (row < 0 || row >= elements.length)
            throw new IllegalArgumentException("Row " + row + " is out of bounds!");
        if (column < 0 || column >= elements[row].length)
            throw new IllegalArgumentException("Column " + column + " is out of bounds!");
        return elements[row][column];
    }

    /**
     * Sets the value of a cell
     * @param row the row of the cell
     * @param column the column of the cell
     * @param value the value to be set
     * @throws IllegalArgumentException if row or column index is out of bounds
     */
    public void set(int row, int column, String value) throws IllegalArgumentException {
        if (row < 0 || row >= elements.length)
            throw new IllegalArgumentException("Row " + row + " is out of bounds!");
        if (column < 0 || column >= elements[row].length)
            throw new IllegalArgumentException("Column " + column + " is out of bounds!");
        columnWidth = 2 + value.length();
        elements[row][column] = value;
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
     * Converts this table into a markdown string
     * @return a string representation of this object
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int row = 0; row < elements.length; row++) {
            for (int column = 0; column < elements[row].length; column++) {
                // Bounding side row
                if (row == 1) {
                    sb.append('|');
                    switch (columnTypes[column]) {
                        case LEFT_BOUND: { // left colon |:---|
                            sb.append(":---");
                            break;
                        }
                        case CENTER_BOUND: { // both colons |:---:|
                            sb.append(":---:");
                            break;
                        }
                        case RIGHT_BOUND: { // right colon |---:|
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
        return sb.toString();
    }

    public static Optional<MarkdownTable> fromLineStream(Stream<String> lines) {
        int rows, columns;
        // First, filter out all lines that don't contatin pipes, since they cant be part of a table
        List<String> usableLines = lines.filter(s -> s.contains("|")).collect(Collectors.toList());

        /* Secondly, there must be three consecutive lines like this to count as a table:
           header|line
              ---|---
           one or|more value lines
         */

        rows = usableLines.size();
        columns = (int) Arrays.stream(usableLines.get(0).split(Pattern.quote("|"))).filter(s -> s.length() > 0).count();

        MarkdownTable table = new MarkdownTable(rows, columns);
        return Optional.of(table);
    }
}
