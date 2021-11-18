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
    public static void main(String[] args){

    }

}
