package main;

import main.api.Cell;
import main.api.ColumnFormatting;
import main.api.ImmutableTable;

public class Main {
    public static void main(String[] args) {
        MarkdownTable t = (MarkdownTable) ImmutableTable.Builder()
                .fromScratch(5, 5)
                .forEachRow((index, table, vector) -> {
                    vector.forSingleCell(index, (index1, cell) -> {
                        cell.setValue("x");
                    });
                })
                .build();
    }
}
