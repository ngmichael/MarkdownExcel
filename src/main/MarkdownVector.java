package main;

import main.api.Cell;
import main.api.CellOperation;
import main.api.Vector;

import java.util.OptionalDouble;

public class MarkdownVector implements Vector{

    int index;
    private Cell[] values;

    @Override
    public Vector setValues(Cell... values) {
        return null;
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
}
