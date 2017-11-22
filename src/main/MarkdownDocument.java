package main;

import main.markdown.MarkdownTable;

public class MarkdownDocument {

    public static void main(String[] args) {
        MarkdownTable t = new MarkdownTable(3, 5);
        System.out.println(t);
    }
}
