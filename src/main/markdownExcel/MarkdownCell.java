package main.markdownExcel;

import main.api.Cell;
import main.api.Formula;
import main.api.TableBuilder;

public class MarkdownCell implements Cell {

    private String value;
    private Formula formula;
    private boolean hasFormula;

    private int index;
    private TableBuilder builderInstance;

    MarkdownCell() {
        value = "";
        hasFormula = false;
        formula = null;
    }
    MarkdownCell(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        if (!hasFormula)
            return value;
        value = formula.execute(index, this, builderInstance);
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
        hasFormula = false;
    }

    @Override
    public void setFormula(Formula formula) {
        this.formula = formula;
        this.hasFormula = true;
    }

    @Override
    public Formula getFormula() {
        return this.hasFormula ? formula : null;
    }
}
