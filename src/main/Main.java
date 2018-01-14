package main;

import main.api.ColumnFormatting;
import main.api.Formula;
import main.api.ImmutableTable;
import main.api.Vector;
import main.markdownExcel.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.OptionalDouble;
import java.util.concurrent.atomic.AtomicReference;

public class Main {
    public static void main(String[] args) {

        final String FILE_PATH = "/home/noah/ExamScores.md";


        /*
         * The semester is over and the professor needs to find out
         * which students have received a passing grade and who needs
         * to repeat the course.
         *
         * Here is a way how you could do that using formulas.
         */

        MarkdownTable.Builder()
                .fromFile(FILE_PATH)                                                                // Open the file that we have created previously
                .forSingleColumn(4, (columnIndex, tableBuilder, vector) -> {                 // Edit the last column in the table.
                    vector.forEachCell((cellIndex, cell) -> {                                       // We want to add the formula to every cell in the last column
                        cell.setFormula(table -> {                                                  // Here we set the formula. That is all we have to do.
                            Vector examResults = table.getRow(cellIndex).match("(\\d+\\.\\d+)");    // First we get the row for the current cell and filter out all irrelevant cells.
                            OptionalDouble finalResult = examResults.mean();                        // Next we calculate the mean for all three exam results.
                            if (!finalResult.isPresent()) return "MathERR";                         // If anything goes wrong we want to know so we write separate value for that.
                            else return finalResult.getAsDouble() > 50.0 ? "O.K." : "FAILED";       // All that is left to do is to check if the student has actually passed and return
                        });                                                                         // the proper result.
                    });                                                                             // Once the value of the cell is requested, the formula will get
                })                                                                                  // resolved first and its result is returned.
                .build()                                                                            // And now the usual building and writing.
                .writeToFile(FILE_PATH);


    }
}

