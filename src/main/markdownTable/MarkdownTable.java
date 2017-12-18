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

    MarkdownTable forSingleRow(int index, VectorOperation op);
    MarkdownTable forSingleColumn(int index, VectorOperation op);

    MarkdownTable forEachRow(VectorOperation op);
    MarkdownTable forEachColumn(VectorOperation op);

    MarkdownTable setFormatting(int columnIndex, ColumnFormatting formatting);

    Table build();
}
