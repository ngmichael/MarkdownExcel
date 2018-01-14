import main.markdownExcel.*;
import main.api.*;

String FILE_PATH = "/home/noah/ExamScores.md";

/* 
 * Exam day 1 has come and gone and we want to fill in the results
 * into our MarkdownTable. The code below shows a possibility
 * how to do that.
 */

/*
MarkdownTable.Builder()
        .fromFile(FILE_PATH)                                    // Open the file that we have created previously
        .forSingleColumn(1, (index, tableBuilder, vector) -> {  // Again we just want to edit a single column
            vector.setValues("100.0", "75.0", "66.5", "23.0");  // Set the values for that column
        })
        .build()                                                // And now the usual building and wrtiing.
        .writeToFile(FILE_PATH);
*/

// Code line for the JShell
MarkdownTable.Builder().fromFile(FILE_PATH).forSingleColumn(1, (index, tableBuilder, vector) -> vector.setValues("100.0", "75.0", "66.5", "23.0")).build().writeToFile(FILE_PATH);
