package main.markdownExcel;

import main.api.*;
import main.api.Vector;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MarkdownTableBuilder implements TableBuilder {

    private Vector headerRow;
    private Cell[][] values;
    private ColumnFormatting[] formattings;
    private int rows, columns;

    MarkdownTableBuilder() {
        rows = 0;
        columns = 0;
        values = new Cell[rows][columns];
        formattings = new ColumnFormatting[columns];
        headerRow = new MarkdownVector(columns, this);
    }

    @Override
    public TableBuilder fromFile(String path) {
        BufferedReader reader;
        List<String> lines = null;
        int rows, columns;

        try {
            reader = new BufferedReader(new FileReader(path));
            lines = reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        rows = lines.size()-2;
        columns = new StringTokenizer(lines.get(0), "|").countTokens();
        this.fromScratch(rows, columns);
        this.setHeader(
                Arrays.stream(lines.get(0).split(Pattern.quote("|")))
                        .filter(s -> !s.equals(""))
                        .map(String::trim)
                        .collect(Collectors.toList())
                        .toArray(new String[columns])
        );
        this.setFormattings(createFormattingRow(lines.get(1)));

        Iterator<String> lineIterator = lines.stream().skip(2).iterator();
        this.forEachRow((index, tableBuilder, vector) -> {
            Iterator<String> values = Arrays.stream(lineIterator.next().split(Pattern.quote("|"))).filter(s -> !s.equals("")).iterator();
            vector.forEachCell((index1, cell) -> cell.setValue(values.next().trim()));
        });
        return this;
    }

    @Override
    public TableBuilder fromScratch(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        values = new Cell[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                values[row][column] = new MarkdownCell(this);
            }
        }
        formattings = new ColumnFormatting[columns];
        Arrays.fill(formattings, ColumnFormatting.NONE);
        return this;
    }

    @Override
    public TableBuilder fromImmutableTable(ImmutableTable table) {
        values = new Cell[(int)table.valueStream().count()][0];
        return this;
    }

    @Override
    public TableBuilder setHeader(Vector values) {
        this.headerRow = values;
        return this;
    }

    @Override
    public TableBuilder setHeader(String... values) {
        headerRow = new MarkdownVector(values.length, this);
        for (int i = 0; i < values.length; i++) {
            headerRow.forSingleCell(i, (index, cell) -> cell.setValue(values[index]));
        }
        return this;
    }

    @Override
    public TableBuilder insertRow(int index) {
        return insertRow(index, new MarkdownVector(columns, this));
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
        return appendRow(new MarkdownVector(columns, this));
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
        headerRow = new MarkdownVector(columns, this);
        headerRow.forEachCell((index1, cell) -> {
            if (index1.equals(index)) {
                cell.setValue(title);
            } else {
                if (hrIter.hasNext()) cell.setValue(hrIter.next().getValue());
                else cell.setValue("");
            }
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
        Iterator<Cell> vecIter = new MarkdownVector(this, vec).iterator();
        for(int row = 0; row < rows; row++) {
            Iterator<Cell> oldRow = Arrays.asList(values[row]).iterator();
            for (int col = 0; col < columns; col++) {
                if (col == index) {
                    Cell newValue = vecIter.next();
                    newValues[row][col] = newValue != null ? newValue : new MarkdownCell(this);
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
        appendColumn(title, new String[0]);
        return this;
    }

    @Override
    public TableBuilder appendColumn(String title, String... vec) {
        insertColumn(columns, title, vec);
        return this;
    }

    @Override
    public TableBuilder removeColumn(int index) {
        columns--;

        // Reduce the header row
        Iterator<Cell> hrIter = headerRow.iterator();
        headerRow = new MarkdownVector(columns, this);
        headerRow.forEachCell((index1, cell) -> {
            if (index1 != index) {
                cell.setValue(hrIter.next().getValue());
            }
        });

        // Extend the format row
        Iterator<ColumnFormatting> oldFormatings = Arrays.asList(formattings).iterator();
        formattings = new ColumnFormatting[columns];
        for (int i = 0; i < columns; i++) {
            if (i != index) formattings[i] = formattings[i] = oldFormatings.next();
        }

        // Extend the rest
        Cell[][] newValues = new MarkdownCell[rows][columns];
        for(int row = 0; row < rows; row++) {
            Iterator<Cell> oldRow = Arrays.asList(values[row]).iterator();
            for (int col = 0; col < columns; col++) {
                if (col != index) {
                    Cell newValue = oldRow.next();
                    newValues[row][col] = newValue != null ? newValue : new MarkdownCell(this);
                    continue;
                }
                newValues[row][col] = oldRow.next();
            }
        }

        values = newValues;
        return this;
    }

    @Override
    public Vector getRow(int index) {
        return new MarkdownVector(this, values[index]);
    }

    @Override
    public Vector getColumn(int index) {
        Cell[] cells = new Cell[rows];
        for (int i = 0; i < rows; i++) {
            cells[i] = values[i][index];
        }
        return new MarkdownVector(this, cells);
    }

    @Override
    public TableBuilder forSingleRow(int index, VectorOperation op) {
        op.manipulateVector(index, this, new MarkdownVector(this, values[index]));
        return this;
    }

    @Override
    public TableBuilder forSingleColumn(int index, VectorOperation op) {
        Cell[] cells = new Cell[rows];
        for (int row = 0; row < rows; row++) {
            cells[row]=values[row][index];
        }
        op.manipulateVector(index, this, new MarkdownVector(this, cells));
        return this;
    }

    @Override
    public TableBuilder forEachRow(VectorOperation op) {
        for (int row = 0; row < values.length; row++) {
            op.manipulateVector(row, this, new MarkdownVector(this, values[row]));
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
            op.manipulateVector(column, this, new MarkdownVector(this, cells));
        }

        return this;
    }

    @Override
    public TableBuilder setFormatting(int columnIndex, ColumnFormatting formatting) {
        formattings[columnIndex] = formatting;
        return this;
    }

    @Override
    public TableBuilder setFormattings(ColumnFormatting... formattings) {
        Iterator<ColumnFormatting> formats = Arrays.asList(formattings).iterator();
        int i = 0;

        while(formats.hasNext() && i < this.formattings.length) {
            this.formattings[i] = formats.next();
            i++;
        }
        return this;
    }

    @Override
    public ImmutableTable build() {
        return new MarkdownTable(values, formattings, headerRow);
    }

    private ColumnFormatting[] createFormattingRow(String rawInput){
        List<String> formatStrings = Arrays.stream(rawInput.split(Pattern.quote("|")))
                .filter(s -> !s.equals(""))
                .map(String::trim)
                .collect(Collectors.toList());
        ColumnFormatting[] formattings = new ColumnFormatting[formatStrings.size()];
        for (int i = 0; i < formatStrings.size(); i++) {
            String format = formatStrings.get(i);
            if (format.startsWith(":") && !format.endsWith(":")) {
                formattings[i] = ColumnFormatting.LEFT_BOUND;
            }
            else if (!format.startsWith(":") && format.endsWith(":")) {
                formattings[i] = ColumnFormatting.RIGHT_BOUND;
            }
            else if (format.startsWith(":") && format.endsWith(":")) {
                formattings[i] = ColumnFormatting.CENTERED;
            }
            else formattings[i] = ColumnFormatting.NONE;
        }
        return formattings;
    }

}