import main.markdownExcel.*;
import main.api.*;

String FILE_PATH = "/home/noah/ExamScores.md";

/*
 * Code for creating a MarkdownTable with to track the exam score
 * of students for their course during the semester
 * The header is pre-filled with describing column titles.
 * Finally we fill in the names of the 4 students.
 */

/*
MarkdownTable.Builder()
        .fromScratch(4, 5)                                            // 4 Data rows + header and formatting row, 5 columns
        .setHeader("Name", "Exam 1", "Exam 2", "Final Exam", "Pass")  // Give each column a meaningful name
        .forSingleColumn(0, (index, tableBuilder, vector) -> {        // Execute the actions described in this
            vector.setValues("Max", "Moritz", "Hensel", "Gretel");    // lambda expression for only one column.
        })                                                            // In this case, the first column.
        .build()                                                      // Build the MarkdownTable instance, since we don't need to modify the table any further.
        .writeToFile(FILE_PATH);                                      // Write the MarkdownTable to disk at the specified file path.
*/

// Code line for the JShell
MarkdownTable.Builder().fromScratch(4, 5).setHeader("Name", "Exam 1", "Exam 2", "Final Exam", "Pass").forSingleColumn(0, (index, tableBuilder, vector) -> {vector.setValues("Max", "Moritz", "Hensel", "Gretel");}).build().writeToFile(FILE_PATH);
