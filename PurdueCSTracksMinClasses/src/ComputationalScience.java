import java.util.TreeSet;

public class ComputationalScience extends Track{
    public ComputationalScience() {
        super("compSciEng.txt");
    }

    @Override
    public boolean isCompleted(TreeSet<String> courses) {
        for (String s: super.required) {
            if (!courses.contains(s))
                return false;
        }

        if (!courses.contains("MA266") && !courses.contains("MA366"))
            return false;

        if (!courses.contains("CS373") && !courses.contains("CS473")
                && !courses.contains("CS478") &&
                !courses.contains("IE336") && !courses.contains("ECE301"))
            return false;

        if (!courses.contains("CS352") && !courses.contains("CS353") && !courses.contains("CS354"))
            return false;

        int matches = 0;
        for (String s: super.elective) {
            if (s.equals("CS373") || s.equals("CS473")
                    || s.equals("CS478") ||
                    s.equals("IE336") || s.equals("ECE301"))
                return false;

            if (s.equals("CS352") || s.equals("CS353") || s.equals("CS354"))
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
