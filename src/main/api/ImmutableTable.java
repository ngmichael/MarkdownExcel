package main.api;

import java.util.stream.Stream;

/**
 * An ImmutableTable
 *
 * @author Noah George Michael <noah.michael@mni.thm.de>
 * @since 19.12.2017
 */
public interface ImmutableTable {

    void writeToFile(String path);
    Stream<Vector> valueStream();

}
