// Initial driver code taken from https://www.w3schools.com/java/java_files_read.asp

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;
import java.util.Properties;
import org.ejml.simple.SimpleMatrix;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations.SentimentAnnotatedTree;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;

public class Driver {

    public static void main(String[] args) {

        try {
            File myObj = new File("testdata.csv");
            Scanner myReader = new Scanner(myObj);

            ArrayList<Sentence> records = new ArrayList<>();

            while (myReader.hasNextLine()) {
                records.add(Sentence.convertLine(myReader.nextLine()));
            }
            myReader.close();

            String filter = "May 1 2009-May 20 2009";
            ArrayList<Sentence> filteredTweets = new ArrayList<>();
            for (int i = 0; i < records.size(); i++) {
                if (records.get(i).keep(filter)) {
                    filteredTweets.add(records.get(i));
                }
            }
            
            HashMap<String, Integer> map = new HashMap<>();

            // PART 3 FOR LOOP - UPDATE HASHMAP
            for (int i = 0; i < filteredTweets.size(); i++) {
                Sentence tweet = filteredTweets.get(i);

                //PART 4: Loop through ArrayList, print the tweet and then the sentiment score 
                System.out.println(tweet.getSentiment() + ": " + tweet);
                
                ArrayList<String> words = tweet.splitSentence(tweet.getText());
                String pair = "";
                for (int j = 0; j < words.size(); j++) { // Adapted from https://stackoverflow.com/a/31289506
                    String word = words.get(j);
                    if (map.get(word) == null) {
                        map.put(word, 1);
                    } else {
                        int newValue = Integer.valueOf(String.valueOf(map.get(word)));
                        newValue++;
                        map.put(word, newValue);
                    }
                    // bi-gram
                    if (j+1 < words.size()) {
                        pair = word + " " + words.get(j+1);
                        if (map.get(pair) == null) {
                            map.put(pair, 1);
                        } else {
                            int newValue = Integer.valueOf(String.valueOf(map.get(pair)));
                            newValue++;
                            map.put(pair, newValue);
                        }
                    }
                    // tri-gram
                    if (j+2 < words.size()) {
                        pair = word + " " + words.get(j+1) + " " + words.get(j+2);
                        if (map.get(pair) == null) {
                            map.put(pair, 1);
                        } else {
                            int newValue = Integer.valueOf(String.valueOf(map.get(pair)));
                            newValue++;
                            map.put(pair, newValue);
                        }
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


