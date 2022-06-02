import java.util.TreeSet;

public class Security extends Track{

    public Security() {
        super("security.txt");
    }

    @Override
    public boolean isCompleted(TreeSet<Integer> courses) {
        for (Integer s: super.required) {
            if (!courses.contains(s))
                return false;
        }
        int matches = 0;

        if (courses.contains(CourseDatabase.getInt("CS307")) || courses.contains(CourseDatabase.getInt("CS408")))
            matches++;

        if (courses.contains(CourseDatabase.getInt("CS348"))
                || courses.contains(CourseDatabase.getInt("CS448")) ||
                courses.contains(CourseDatabase.getInt("CS473")))
            matches++;
        if (courses.contains(CourseDatabase.getInt("CS352")))
            matches++;

        if (courses.contains(CourseDatabase.getInt("CS353")) || courses.contains(CourseDatabase.getInt("CS456")))
            matches++;

        if (courses.contains(CourseDatabase.getInt("CS373")) || courses.contains(CourseDatabase.getInt("CS471")))
            matches++;

        if (courses.contains(CourseDatabase.getInt("CS381")))
            matches++;

        if (courses.contains(CourseDatabase.getInt("CS422")))
            matches++;

        if (courses.contains(CourseDatabase.getInt("CS489")) || courses.contains(CourseDatabase.getInt("CS490-DSO")))
            matches++;

        if (courses.contains(CourseDatabase.getInt("CS490-SWS")))
            matches++;

        return matches >= 3;
    }
}
