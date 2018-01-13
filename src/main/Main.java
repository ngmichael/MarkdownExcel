package main;

import main.api.ImmutableTable;
import main.markdownExcel.*;

public class Main {
    public static void main(String[] args) {
        String s = MarkdownTable.Builder()
                .fromScratch(4, 5)
                .appendColumn("AsdfMovie")
                
                .build()
                .toString();

        System.out.println(s);
    }
}
