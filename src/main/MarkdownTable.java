package main;

import main.api.*;

import java.io.*;
import java.util.Arrays;
import java.util.stream.Stream;

public final class MarkdownTable implements ImmutableTable {

    private Cell[][] values;
    private ColumnFormatting[] formattings;

    private MarkdownTable(Cell[][] values, ColumnFormatting[] formattings) {
        this.values = values;
        this.formattings = formattings;
    }

    @Override
    public void writeToFile(String path) {
        try {
            BufferedWriter w = new BufferedWriter(new FileWriter(new File(path)));
            w.write(this.toString());
            w.flush();
            w.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found:");
            System.err.println(path);
        } catch (IOException e) {
            System.err.println("Could not write to file:");
            System.err.println(e.getLocalizedMessage());
        }
    }

    @Override
    public Stream<Vector> valueStream() {
        return Stream.of(values).map(MarkdownVector::new);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Cell[] row : values) {
            sb.append('|');
            for (Cell column : row) {
                sb.append(" ").append(column.getValue()).append(" ").append('|');
            }
            sb.append('\n');
        }
        sb.append('\n');

        return sb.toString();
    }

    public static final class TableBuilder implements Table {

        private Cell[][] values;
        private ColumnFormatting[] formattings;
        private int rows, columns;

        public TableBuilder() {
            rows = 0;
            columns = 0;
            values = new Cell[rows][columns];
            formattings = new ColumnFormatting[0];
        }

        @Override
        public Table fromFile(String s) {
            // TODO: implement md file parsing here or call method
            return this;
        }

        @Override
        public Table fromScratch(int rows, int columns) {
            this.rows = rows;
            this.columns = columns;
            values = new Cell[rows][columns];
            for (int row = 0; row < rows; row++) {
                Arrays.fill(values[row], new MarkdownCell());
            }
            formattings = new ColumnFormatting[columns];
            Arrays.fill(formattings, ColumnFormatting.NONE);
            return this;
        }

        @Override
        public Table fromImmutableTable(ImmutableTable table) {
            values = new Cell[(int)table.valueStream().count()][0];
            return this;
        }

        @Override
        public Table setRows(int rows) {
            values = new Cell[rows][this.columns];
            this.rows = rows;
            return this;
        }

        @Override
        public Table setColumns(int columns) {
            values = new Cell[this.rows][columns];
            this.columns = columns;
            return this;
        }

        @Override
        public int getRowCount() {
            return rows;
        }

        @Override
        public int getColumnCount() {
            return columns;
        }

        @Override
        public Table insertRow(int index) {
            Cell[] values = new Cell[this.columns];
            Arrays.fill(values, new MarkdownCell());
            Vector v = new MarkdownVector(values);
            return insertRow(index, v);
        }

        @Override
        public Table insertRow(int index, Vector vec) {
            rows += 1;
            Cell[][] newValues = new Cell[rows][columns];
            for (int row = 0; row < rows; row++) {
                if (row == index) {
                    System.arraycopy(vec.getValues(), 0, newValues[row], 0, vec.getValues().length);
                    continue;
                }
                System.arraycopy(values[row], 0, newValues[row], 0, values.length);
            }
            values = newValues;
            return this;
        }

        @Override
        public Table appendRow() {
            return appendRow(new MarkdownVector(columns));
        }

        @Override
        public Table appendRow(Vector vec) {
            rows += 1;
            Cell[][] newValues = new Cell[rows][columns];
            for (int row = 0; row < rows-1; row++) {
                System.arraycopy(values[row], 0, newValues[row], 0, values.length);
            }
            System.arraycopy(vec.getValues(), 0, newValues[rows-1], 0, vec.getValues().length);
            values = newValues;
            return this;
        }

        @Override
        public Table removeRow(int index) {
            rows -= 1;
            Cell[][] newValues = new Cell[rows][columns];
            for (int row = 0; row < rows; row++) {
                if (row != index) {
                    System.arraycopy(values[row], 0, newValues[row], 0, values.length);
                }
            }
            values = newValues;
            return this;
        }

        @Override
        public Table insertColumn(int index) {
            Cell[] values = new Cell[this.columns];
            Arrays.fill(values, new MarkdownCell());
            Vector v = new MarkdownVector(values);
            return insertColumn(index, v);
        }

        @Override
        public Table insertColumn(int index, Vector vec) {
            /*
            columns += 1;
            Cell[][] newValues = new Cell[rows][columns];
            for (int col = 0; col < columns; col++) {
                System.arraycopy(values);
            }

        rows += 1;
        Cell[][] newValues = new Cell[rows][columns];
        for (int row = 0; row < rows; row++) {
            if (row == index) {
                System.arraycopy(vec.getValues(), 0, newValues[row], 0, vec.getValues().length);
                continue;
            }
            System.arraycopy(values[row], 0, newValues[row], 0, values.length);
        }
        values = newValues;
        return this;
         */
            return null;
        }

        @Override
        public Table appendColumn() {
            return null;
        }

        @Override
        public Table appendColumn(Vector vec) {
            return null;
        }

        @Override
        public Table deleteColumn(int index) {
            return null;
        }

        @Override
        public Vector getRow(int index) {
            return null;
        }

        @Override
        public Vector getColumn(int index) {
            return null;
        }

        @Override
        public Table forSingleRow(int index, VectorOperation op) {
            op.manipulateVector(index, this, new MarkdownVector(values[index]));
            return this;
        }

        @Override
        public Table forSingleColumn(int index, VectorOperation op) {
            return null;
        }

        @Override
        public Table forEachRow(VectorOperation op) {
            for (int row = 0; row < values.length; row++) {
                op.manipulateVector(row, this, new MarkdownVector(values[row]));
            }
            return this;
        }

        @Override
        public Table forEachColumn(VectorOperation op) {
            return null;
        }

        @Override
        public Table setFormatting(int columnIndex, ColumnFormatting formatting) {
            formattings[columnIndex] = formatting;
            return this;
        }

        @Override
        public ImmutableTable build() {
            return new MarkdownTable(values, formattings);
        }

    }

}
