package main;

import main.api.ImmutableTable;
import main.markdownExcel.*;

public class Main {
    public static void main(String[] args) {
        String s = MarkdownTable.Builder()
                .fromScratch(4, 5)
                .setHeaderRow("Name", "Termin 1", "Termin 2", "Termin 3", "Anwesenheit OK")
                .forSingleColumn(0, (index, tableBuilder, vector) -> {
                    vector.setValues("Max Mustermann", "Anita Musterfrau", "Ken Thompson", "Elon Musk");
                })
                .forSingleColumn(1, (index, tableBuilder, vector) -> {
                    vector.forEachCell((index1, cell) -> {
                        if (index1 == 1) {
                            cell.setValue("F");
                        }
                        else {
                            cell.setValue("X");
                        }
                    });
                }).build().toString();

        System.out.println(s);
    }
}
