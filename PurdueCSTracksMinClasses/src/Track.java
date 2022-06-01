import java.io.File;
import java.io.FileNotFoundException;
import java.util.TreeSet;
import java.util.Scanner;

public class Track {
    String fileName;
    TreeSet<Integer> required;
    TreeSet<Integer> elective;
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
                CourseDatabase.put(s);
                required.add(CourseDatabase.getInt(s));
            }
            while (scan.hasNextLine()) {
                String s = scan.nextLine();
                CourseDatabase.put(s);
                elective.add(CourseDatabase.getInt(s));
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Cannot find file");
        }
    }

    public boolean isCompleted(TreeSet<Integer> courses) {
        return false;
    }
}
