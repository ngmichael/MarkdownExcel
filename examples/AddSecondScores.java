import main.markdownExcel.*;
import main.api.*;

String FILE_PATH = "/home/noah/ExamScores.md";

/*
 * Something strange happened on exam 2. Everyone got the exact same score.
 * We can use an easier method of filling in the scores because of that.
 */

/*
MarkdownTable.Builder()
        .fromFile(FILE_PATH)                                             // Open the file that we have created previously
        .forSingleColumn(2, (colIndex, tableBuilder, vector) -> {        // Again we just want to edit a single column,
            vector.forEachCell((cellIndex, cell) -> {                    // but this time we want to perform an action for every cell in that column
                cell.setValue("50.0");                                   // and that action is setting the value of all cells.
            });
        })
        // We now realize that we want to change the formatting of the table as well, so we supply some formatting information for each column
        .setFormattings(ColumnFormatting.LEFT_BOUND, ColumnFormatting.CENTERED, ColumnFormatting.CENTERED, ColumnFormatting.CENTERED, ColumnFormatting.RIGHT_BOUND)
        .build()
        .writeToFile(FILE_PATH);
*/

// Code line for the JShell
MarkdownTable.Builder().fromFile(FILE_PATH).forSingleColumn(2, (colIndex, tableBuilder, vector) -> vector.forEachCell((cellIndex, cell) -> cell.setValue("50.0"))).setFormattings(ColumnFormatting.LEFT_BOUND, ColumnFormatting.CENTERED, ColumnFormatting.CENTERED, ColumnFormatting.CENTERED, ColumnFormatting.RIGHT_BOUND).build().writeToFile(FILE_PATH);
