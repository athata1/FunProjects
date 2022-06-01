import java.util.TreeSet;

public class ComputerGraphics extends Track{

    public ComputerGraphics() {
        super("computerGraphics.txt");
    }

    @Override
    public boolean isCompleted(TreeSet<Integer> courses) {
        for (Integer s: super.required) {
            if (!courses.contains(s)) {
                return false;
            }
        }

        int matches = 0;
        if (!courses.contains(CourseDatabase.getInt("CS373")) && !courses.contains(CourseDatabase.getInt("CS434"))
            && !courses.contains(CourseDatabase.getInt("CS471")))
            return false;

        int count = -1;
        if (courses.contains(CourseDatabase.getInt("CS373")))
            count++;
        if (courses.contains(CourseDatabase.getInt("CS434")))
            count++;
        if (courses.contains(CourseDatabase.getInt("CS471")))
            count++;
        matches += count;
        for (Integer s: super.elective) {
            if (s == CourseDatabase.getInt("CS373") || s == CourseDatabase.getInt("CS434") || s == CourseDatabase.getInt("CS471"))
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
