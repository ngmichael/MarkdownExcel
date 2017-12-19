package main.api;

/**
 * An ImmutableTable
 *
 * @author Noah George Michael <noah.michael@mni.thm.de>
 * @since 19.12.2017
 */
public interface ImmutableTable {

    Table edit();
    void writeToFile(String path);

}
