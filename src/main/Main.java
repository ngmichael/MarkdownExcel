package main;

import main.api.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        MarkdownTable t = (MarkdownTable) MarkdownTable.Builder()
                .fromScratch(5, 5)
                .setHeaderRow("A", "s", "d", "f", "!")
                .build();

        // System.out.println(t.toString());

        String s = MarkdownTable.Builder()
                .fromScratch(2, 6)
                .setHeaderRow("A", "s", "d", "f", "2", "!")
                .forEachRow((index, tableBuilder, vector) -> vector.forEachCell((index1, cell) -> cell.setValue(" ")))
                .insertColumn(2, "x", "a", "b")
                .build().toString();

        System.out.println(s);
    }
}
