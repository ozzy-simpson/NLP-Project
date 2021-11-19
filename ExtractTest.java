public class ExtractTest{
    public static void main(String[] args) {
        String line = "\"0\",\"0\",\"Thu Nov 18 21:36:17 UTC 2021\",\"test\",\"dr_kinga\",\"Hi, does this tweet with a comma process correctly?\"";
        
        String author = "dr_kinga";
        String timestamp = "Nov 18 2021";
        String text = "Hi does this tweet with a comma process correctly";
        
        Sentence testSentence = new Sentence(text, author, timestamp);

        System.out.println("test1: " + (testSentence.equals(Driver.convertLine(line))));
        System.out.println("expected: " + text + "\n" + "returned: " + Driver.convertLine(line).getText());

        System.out.println();

        System.out.println("test2: " + (testSentence.equals(Driver.convertLine(line))));
        System.out.println("expected: " + timestamp + "\n" + "returned: " + Driver.convertLine(line).getTimestamp());
    }
}