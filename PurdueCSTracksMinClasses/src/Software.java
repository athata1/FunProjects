import java.util.TreeSet;

public class Software extends Track{

    public Software() {
        super("software.txt");
    }

    @Override
    public boolean isCompleted(TreeSet<Integer> courses) {
        for (Integer s: super.required) {
            if (!courses.contains(s)) {
                return false;
            }
        }

        if (courses.contains(CourseDatabase.getInt(("CS354"))) && courses.contains(CourseDatabase.getInt("CS352"))) {
            return true;
        }
        if (!courses.contains(CourseDatabase.getInt("CS354")) && !courses.contains(CourseDatabase.getInt("CS352"))) {
            return false;
        }

        for (Integer s: super.elective) {
            if (s == CourseDatabase.getInt("CS354") || s == CourseDatabase.getInt("CS352"))
                continue;
            if (courses.contains(s))
                return true;
        }

        return false;
    }
}
