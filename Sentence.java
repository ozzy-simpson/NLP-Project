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
