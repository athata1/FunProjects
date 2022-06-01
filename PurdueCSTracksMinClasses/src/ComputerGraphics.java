import java.util.TreeSet;

public class ComputerGraphics extends Track{

    public ComputerGraphics() {
        super("computerGraphics.txt");
    }

    @Override
    public boolean isCompleted(TreeSet<String> courses) {
        for (String s: super.required) {
            if (!courses.contains(s)) {
                return false;
            }
        }

        int matches = 0;
        if (!courses.contains("CS373") && !courses.contains("CS434")
            && !courses.contains("CS471"))
            return false;

        int count = -1;
        if (courses.contains("CS373"))
            count++;
        if (courses.contains("CS434"))
            count++;
        if (courses.contains("CS471"))
            count++;
        matches += count;
        for (String s: super.elective) {
            if (s.equals("CS373") || s.equals("CS434") || s.equals("CS471"))
                continue;
            if (courses.contains(s)) {
                matches++;
                if (matches >= 3) {
                    return true;
                }
            }
        }
        return matches >= 3;
    }
}
