package main;

import main.markdown.Table;

public class MarkdownDocument {

    public static void main(String[] args) {
        Table t = new Table(3, 5);
        t.appendRow();
        t.appendColumn();
        t.set(0, 5, "A");
        System.out.println(t);
    }
}
