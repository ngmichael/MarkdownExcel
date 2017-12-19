package main;

import main.api.Cell;
import main.api.ColumnFormatting;
import main.api.ImmutableTable;

public class Main {

    public static void main(String[] args) {
        new TableBuilder()
                .setRows(5)
                .setColumns(3)
                .forEachRow((index, table, v) -> v.setValues())
                .setFormatting(2, ColumnFormatting.CENTERED)
                .build()
                .writeToFile("../foo/bar.md");
    }

}
