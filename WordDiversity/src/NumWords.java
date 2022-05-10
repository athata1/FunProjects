import java.io.PrintWriter;
import java.util.ArrayList;

public class NumWords {
    static Node words = null;
    static PrintWriter writer;
    public static void main(String[] args) throws Exception{
        writer = new PrintWriter("7LetterWords.txt");
        getAllWords(7,"");

        writer.flush();
        writer.close();
    }
    public static void getAllWords(int n, String str) {
        if (str.length() == n) {
            writer.write(str + "\n");
            Node newNode = new Node(str);
            if (words == null) {
                words = newNode;
            }
            else {
                newNode.next = words;
                words = newNode;
            }
            return;
        }
        for (int i = 0; i < 26; i++) {
            str += (char)(i + 'A') + "";
            getAllWords(n,str);
            str = str.substring(0,str.length()-1);
        }
    }
}
