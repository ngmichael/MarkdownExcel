package main.markdown;

import java.util.Arrays;

public class Table {

    private String[][] elements;
    private int[] columnType;

    /**
     * Creates an empty table instance.
     *
     * @param numberColumns - The number of columns of this table
     * @param numberRows - The number of rows in this column
     */
    public Table(int numberRows, int numberColumns) {
        elements = new String[numberRows][numberColumns];
        columnType = new int[numberColumns];
        Arrays.fill(columnType, 0);
    }

    public String get(int row, int column) throws IllegalArgumentException {
        if (row < 0 || row >= elements.length)
            throw new IllegalArgumentException("Row " + row + " is out of bounds!");
        if (column < 0 || column >= elements[row].length)
            throw new IllegalArgumentException("Column " + column + " is out of bounds!");
        return elements[row][column];
    }

    public void set(int row, int column, String value) throws IllegalArgumentException {
        if (row < 0 || row >= elements.length)
            throw new IllegalArgumentException("Row " + row + " is out of bounds!");
        if (column < 0 || column >= elements[row].length)
            throw new IllegalArgumentException("Column " + column + " is out of bounds!");
        elements[row][column] = value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int row = 0; row < elements.length; row++) {
            for (int column = 0; column < elements[row].length; column++) {
                // Bounding side row
                if (row == 1) {
                    // TODO do stuff
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

}
