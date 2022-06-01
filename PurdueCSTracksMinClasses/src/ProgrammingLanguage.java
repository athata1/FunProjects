import java.util.TreeSet;

public class ProgrammingLanguage extends Track{

    public ProgrammingLanguage() {
        super("programmingLanguages.txt");
    }

    @Override
    public boolean isCompleted(TreeSet<String> courses) {
        for (String s: super.required) {
            if (!courses.contains(s))
                return false;
        }
        int matches = 0;

        if (courses.contains("CS307") || courses.contains("CS408"))
            matches++;

        if (courses.contains("CS348") || courses.contains("CS448"))
            matches++;

        if (courses.contains("MA385") || courses.contains("MA453"))
            matches++;

        if (matches >= 3)
            return true;
        for (String s: super.elective) {
            if (courses.contains("CS307") || courses.contains("CS408"))
                continue;

            if (courses.contains("CS348") || courses.contains("CS448"))
                continue;

            if (courses.contains("MA385") || courses.contains("MA453"))
                continue;

            if (courses.contains(s))
                matches++;
            if (matches >= 3)
                return true;
        }

        return false;
    }
}
