package main.markdownExcel;

import main.api.Cell;
import main.api.CellOperation;
import main.api.Vector;

import java.util.Iterator;
import java.util.OptionalDouble;

public class MarkdownVector implements Vector{

    private Cell[] values;

    private MarkdownVector(){}

    MarkdownVector(int size) {
        values = new Cell[size];
        for (int i = 0; i < size; i++) {
            values[i] = new MarkdownCell();
        }
    }

    MarkdownVector(Cell... values) {
        this.values = values;
    }

    MarkdownVector(String... values) {
        this(values.length);
        forEachCell((index, cell) -> {
            cell.setValue(values[index]);
        });
    }

    @Override
    public Vector setValues(Cell... values) {
        this.values = values;
        return this;
    }

    @Override
    public Cell[] getValues() {
        return values;
    }

    @Override
    public void forEachCell(CellOperation op) {
        for (int i = 0; i < values.length; i++) {
            op.manipulateCell(i, values[i]);
        }
    }

    @Override
    public void forSingleCell(int index, CellOperation op) {
        op.manipulateCell(index, values[index]);
    }

    @Override
    public OptionalDouble mean() {
        return OptionalDouble.empty();
    }

    @Override
    public OptionalDouble median() {
        return OptionalDouble.empty();
    }

    @Override
    public OptionalDouble sum() {
        return OptionalDouble.empty();
    }

    @Override
    public int length() {
        return values.length;
    }

    @Override
    public Iterator<Cell> iterator() {
        return new Iterator<>() {

            private int index;

            @Override
            public boolean hasNext() {
                return index < values.length;
            }

            @Override
            public Cell next() {
                if (index >= values.length) return null;
                return values[index++];
            }
        };
    }
}