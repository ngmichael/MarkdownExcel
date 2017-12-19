package main;

import main.api.*;

public final class TableBuilder implements Table {
    @Override
    public Table fromFile(String s) {
        return null;
    }

    @Override
    public Table fromScratch() {
        return null;
    }

    @Override
    public Table fromScratch(int rows, int columns) {
        return null;
    }

    @Override
    public Table setRows(int rows) {
        return null;
    }

    @Override
    public Table setColumns(int columns) {
        return null;
    }

    @Override
    public Table insertRow(int index) {
        return null;
    }

    @Override
    public Table appendRow() {
        return null;
    }

    @Override
    public Table removeRow(int index) {
        return null;
    }

    @Override
    public Table insertColumn(int index) {
        return null;
    }

    @Override
    public Table appendColumn() {
        return null;
    }

    @Override
    public Table deleteColumn(int index) {
        return null;
    }

    @Override
    public Vector getRow(int index) {
        return null;
    }

    @Override
    public Vector getColumn(int index) {
        return null;
    }

    @Override
    public Table forSingleRow(int index, VectorOperation op) {
        return null;
    }

    @Override
    public Table forSingleColumn(int index, VectorOperation op) {
        return null;
    }

    @Override
    public Table forEachRow(VectorOperation op) {
        return null;
    }

    @Override
    public Table forEachColumn(VectorOperation op) {
        return null;
    }

    @Override
    public Table setFormatting(int columnIndex, ColumnFormatting formatting) {
        return null;
    }

    @Override
    public ImmutableTable build() {
        return null;
    }
}