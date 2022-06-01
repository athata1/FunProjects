import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Scanner;

public class Track {
    String fileName;
    TreeSet<String> required;
    TreeSet<String> elective;
    public Track(String fileName) {
        this.fileName = fileName;
        required = new TreeSet<>();
        elective = new TreeSet<>();
        addClassesFromFile();
    }

    public void addClassesFromFile() {
        try {
            Scanner scan = new Scanner(new File(fileName));
            while (scan.hasNextLine()) {
                String s = scan.nextLine();
                if (s.equals(""))
                    break;
                required.add(s);
            }
            while (scan.hasNextLine()) {
                String s = scan.nextLine();
                elective.add(s);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Cannot find file");
        }
    }
}
