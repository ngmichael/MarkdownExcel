package main;

import main.api.*;

import java.util.stream.Stream;

public final class MarkdownTable implements ImmutableTable{

    private Cell[][] values;

    @Override
    public void writeToFile(String path) {

    }

    @Override
    public Stream<Vector> valueStream() {
        return Stream.of(values).map(MarkdownVector::new);
    }


}
