package main.markdown;

import java.util.Arrays;

public class Table {

    private String[][] elements;
    private ColumnType[] columnTypes;

    /**
     * Creates an empty table instance.
     *
     * @param numberColumns - The number of columns of this table
     * @param numberRows - The number of rows in this column
     */
    public Table(int numberRows, int numberColumns) {
        elements = new String[numberRows][numberColumns];
        columnTypes = new ColumnType[numberColumns];
        Arrays.fill(columnTypes, ColumnType.NO_BOUND);
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
        elements[row][column] = value;
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

    public enum ColumnType {
        LEFT_BOUND,
        CENTER_BOUND,
        RIGHT_BOUND,
        NO_BOUND
    }

}
