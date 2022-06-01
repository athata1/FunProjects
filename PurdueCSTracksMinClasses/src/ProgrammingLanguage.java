import java.util.TreeSet;

public class ProgrammingLanguage extends Track{

    public ProgrammingLanguage() {
        super("programmingLanguages.txt");
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

        if (courses.contains(CourseDatabase.getInt("CS348")) || courses.contains(CourseDatabase.getInt("CS448")))
            matches++;

        if (courses.contains(CourseDatabase.getInt("MA385")) || courses.contains(CourseDatabase.getInt("MA453")))
            matches++;

        if (matches >= 3)
            return true;
        for (Integer s: super.elective) {
            if (s == (CourseDatabase.getInt("CS307")) || s == (CourseDatabase.getInt("CS408")))
                continue;

            if (s == (CourseDatabase.getInt("CS348")) || s == (CourseDatabase.getInt("CS448")))
                continue;

            if (s == (CourseDatabase.getInt("MA385")) || s == (CourseDatabase.getInt("MA453")))
                continue;

            if (courses.contains(s))
                matches++;
            if (matches >= 3)
                return true;
        }

        return false;
    }
}
