package main;

import main.markdown.Table;

public class MarkdownDocument {

    public static void main(String[] args) {
        Table t = new Table(5, 5);
        t.insertRow(3);
        System.out.println(t);
        System.out.println(t.getRowCount());
    }
}
