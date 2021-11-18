// Driver code taken from https://www.w3schools.com/java/java_files_read.asp
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
                if (lineNr == 6 || lineNr == 7) {
                    value = "\"" + value.replaceAll("\\p{Punct}","") + "\"";
                }
                values.add(value);
                lineNr++;
            }
        }
        return values;
    }
    public static void main(String[] args) {
        try {
            List<List<String>> records = new ArrayList<>();
            File myObj = new File("testdata.csv");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                records.add(getRecordFromLine(myReader.nextLine()));
                /*String data = myReader.nextLine();
                System.out.println(data);*/
            }
            System.out.println(records);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
