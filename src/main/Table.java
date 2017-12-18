package main;

import main.markdownTable.MarkdownTable;

public class Table {

    private Table() {}

    public static TableBuilder Builder(){
        // return new TableBuilder();
        return null;
    }

    private static abstract class TableBuilder implements MarkdownTable{
    }

}
