import java.util.ArrayList;
import java.util.Arrays;

public class Sentence{
    private String text;
    private String author;
    private String timestamp;

    public Sentence(String t, String a, String ts) {    
        text = t;
        author = a;
        timestamp = ts;
    }

    public String getText(){
        return text;
    }

    public String setText(String t){
        text = t;
        return text;
    }

    public String getAuthor(){
        return author;
    }

    public String setAuthor(String a){
        author = a;
        return author;
    }

    public String getTimestamp(){
        return timestamp;
    }

    public String setTimestamp(String ts){
        timestamp = ts;
        return timestamp;
    }

    public String toString() {
        return "{author:" + author + ", sentence:\"" + text + "\", timestamp:\"" + timestamp + "\"}";
    }

    public ArrayList<String> splitSentence(String sentence) {
        ArrayList<String> words = new ArrayList<String>(Arrays.asList(sentence.toLowerCase().split(" "))); // Adapted from https://stackoverflow.com/a/7488710
        String[] stopwords = {"", "a", "about", "above", "after", "again", "against", "all", "am", "an", "and", "any", "are", "aren't", "as", "at", "be", "because", "been", "before", "being", "below", "between", "both", "but", "by", "can't", "cannot", "could", "couldn't", "did", "didn't", "do", "does", "doesn't", "doing", "don't", "down", "during", "each", "few", "for", "from", "further", "had", "hadn't", "has", "hasn't", "have", "haven't", "having", "he", "he'd", "he'll", "he's", "her", "here", "here's", "hers", "herself", "him", "himself", "his", "how", "how's", "i", "i'd", "i'll", "i'm", "i've", "if", "in", "into", "is", "isn't", "it", "it's", "its", "itself", "let's", "me", "more", "most", "mustn't", "my", "myself", "no", "nor", "not", "of", "off", "on", "once", "only", "or", "other", "ought", "our", "ours ourselves", "out", "over", "own", "same", "shan't", "she", "she'd", "she'll", "she's", "should", "shouldn't", "so", "some", "such", "than", "that", "that's", "the", "their", "theirs", "them", "themselves", "then", "there", "there's", "these", "they", "they'd", "they'll", "they're", "they've", "this", "those", "through", "to", "too", "under", "until", "up", "very", "was", "wasn't", "we", "we'd", "we'll", "we're", "we've", "were", "weren't", "what", "what's", "when", "when's", "where", "where's", "which", "while", "who", "who's", "whom", "why", "why's", "with", "won't", "would", "wouldn't", "you", "you'd", "you'll", "you're", "you've", "your", "yours", "yourself", "yourselves"}; //from https://www.ranks.nl/stopwords 
        stopwords = Driver.removePunct(stopwords);
        words.removeAll(Arrays.asList(stopwords));

        return words;
    }

    public static void main(String[] args){
    }

    @Override // Adapted from https://www.infoworld.com/article/3305792/comparing-java-objects-with-equals-and-hashcode.html
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Sentence sentence = (Sentence) o;
        return text.equals(sentence.text) &&
                author.equals(sentence.author) &&
                timestamp.equals(sentence.timestamp);
    }

}
