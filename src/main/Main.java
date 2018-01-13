package main;

import main.api.ImmutableTable;
import main.markdownExcel.*;

public class Main {
    public static void main(String[] args) {
        String s = MarkdownTable.Builder()
                .fromFile("/home/noah/SWT_Team.md")
                .build()
                .toString();

        System.out.println(s);
    }
}

/*

        MarkdownTable.Builder()
                .fromScratch(4, 5)
                .setHeader("Name", "Termin 1", "Termin 2", "Termin 3", "Zulassung erreicht")
                .forSingleColumn(0, (index, tableBuilder, vector) -> {
                    vector.setValues("Hensel", "Gretel", "Max", "Moritz");
                })
                .build()
                .writeToFile("/home/noah/SWT_Team.md");

 */
