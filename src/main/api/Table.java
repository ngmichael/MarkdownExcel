package main.api;

public interface Table {

    Table fromFile(String s);

    Table fromScratch();
    Table fromScratch(int rows, int columns);

    Table setRows(int rows);
    Table setColumns(int columns);

    Table insertRow(int index);
    Table insertRow(int index, Vector vec);
    Table appendRow();
    Table appendRow(Vector vec);
    Table removeRow(int index);

    Table insertColumn(int index);
    Table insertColumn(int index, Vector vec);
    Table appendColumn();
    Table appendColumn(Vector vec);
    Table deleteColumn(int index);

    Vector getRow(int index);
    Vector getColumn(int index);

    Table forSingleRow(int index, VectorOperation op);
    Table forSingleColumn(int index, VectorOperation op);

    Table forEachRow(VectorOperation op);
    Table forEachColumn(VectorOperation op);

    Table setFormatting(int columnIndex, ColumnFormatting formatting);

    ImmutableTable build();
}
