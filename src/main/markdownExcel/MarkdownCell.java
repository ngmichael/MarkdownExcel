package main.markdownExcel;

import main.api.Cell;

public class MarkdownCell implements Cell {

    private String value;

    MarkdownCell() {
        value = "";
    }
    MarkdownCell(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }
}
