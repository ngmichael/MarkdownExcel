package main.markdownExcel;

import main.api.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public final class MarkdownTable implements ImmutableTable {

    private Vector headerRow;
    private ColumnFormatting[] formattings;
    private Cell[][] values;

    @SuppressWarnings("unused")
    private MarkdownTable() {}

    MarkdownTable(Cell[][] values, ColumnFormatting[] formattings, Vector headerRow) {
        this.values = values;
        this.formattings = formattings;
        this.headerRow = headerRow;
    }

    @Override
    public String[] getHeader() {
        Cell[] cells = headerRow.getValues();
        String[] values = new String[cells.length];

        for(int i = 0; i < cells.length; i++) {
            values[i] = cells[i].getValue();
        }
        return values;
    }

    @Override
    public String[] getRow(int index) {
        Cell[] cells = this.values[index];
        String[] values = new String[cells.length];

        for(int i = 0; i < cells.length; i++) {
            values[i] = cells[i].getValue();
        }
        return values;
    }

    @Override
    public String[] getColumn(int index) {
        List<Cell> cells = new ArrayList<>();
        for (Cell[] row : values) {
            cells.add(row[index]);
        }
        String[] values = new String[cells.size()];

        for(int i = 0; i < cells.size(); i++) {
            values[i] = cells.get(i).getValue();
        }
        return values;
    }

    @Override
    public String getCell(int row, int column) {
        return values[row][column].getValue();
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
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('|');

        // Add the header row to the string;
        headerRow.forEachCell((index, cell) -> sb.append(" ").append(cell.getValue()).append(" |"));
        sb.append('\n');

        // Add the column formatting row
        sb.append('|');
        for (ColumnFormatting c : formattings) {
            switch (c) {
                case NONE: sb.append("---");
                    break;
                case LEFT_BOUND: sb.append(":--");
                    break;
                case CENTERED: sb.append(":-:");
                    break;
                case RIGHT_BOUND: sb.append("--:");
                    break;
            }
            sb.append("|");
        }
        sb.append('\n');

        // Add the actual table itself
        for (Cell[] rows : values) {
            sb.append('|');
            for (Cell cell : rows) {
                sb.append(" ").append(cell != null ? cell.getValue() : "(null)").append(" ").append('|');
            }
            sb.append('\n');
        }

        return sb.toString();
    }

    public static TableBuilder Builder() {
        return new MarkdownTableBuilder();
    }

}
