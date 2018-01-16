package main.api;

import java.util.stream.Stream;

/**
 * An ImmutableTable
 *
 * @author Noah George Michael <noah.michael@mni.thm.de>
 * @since 19.12.2017
 */
public interface ImmutableTable {

    String[] getHeader();
    String[] getRow(int index);
    String[] getColumn(int index);
    String getCell(int row, int column);

    void writeToFile(String path);
    String toString();

}
