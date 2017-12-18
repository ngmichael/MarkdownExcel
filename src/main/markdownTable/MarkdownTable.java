package main.markdownTable;

import main.Table;

public interface MarkdownTable {

    MarkdownTable fromFile(String s);

    MarkdownTable fromScratch();
    MarkdownTable fromScratch(int rows, int columns);

    MarkdownTable insertRow(int index);
    MarkdownTable appendRow();
    MarkdownTable removeRow(int index);

    MarkdownTable insertCoulmn(int index);
    MarkdownTable appendColumn();
    MarkdownTable deleteColumn(int index);

    Vector getRow(int index);
    Vector getColumn(int index);

    void forEachRow(VectorOperation op);
    void forEachColumn(VectorOperation op);

    MarkdownTable setFormatting(int column);

    Table build();
}
