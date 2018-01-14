package main.markdownExcel;

import main.api.Cell;
import main.api.CellOperation;
import main.api.TableBuilder;
import main.api.Vector;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.OptionalDouble;

public class MarkdownVector implements Vector{

    private Cell[] values;
    private TableBuilder builder;

    @SuppressWarnings("unused")
    private MarkdownVector(){}

    MarkdownVector(int size, TableBuilder builder) {
        values = new Cell[size];
        this.builder = builder;
        for (int i = 0; i < size; i++) {
            values[i] = new MarkdownCell(builder);
        }
    }

    MarkdownVector(TableBuilder builder, Cell... values) {
        this(values.length, builder);
        this.values = values;
    }

    MarkdownVector(TableBuilder builder, String... values) {
        this(values.length, builder);
        forEachCell((index, cell) -> cell.setValue(values[index]));
    }

    @Override
    public Vector setValues(Cell... values) {
        this.values = values;
        return this;
    }

    @Override
    public Vector setValues(String... values) {
        System.out.println(this.values.length + " " + values.length);
        for (int i = 0; i < Math.min(this.values.length, values.length); i++) {
            this.values[i].setValue(values[i]);
        }
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
        double sum = 0;

        for(Cell c : values){
            String number = c.getValue().replace(" ","");

            try {
                sum += Double.parseDouble(number);
            } catch (NumberFormatException | NullPointerException e) {
                return OptionalDouble.empty();
            }
        }

        return OptionalDouble.of(sum / (double) values.length);
    }

    @Override
    public OptionalDouble median() {
        for (Cell c : values) {
            try {
                //noinspection ResultOfMethodCallIgnored
                Double.parseDouble(c.getValue());
            }
            catch (NullPointerException | NumberFormatException e) {
                return OptionalDouble.empty();
            }
        }

        if (values.length % 2 == 0) {
            double val1 = Double.parseDouble(values[(values.length/2)-1].getValue());
            double val2 = Double.parseDouble(values[values.length/2].getValue());
            return OptionalDouble.of((val1+val2)/2);
        } else {
            return OptionalDouble.of(Double.parseDouble(values[values.length/2].getValue()));
        }
    }

    @Override
    public OptionalDouble sum() {
        double sum = 0d;

        for(Cell c : values){
            String number = c.getValue().replace(" ","");

            if(number.matches("^(-?)(0|([1-9][0-9]*))(\\.[0-9]+)?$")){
                sum = sum + Double.parseDouble(number);
            }
            else return OptionalDouble.empty();
        }

        return OptionalDouble.of(sum);
    }

    @Override
    public int length() {
        return values.length;
    }

    @Override
    public OptionalDouble min() {
        double min = Double.POSITIVE_INFINITY;

        for (Cell c : values) {
            String number = c.getValue().replace(" ","");
            double current;

            try {
                current = Double.parseDouble(number);
            } catch (NullPointerException | NumberFormatException e) {
                return OptionalDouble.empty();
            }

            if (current < min) {
                min = current;
            }
        }

        return OptionalDouble.of(min);
    }

    @Override
    public OptionalDouble max() {
        double max = Double.NEGATIVE_INFINITY;

        for (Cell c : values) {
            String number = c.getValue().replace(" ","");
            double current;
            try {
                current = Double.parseDouble(number);
            } catch (NullPointerException | NumberFormatException e) {
                return OptionalDouble.empty();
            }

            if (current > max) {
                max = current;
            }
        }

        return OptionalDouble.of(max);
    }

    @Override
    public Vector match(String regEx) {
        List<Cell> cellList = new ArrayList<>(values.length);

        for (Cell c : values) {
            if (c.getValue().matches(regEx)){
                cellList.add(c);
            }
        }

        return new MarkdownVector(this.builder, cellList.toArray(new Cell[cellList.size()]));
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
