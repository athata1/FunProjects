import java.io.File;
import java.util.Scanner;

public class WordDiversity {
    public static void main(String[] args) throws Exception {
        Scanner getFile = new Scanner(System.in);
        System.out.println("Enter file name: ");
        String fileName = getFile.nextLine();
        File f = new File(fileName);
        if (!f.exists()) {
            System.out.println("Error: Could not find file");
        }
        else {

        }
    }
}
