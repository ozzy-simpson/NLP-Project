// Initial driver code taken from https://www.w3schools.com/java/java_files_read.asp

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.text.ParseException;
import java.util.TimeZone;
import java.util.Map;
import java.util.Collections;

public class Driver {
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
            SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
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
    public static String removePunct(String line) {
        return line.replaceAll("\\p{Punct}", "");
    }
    public static String[] removePunct(String[] lines) {
        String newLines = "";
        for (int i = 0; i < lines.length; i++) {
            newLines += lines[i].replaceAll("\\p{Punct}", "") + ",";
        }
        return newLines.split(",");
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

            HashMap<String, Integer> map = new HashMap<>();

            // for loop for part 3, loop through arraylist and update hashmap
            for (int i = 0; i < records.size(); i++) {
                Sentence tweet = records.get(i);
                ArrayList<String> words = tweet.splitSentence(tweet.getText());
                for (int j = 0; j < words.size(); j++) // Adapted fromhttps://stackoverflow.com/a/31289506
                {
                    if (map.get(words.get(j)) == null) {
                        map.put(words.get(j), 1);
                    } else {
                        int newValue = Integer.valueOf(String.valueOf(map.get(words.get(j))));
                        newValue++;
                        map.put(words.get(j), newValue);
                    }
                }
            }

            Map.Entry<String, Integer> maxEntry = null;
            for (Map.Entry<String, Integer> entry : map.entrySet())
                if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
                    maxEntry = entry;
            int maxValueLen = maxEntry.getValue().toString().length();
            ArrayList <String> results = new ArrayList<String>();
            for (Map.Entry set : map.entrySet()){
                String value = set.getValue().toString();
                while(value.length() < maxValueLen)
                    value = " " + value;
                results.add(value + " of " + set.getKey());
            }
            Collections.sort(results);
            Collections.reverse(results);
            for (int i = 0; i < 100; i++)
                System.out.println(results.get(i));

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
