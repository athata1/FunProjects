import java.util.TreeSet;

public class Algorithms extends Track{

    public Algorithms() {
        super("algorithms.txt");
    }

    @Override
    public boolean isCompleted(TreeSet<Integer> courses) {
        for (Integer s: super.required) {
            if (!courses.contains(s))
                return false;
        }

        if (!courses.contains(CourseDatabase.getInt("CS352")) && !courses.contains(CourseDatabase.getInt("CS354")))
            return false;

        if (!courses.contains(CourseDatabase.getInt("CS373")) && !courses.contains(CourseDatabase.getInt("CS471")))
            return false;

        int matches = 0;
        for (Integer s: super.elective) {
            if (s == CourseDatabase.getInt("MA341") || s == CourseDatabase.getInt("MA353") || s == CourseDatabase.getInt("MA362")
                    || s == CourseDatabase.getInt("MA366") || s == CourseDatabase.getInt("MA385") || s == CourseDatabase.getInt("MA421")
                    || s == CourseDatabase.getInt("MA453"))
                continue;

            if (s == CourseDatabase.getInt("CS352") || s == CourseDatabase.getInt("CS354"))
                continue;

            if (s == CourseDatabase.getInt("CS373") || s == CourseDatabase.getInt("CS471"))
                continue;

            if (courses.contains(s)) {
                matches++;
                if (matches >= 3)
                    return true;
            }
        }


        if (courses.contains(CourseDatabase.getInt("MA341")) || courses.contains(CourseDatabase.getInt("MA353")) || courses.contains(CourseDatabase.getInt("MA362"))
            || courses.contains(CourseDatabase.getInt("MA366")) || courses.contains(CourseDatabase.getInt("MA385")) || courses.contains(CourseDatabase.getInt("MA421"))
            || courses.contains(CourseDatabase.getInt("MA453"))) {
            matches++;
        }

        return matches >= 3;
    }
}
