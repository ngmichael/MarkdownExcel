package main.api;

import main.MarkdownTable.TableBuilder;

import java.util.stream.Stream;

/**
 * An ImmutableTable
 *
 * @author Noah George Michael <noah.michael@mni.thm.de>
 * @since 19.12.2017
 */
public interface ImmutableTable {

    static Table Builder() {
        return new TableBuilder();
    }
    void writeToFile(String path);
    Stream<Vector> valueStream();

}
