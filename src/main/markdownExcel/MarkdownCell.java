package main.markdownExcel;

import main.api.Cell;
import main.api.Formula;
import main.api.TableBuilder;

public class MarkdownCell implements Cell {

    private String value;
    private Formula formula;
    private boolean hasFormula;
    private boolean isExecuting;

    private TableBuilder builderInstance;

    MarkdownCell(TableBuilder builder) {
        value = "";
        hasFormula = false;
        isExecuting = false;
        formula = null;
    }

    @Override
    public String getValue() {
        if (!hasFormula)
            return value;
        else if (!isExecuting){
            isExecuting = true;
            value = formula.execute(builderInstance);
        }
        else {
            return "\"UNDEFINED\"";
        }
        isExecuting = false;
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

    @Override
    public boolean hasFormula() {
        return hasFormula;
    }
}
