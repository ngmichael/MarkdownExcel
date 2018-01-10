package main.markdownExcel;

import main.api.*;
import main.api.Vector;

import java.util.*;

public class MarkdownTableBuilder implements TableBuilder {

    private Vector headerRow;
    private Cell[][] values;
    private ColumnFormatting[] formattings;
    private int rows, columns;
    private HashMap<Cell, Formula> formulas;

    MarkdownTableBuilder() {
        rows = 0;
        columns = 0;
        values = new Cell[rows][columns];
        formattings = new ColumnFormatting[columns];
        headerRow = new MarkdownVector(columns);
        formulas = new HashMap<>();
    }

    @Override
    public TableBuilder fromFile(String s) {
        // TODO: implement md file parsing here or call method
        return this;
    }

    @Override
    public TableBuilder fromScratch(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        values = new Cell[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                values[row][column] = new MarkdownCell();
            }
        }
        formattings = new ColumnFormatting[columns];
        Arrays.fill(formattings, ColumnFormatting.NONE);
        formulas = new HashMap<>();
        return this;
    }

    @Override
    public TableBuilder fromImmutableTable(ImmutableTable table) {
        values = new Cell[(int)table.valueStream().count()][0];
        formulas = new HashMap<>();
        return this;
    }

    @Override
    public TableBuilder setHeaderRow(Vector values) {
        this.headerRow = values;
        return this;
    }

    @Override
    public TableBuilder setHeaderRow(String... values) {
        headerRow = new MarkdownVector(values.length);
        for (int i = 0; i < values.length; i++) {
            headerRow.forSingleCell(i, (index, cell) -> cell.setValue(values[index]));
        }
        return this;
    }

    @Override
    public TableBuilder setRows(int rows) {
        values = new Cell[rows][this.columns];
        this.rows = rows;
        return this;
    }

    @Override
    public TableBuilder setColumns(int columns) {
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
    public TableBuilder insertRow(int index) {
        return insertRow(index, new MarkdownVector(columns));
    }

    @Override
    public TableBuilder insertRow(int index, Vector vec) {
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
    public TableBuilder appendRow() {
        return appendRow(new MarkdownVector(columns));
    }

    @Override
    public TableBuilder appendRow(Vector vec) {
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
    public TableBuilder removeRow(int index) {
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
    public TableBuilder insertColumn(int index, String title) {
        return insertColumn(index, title, new String[0]);
    }

    @Override
    public TableBuilder insertColumn(int index, String title, String... vec) {
        columns++;

        // Extend the header row
        Iterator<Cell> hrIter = headerRow.iterator();
        headerRow = new MarkdownVector(columns);
        headerRow.forEachCell((index1, cell) -> {
            if (index1.equals(index)) {
                cell.setValue(title);
            } else cell.setValue(hrIter.next().getValue());
        });

        // Extend the format row
        Iterator<ColumnFormatting> oldFormatings = Arrays.asList(formattings).iterator();
        formattings = new ColumnFormatting[columns];
        for (int i = 0; i < columns; i++) {
            if (i == index) formattings[i] = ColumnFormatting.NONE;
            else formattings[i] = oldFormatings.next();
        }

        // Extend the rest
        Cell[][] newValues = new MarkdownCell[rows][columns];
        Iterator<Cell> vecIter = new MarkdownVector(vec).iterator();
        for(int row = 0; row < rows; row++) {
            Iterator<Cell> oldRow = Arrays.asList(values[row]).iterator();
            for (int col = 0; col < columns; col++) {
                if (col == index) {
                    newValues[row][col] = vecIter.next();
                    continue;
                }
                newValues[row][col] = oldRow.next();
            }
        }

        values = newValues;
        return this;
    }

    @Override
    public TableBuilder appendColumn(String title) {
        return null;
    }

    @Override
    public TableBuilder appendColumn(String title, String... vec) {
        return null;
    }

    @Override
    public TableBuilder deleteColumn(int index) {
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
    public TableBuilder forSingleRow(int index, VectorOperation op) {
        op.manipulateVector(index, this, new MarkdownVector(values[index]));
        return this;
    }

    @Override
    public TableBuilder forSingleColumn(int index, VectorOperation op) {
        Cell[] cells = new Cell[rows];
        for (int row = 0; row < rows; row++) {
            cells[row]=values[row][index];
        }
        op.manipulateVector(index, this, new MarkdownVector(cells));
        return this;
    }

    @Override
    public TableBuilder forEachRow(VectorOperation op) {
        for (int row = 0; row < values.length; row++) {
            op.manipulateVector(row, this, new MarkdownVector(values[row]));
        }
        return this;
    }

    @Override
    public TableBuilder forEachColumn(VectorOperation op) {
        for (int column = 0; column < columns; column++) {
            Cell[] cells = new Cell[rows];
            for (int row = 0; row < rows; row++) {
                cells[row]=values[row][column];
            }
            op.manipulateVector(column, this, new MarkdownVector(cells));
        }

        return this;
    }

    @Override
    public TableBuilder setFormatting(int columnIndex, ColumnFormatting formatting) {
        formattings[columnIndex] = formatting;
        return this;
    }

    @Override
    public TableBuilder addFormula(int row, int column, Formula formula) {
        return addFormula(values[row][column], formula);
    }

    @Override
    public TableBuilder addFormula(Cell c, Formula formula) {
        formulas.put(c, formula);
        return this;
    }

    @Override
    public ImmutableTable build() {
        return new MarkdownTable(values, formattings, headerRow);
    }

}