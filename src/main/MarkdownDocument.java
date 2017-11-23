package main;

import main.markdown.ColumnType;
import main.markdown.Table;

public class MarkdownDocument {

    public static void main(String[] args) {
        Table t = new Table(5, 3);
        t.setHeaderRow("a", "b", "c");
        t.insertColumn(4, ColumnType.NO_BOUND);
        System.out.println(t);
        System.out.println(t.getRowCount());
    }
}
