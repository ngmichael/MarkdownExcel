import main.markdownExcel.*;
import main.api.*;

String FILE_PATH = "/home/noah/ExamScores.md";

/*
 * The semester is over and the professor needs to find out
 * which students have received a passing grade and who has
 * to repeat the course.
 *
 * Here is a way how you could do that using formulas.
 */

/*
MarkdownTable.Builder()
        .fromFile(FILE_PATH)                                                                // Open the file that we have created previously
        .forSingleColumn(4, (columnIndex, tableBuilder, vector) -> {                        // Edit the last column in the table.
            vector.forEachCell((cellIndex, cell) -> {                                       // We want to add the formula to every cell in the last column
                cell.setFormula(table -> {                                                  // Here we set the formula. That is all we have to do.
                    Vector examResults = table.getRow(cellIndex).match("(\\d+\\.\\d+)");    // First we get the row for the current cell and filter out all irrelevant cells.
                    OptionalDouble finalResult = examResults.mean();                        // Next we calculate the mean for all three exam results.
                    if (!finalResult.isPresent()) return "MathERR";                         // If anything goes wrong we want to know so we write separate value for that.
                    else return finalResult.getAsDouble() >= 50.0 ? "O.K." : "FAILED";      // All that is left to do is to check if the student has actually passed and return
                });                                                                         // the proper result.
            });                                                                             // Once the value of the cell is requested, the formula will get
        })                                                                                  // resolved first and its result is returned.
        .build()                                                                            // And now the usual building and writing.
        .writeToFile(FILE_PATH);
*/

// Code line for the JShell
MarkdownTable.Builder().fromFile(FILE_PATH).forSingleColumn(4, (columnIndex, tableBuilder, vector) -> { vector.forEachCell((cellIndex, cell) -> { cell.setFormula(table -> { Vector examResults = table.getRow(cellIndex).match("(\\d+\\.\\d+)"); OptionalDouble finalResult = examResults.mean(); if (!finalResult.isPresent()) return "MathERR"; else return finalResult.getAsDouble() >= 50.0 ? "O.K." : "FAILED"; }); }); }).build().writeToFile(FILE_PATH);
