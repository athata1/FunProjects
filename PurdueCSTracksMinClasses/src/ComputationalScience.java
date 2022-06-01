import java.util.TreeSet;

public class ComputationalScience extends Track{
    public ComputationalScience() {
        super("compSciEng.txt");
    }

    @Override
    public boolean isCompleted(TreeSet<Integer> courses) {
        for (Integer s: super.required) {
            if (!courses.contains(s))
                return false;
        }

        if (!courses.contains(CourseDatabase.getInt("MA266")) && !courses.contains(CourseDatabase.getInt("MA366")))
            return false;

        if (!courses.contains(CourseDatabase.getInt("CS373")) && !courses.contains(CourseDatabase.getInt("CS473"))
                && !courses.contains(CourseDatabase.getInt("CS478")) &&
                !courses.contains(CourseDatabase.getInt("IE336")) && !courses.contains(CourseDatabase.getInt("ECE301")))
            return false;

        if (!courses.contains(CourseDatabase.getInt("CS352")) && !courses.contains(CourseDatabase.getInt("CS353")) && !courses.contains(CourseDatabase.getInt("CS354")))
            return false;

        int matches = 0;
        for (Integer s: super.elective) {
            if (s == CourseDatabase.getInt("MA366") || s == CourseDatabase.getInt("MA266"))
                continue;
            if (s == (CourseDatabase.getInt("CS373")) || s == (CourseDatabase.getInt("CS473"))
                    || s == (CourseDatabase.getInt("CS478")) ||
                    s == (CourseDatabase.getInt("IE336")) || s ==CourseDatabase.getInt("ECE301"))
                continue;

            if (s == (CourseDatabase.getInt("CS352")) || s == (CourseDatabase.getInt("CS353")) || s == (CourseDatabase.getInt("CS354")))
                continue;

            if (courses.contains(s)) {
                matches++;
                if (matches >= 2)
                    return true;
            }
        }
        return false;
    }
}
