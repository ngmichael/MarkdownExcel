package main.api;

@FunctionalInterface
public interface CellOperation {

    void manipulateCell(Integer index, Cell cell);

}
