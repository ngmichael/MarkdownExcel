package main.markdown.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Parser {

    private long result;

    public Parser(File markdownDocument) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(markdownDocument));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private

    private ArrayList<String[]> findTable(Stream<String> lines) {
        String[] rawTableLines = (String[]) lines.filter(s -> s.contains("|")).toArray();
        for(String s: rawTableLines) {

        }
    }

}
