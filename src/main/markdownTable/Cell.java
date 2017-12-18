package main.markdownTable;

import java.util.Optional;

public abstract class Cell<T> {

    private int x, y;
    private T value;

    public Cell(int x, int y, T value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    /**
     * Tries to match the supplied regular expression against the value
     * of this cell. The returned value is a boolean which will
     * be false if the RegEx didn't match the value or true if it did.
     *
     * @param regex A regular expression to match against the value
     * @return a boolean representing the result of the matching process
     */
    public boolean matches(String regex) {
        return value.toString().matches(regex);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
