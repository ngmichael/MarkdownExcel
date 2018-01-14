import main.markdownExcel.*;
import main.api.*;
import main.api.Vector;

String FILE_PATH = "/home/noah/ExamScores.md";
Iterator<Double> results = Arrays.asList(new Double[]{ 85.0, 60.0, 49.5, 32.5}).iterator();

/*
 * The final exam has passed. There is one restriction though. The score of the finals
 * only counts if the student has reached at least 75 points combined in the previous
 * 2 exams. If this criterium is not met, the score for the final will be 0.
 *
 * The following example shows how you could implement that.
 */

/*
MarkdownTable.Builder()
        .fromFile(FILE_PATH)                                                                // Open the file that we have created previously
        .forSingleColumn(3, (columnIndex, tableBuilder, vector) -> {                        // Again we just want to edit a single column
            vector.forEachCell((cellIndex, cell) -> {                                       // and we want to edit every cell of this column
                Vector row = tableBuilder.getRow(cellIndex);                                // Here we get the row that this cell sits in
                double ex1 = Double.parseDouble(row.getValues()[1].getValue());             // And we get the results of the first two exams
                double ex2 = Double.parseDouble(row.getValues()[2].getValue());             //
                if (ex1 + ex2 >= 75.0) cell.setValue(String.valueOf(results.next()));       // After that we add them together and see if the
                else cell.setValue("0.0");                                                  // student matches the criteria.
            });                                                                             //
        })                                                                                  //
        .build()                                                                            // And now the usual building and writing.
        .writeToFile(FILE_PATH);
*/

// Code line for the JShell
MarkdownTable.Builder().fromFile(FILE_PATH).forSingleColumn(3, (columnIndex, tableBuilder, vector) -> { vector.forEachCell((cellIndex, cell) -> { Vector row = tableBuilder.getRow(cellIndex); double ex1 = Double.parseDouble(row.getValues()[1].getValue()); double ex2 = Double.parseDouble(row.getValues()[2].getValue()); if (ex1 + ex2 >= 75.0) cell.setValue(String.valueOf(results.next())); else cell.setValue("0.0"); }); }).build().writeToFile(FILE_PATH);
