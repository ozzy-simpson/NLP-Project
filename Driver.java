// Initial driver code taken from https://www.w3schools.com/java/java_files_read.asp
// Code to convert CSV data to arraylist adapted from https://www.baeldung.com/java-csv-file-array
// Punctuation replacement from https://www.studytonight.com/java-examples/how-to-remove-punctuation-from-string-in-java

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Driver{
    private static List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<String>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(",");
            int lineNr = 1;
            while (rowScanner.hasNext()) {
                String value = rowScanner.next();
                if (lineNr == 3) { // Date column
                    value = formatDate(value);
                }
                if (lineNr >= 6) { // Tweet content
                    value = removePunct(value);
                }
                values.add(value);
                lineNr++;
            }
        }
        return values;
    }
    private static String formatDate(String timestamp) {
        // Example input format required: Mon May 11 03:18:03 UTC 2009
        String[] date = timestamp.split(" ");
        return "\"" + date[1] + " " + date[2] + ", " + date[5];
    }
    private static String removePunct(String line) {
        return "\"" + line.replaceAll("\\p{Punct}","") + "\"";
    }

    public static Sentence convertLine(String line) {

        String[] values = line.split("\", \"");
        String author = values[4];
        String timestamp = values[2];
        String text = values[5];
        
        Sentence converted = new Sentence(text, author, timestamp);

        return converted;
    }

    public static void main(String[] args) {
        try {
            List<List<String>> records = new ArrayList<>();
            File myObj = new File("testdata.csv");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                records.add(getRecordFromLine(myReader.nextLine()));
            }
            System.out.println(records);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
