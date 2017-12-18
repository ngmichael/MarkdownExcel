package main.markdownTable;

public interface Vector {

    Vector setValues(String... values);
    String[] getValues();

    int mean();
    int median();
    int length();
    int sum();


}
