// Initial driver code taken from https://www.w3schools.com/java/java_files_read.asp
// Code to convert CSV data to arraylist adapted from https://www.baeldung.com/java-csv-file-array
// Punctuation replacement from https://www.studytonight.com/java-examples/how-to-remove-punctuation-from-string-in-java

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Driver{
    public static Sentence convertLine(String line) {
        String[] values = line.split("\",\"");
        String author = values[4];
        String timestamp = formatDate(values[2]);
        String text = removePunct(values[5]);
        
        Sentence converted = new Sentence(text, author, timestamp);
        return converted;
    }

    private static String formatDate(String timestamp) {
        // Example input format required: Mon May 11 03:18:03 UTC 2009
        String[] date = timestamp.split(" ");
        return date[1] + " " + date[2] + " " + date[5].replaceAll("\"","");
    }
    
    private static String removePunct(String line) {
        return line.replaceAll("\\p{Punct}","");
    }

    public static void main(String[] args) {
        try {
            File myObj = new File("testdata.csv");
            Scanner myReader = new Scanner(myObj);

            ArrayList<Sentence> records = new ArrayList<>();

            while (myReader.hasNextLine()) {
                records.add(convertLine(myReader.nextLine()));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
