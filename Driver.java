// Driver code taken from https://www.w3schools.com/java/java_files_read.asp
// Code to convert CSV data to arraylist taken from https://www.baeldung.com/java-csv-file-array

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Driver{
    private static List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<String>();
        try (Scanner rowScanner = new Scanner(line)) {
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
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
