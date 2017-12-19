package main.api;

public interface Vector {

    Vector setValues(String... values);
    String[] getValues();

    void forEachCell(CellOperation op);
    void forSingleCell(CellOperation op);

    int mean();
    int median();
    int length();
    int sum();


}
