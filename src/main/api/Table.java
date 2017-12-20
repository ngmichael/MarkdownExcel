package main.api;

/**
 * Interface Table
 *
 * This interface defines a builder class for Table instances.
 *
 *
 */
public interface Table {

    Table fromFile(String s);
    Table fromScratch(int rows, int columns);
    Table fromImmutableTable(ImmutableTable table);

    Table setRows(int rows);
    Table setColumns(int columns);

    int getRowCount();
    int getColumnCount();

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
