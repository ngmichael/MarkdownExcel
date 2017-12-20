package main;

import main.api.*;

import java.util.Arrays;

public final class TableBuilder implements Table {

    private int rows, columns;
    private Cell[][] values;

    public TableBuilder() {
        values = new Cell[0][0];
        rows = 0;
        columns = 0;
    }

    @Override
    public Table fromFile(String s) {
        // TODO: implement md file parsing here or call method
        return this;
    }

    @Override
    public Table fromScratch(int rows, int columns) {
        values = new Cell[rows][columns];
        this.rows = rows;
        this.columns = columns;
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
        return null;
    }

    @Override
    public Table insertColumn(int index, Vector vec) {
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
        return null;
    }

    @Override
    public Table forSingleColumn(int index, VectorOperation op) {
        return null;
    }

    @Override
    public Table forEachRow(VectorOperation op) {
        return null;
    }

    @Override
    public Table forEachColumn(VectorOperation op) {
        return null;
    }

    @Override
    public Table setFormatting(int columnIndex, ColumnFormatting formatting) {
        return null;
    }

    @Override
    public ImmutableTable build() {
        return null;
    }
}