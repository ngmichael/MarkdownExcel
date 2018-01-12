package main.markdownExcel;

import main.api.*;

import java.io.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;

public final class MarkdownTable implements ImmutableTable {

    private Vector headerRow;
    private ColumnFormatting[] formattings;
    private Cell[][] values;

    private MarkdownTable() {}

    MarkdownTable(Cell[][] values, ColumnFormatting[] formattings, Vector headerRow) {
        this.values = values;
        this.formattings = formattings;
        this.headerRow = headerRow;
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
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('|');

        // Add the header row to the string;
        headerRow.forEachCell((index, cell) -> {
            sb.append(" ").append(cell.getValue()).append(" |");
        });
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
        for (int row = 0; row < values.length; row++) {
            sb.append('|');
            for (int col = 0; col < values[row].length; col++) {
                Cell c = values[row][col];
                sb.append(" ").append(c != null ? c.getValue() : "(null)").append(" ").append('|');
            }
            sb.append('\n');
        }
        sb.append('\n');

        return sb.toString();
    }

    public static TableBuilder Builder() {
        return new MarkdownTableBuilder();
    }

}
