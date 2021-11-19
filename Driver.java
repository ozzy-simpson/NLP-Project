// Initial driver code taken from https://www.w3schools.com/java/java_files_read.asp

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.text.ParseException;
import java.util.TimeZone;

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
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy",Locale.ENGLISH);
            sdf.setTimeZone(TimeZone.getTimeZone("UTC")); // https://stackoverflow.com/a/19115247
            Date date = sdf.parse(timestamp);
            SimpleDateFormat newDate = new SimpleDateFormat("MMM dd yyyy");
            newDate.setTimeZone(TimeZone.getTimeZone("UTC")); // https://stackoverflow.com/a/19115247
            String formattedDate = newDate.format(date);

            return formattedDate;
        } catch (ParseException e) { // https://stackoverflow.com/a/28960155
            e.printStackTrace();
            return timestamp;
        }
    }
    
    // https://www.studytonight.com/java-examples/how-to-remove-punctuation-from-string-in-java
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
