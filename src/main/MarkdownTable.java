package main;

import main.api.*;

import java.util.stream.Stream;

public final class MarkdownTable implements ImmutableTable{

    private Cell[][] values;

    @Override
    public Table edit() {
        // TODO: Implement copy of values from this table to builder instance
        TableBuilder t = new TableBuilder();
        for (Cell[] row: values) {
            t.appendRow();
        }
        return t;
    }

    @Override
    public void writeToFile(String path) {

    }

    @Override
    public Stream<Vector> valueStream() {
        return Stream.of(values).map(MarkdownVector::new);
    }


}
