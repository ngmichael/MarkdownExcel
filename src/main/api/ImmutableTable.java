package main.api;

import main.TableBuilder;

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

    Table edit();
    void writeToFile(String path);

}
