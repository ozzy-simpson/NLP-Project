public class Sentence{
    private String text;
    private String author;
    private String timestamp;

    public Sentence() {

    }

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

    public static Sentence convertLine(String line) {

        String[] values = line.split("\", \"");
        String author = values[4];
        String timestamp = values[2];
        String text = values[5];
        
        Sentence converted = new Sentence(text, author, timestamp);

        return converted;
    }
    public static void main(String[] args){
        String line = "\"0\", \"14075\", \"Jun 14, 2009\", \"iran\", \"plutopup7\", \"Trouble in Iran\", I see Hmm Iran Iran so far away flockofseagullsweregeopoliticallycorrect\"";
        System.out.println(convertLine(line));
    }

}
